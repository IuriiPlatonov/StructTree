package com.otoil.shared;


import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;

import com.otoil.client.dto.DocCardKindStructureBean;
import com.otoil.client.dto.RequestDocumentCardBean;
import com.otoil.client.dto.ResponseDocumentCardBean;

import ru.ot.gwt.utils.shared.tree.TreeNode;

@Path("/structtree/rest/ext")
@Produces(MediaType.APPLICATION_JSON)
public interface StructTreeService extends DirectRestService
{

    @PUT
    @Path("/documentCard/")
    @Produces(MediaType.APPLICATION_JSON)
    Boolean saveDocumentCard(RequestDocumentCardBean request);
    
    @GET
    @Path("/docTree")
    @Produces(MediaType.APPLICATION_JSON)
    TreeNode<ResponseDocumentCardBean> loadDocTree();
    
    @GET
    @Path("/structTree/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    TreeNode<DocCardKindStructureBean> loadStructTree(@PathParam("id") String id); 
}
