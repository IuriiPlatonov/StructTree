package com.otoil.server;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.Path;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.otoil.client.dto.DocCardKindStructureBean;
import com.otoil.client.dto.RequestDocumentCardBean;
import com.otoil.client.dto.ResponseDocumentCardBean;
import com.otoil.server.entities.treedoccard.TreeDocCard;
import com.otoil.shared.StructTreeService;

import lombok.SneakyThrows;
import ru.ep.sdo.Session;
import ru.ep.sdo.SessionFactory;
import ru.ep.sdo.filter.EqualFilter;
import ru.ep.sdo.list.XMLListModel;
import ru.ep.sdo.lob.blob.SDOBlob;
import ru.ot.gwt.utils.shared.tree.TreeBuilder;
import ru.ot.gwt.utils.shared.tree.TreeNode;
import server.Constant;


@Path("/ext")
public class StructTreeServiceImpl implements StructTreeService
{

    @SuppressWarnings("unchecked")
    @Override
    public TreeNode<ResponseDocumentCardBean> loadDocTree()
    {
        Session session = getSession();

        XMLListModel listModel = session.getListModel("StructTree.TreeDocCard");

        Iterator<TreeDocCard> iterator = listModel.iterator();
        List<ResponseDocumentCardBean> list = new ArrayList<>();

        while (iterator.hasNext())
        {
            TreeDocCard doc = iterator.next();

            list.add(ResponseDocumentCardBean.builder()
                .id(doc.getDcmcrdId().toString()).name(doc.getDocName())
                .orderNumber(doc.getOrderNumber().toString())
                .changeDate(new Date(doc.getChangeDate().getTime()))
                .parent(doc.getDcmcrdDcmcrdId() != null
                    ? doc.getDcmcrdDcmcrdId().toString()
                    : null)
                .binaryData(
                    doc.getIcon() != null ? convertBlob(doc.getIcon()) : null)
                .dccrdkndDccrdkndId(doc.getDccrdkndDccrdkndId().toString())
                .build());
        }

        Iterator<ResponseDocumentCardBean> listIterator = list.iterator();

        TreeBuilder<ResponseDocumentCardBean> treeBuilder = TreeBuilder
            .fromIterator(ResponseDocumentCardBean::getId,
                ResponseDocumentCardBean::getParent, listIterator);

        return treeBuilder.build();
    }

    @Override
    public Boolean saveDocumentCard(RequestDocumentCardBean docCard)
    {
        Session session = getSession();
        XMLListModel listModel = session.getListModel("StructTree.TreeDocCard");

        listModel.setFilter(new EqualFilter(TreeDocCard.PROPERTYNAME_DCMCRD_ID,
            docCard.getDcmcrdId()));

        listModel.fetchAll();

        TreeDocCard bo = (TreeDocCard) listModel.get(0);
        bo.setDocName(docCard.getName());
        bo.setOrderNumber(new BigDecimal(docCard.getOrderNumber()));
        session.commit();

        return true;
    }

    @SneakyThrows
    @Override
    public TreeNode<DocCardKindStructureBean> loadStructTree(String id)
    {

        HttpSolrClient solr = getSolrClient();

        SolrQuery query = new SolrQuery();
        query.setQuery("catKindId:" + id);
        query.addField(Constant.catDccrdkndstId);
        query.addField(Constant.catDccrdkndstIdPath);
        query.addField(Constant.catName);
        QueryResponse response = solr.query(query);
        SolrDocumentList docs = response.getResults();

        List<DocCardKindStructureBean> docCardKindStructList = new ArrayList<>();

        for (SolrDocument doc : docs)
        {
            search(docCardKindStructList,
                (String) doc.getFieldValue(Constant.catDccrdkndstId), solr);
        }

        docCardKindStructList.sort((o1, o2) -> o1.getCatDccrdkndstIdPath()
            .compareTo(o2.getCatDccrdkndstIdPath()));

        Iterator<DocCardKindStructureBean> iterator = docCardKindStructList
            .iterator();

        TreeBuilder<DocCardKindStructureBean> treeBuilder = TreeBuilder
            .fromIterator(DocCardKindStructureBean::getId,
                DocCardKindStructureBean::getParentId, iterator);

        return treeBuilder.build();
    }

    @SneakyThrows
    private void search(List<DocCardKindStructureBean> list, String parent,
        HttpSolrClient solr)
    {

        SolrQuery query = new SolrQuery();
        query.setQuery("catDccrdkndstIdPath:" + parent + "~");

        query.setRows(Integer.MAX_VALUE);
        query.addField(Constant.catDccrdkndstId);
        query.addField(Constant.catDccrdkndstIdPath);
        query.addField(Constant.catName);
        query.addField(Constant.catParentId);

        QueryResponse response = solr.query(query);
        SolrDocumentList docs = response.getResults();

        for (SolrDocument doc : docs)
        {
            list.add(DocCardKindStructureBean.builder()
                .id((String) doc.getFieldValue(Constant.catDccrdkndstId))
                .parentId((String) doc.getFieldValue(Constant.catParentId))
                .name((String) doc.getFieldValue(Constant.catName))
                .catDccrdkndstIdPath(
                    (String) doc.getFieldValue(Constant.catDccrdkndstIdPath))
                .build());
        }
    }

    private Session getSession()
    {
        Properties properties = new Properties();
        properties.put("user", "ATOLL_SERVICE");
        properties.put("password", "ATOLL_SERVICE");
        properties.put("connectionString",
            "jdbc:oracle:thin:@10.100.22.9:1521:HPDOILDV");
        // "jdbc:oracle:thin:@185.40.76.81:1521:MPDEV");
        properties.put("driverClassName", "oracle.jdbc.driver.OracleDriver");
        properties.put("databaseType", "ora");
        Session session = SessionFactory.getInstance()
            .createSessionFromFile("com/otoil/server/new.session", properties);
        return session;
    }

    private HttpSolrClient getSolrClient()
    {
        HttpSolrClient solr = new HttpSolrClient.Builder(Constant.solrCoreUrl)
            .build();
        solr.setParser(new XMLResponseParser());
        return solr;
    }

    @SneakyThrows
    private String convertBlob(SDOBlob blob)
    {
        byte[] bytes = IOUtils.toByteArray(blob.openStream());

        String base64 = Base64.encodeBase64String(bytes);
        base64 = "data:image/png;base64," + base64;

        return base64;
    }

}
