
package com.otoil.client.master;


import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.view.client.NoSelectionModel;
import com.otoil.client.StructTreeClientFactory;
import com.otoil.client.dto.RequestDocumentCardBean;
import com.otoil.client.dto.ResponseDocumentCardBean;
import com.otoil.client.event.GetDetailIdEvent;

import lombok.Setter;
import ru.ot.mvp.client.presenters.AbstractPresenter;
import ru.ot.ot_132_5_0030.client.rest.helpers.tree.FetchedTreeProviderAdapter;
import ru.ot.wevelns.client.tree.DefaultTreeNode;


@Setter
public class StructTreeMasterPresenter
        extends AbstractPresenter<StructTreeMasterModel, StructTreeMasterView>

{

    private FetchedTreeProviderAdapter<ResponseDocumentCardBean> provider = new FetchedTreeProviderAdapter<>();
    private NoSelectionModel<DefaultTreeNode<ResponseDocumentCardBean>> selectionModel = new NoSelectionModel<>();

    public StructTreeMasterPresenter(StructTreeClientFactory factory)
    {
        super(factory.getMasterModel(), factory.getMasterView());
        provider.addDataDisplay(view.getTree());
        view.getTree().setSelectionModel(selectionModel);

    }

    @Override
    public void bind()
    {
        Defaults.setServiceRoot(
            com.google.gwt.core.client.GWT.getHostPageBaseURL());

        model.loadDocTree().subscribe((treeData) -> {
            provider.setTree(treeData);
            provider.refresh();
        });

        selectionModel.addSelectionChangeHandler(x -> createGetDetailIdEvent(
            selectionModel.getLastSelectedObject().getValue().getDccrdkndDccrdkndId()));

        view.getTreeSaveSubject().subscribe(this::saveDocument);
    }

    private void saveDocument(RequestDocumentCardBean cardBean)
    {
        model.saveDocumentCard(cardBean).subscribe();
    }

    private void createGetDetailIdEvent(String id)
    {
        eventBus.fireEvent(new GetDetailIdEvent(id));
    }
}
