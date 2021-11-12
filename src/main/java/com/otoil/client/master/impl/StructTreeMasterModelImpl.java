package com.otoil.client.master.impl;


import com.google.gwt.core.shared.GWT;
import com.otoil.client.dto.RequestDocumentCardBean;
import com.otoil.client.dto.ResponseDocumentCardBean;
import com.otoil.client.master.StructTreeMasterModel;
import com.otoil.shared.StructTreeService;

import io.reactivex.Single;
import ru.ot.gwt.utils.client.rest.RestServiceAdapter;
import ru.ot.gwt.utils.shared.tree.TreeNode;


public class StructTreeMasterModelImpl implements StructTreeMasterModel
{

    private RestServiceAdapter<StructTreeService> service = RestServiceAdapter
        .get(GWT.create(StructTreeService.class));

    @Override
    public Single<Boolean> saveDocumentCard(RequestDocumentCardBean request)
    {
        return service.toSingle(rs -> rs.saveDocumentCard(request));
    }

    @Override
    public Single<TreeNode<ResponseDocumentCardBean>> loadDocTree()
    {
        return service.toSingle(rs -> rs.loadDocTree());
    }
}
