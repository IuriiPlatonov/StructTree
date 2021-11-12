package com.otoil.client.detail.impl;


import com.google.gwt.core.shared.GWT;
import com.otoil.client.detail.StructTreeDetailModel;
import com.otoil.client.dto.DocCardKindStructureBean;
import com.otoil.shared.StructTreeService;

import io.reactivex.Single;
import ru.ot.gwt.utils.client.rest.RestServiceAdapter;
import ru.ot.gwt.utils.shared.tree.TreeNode;


public class StructTreeDetailModelImpl implements StructTreeDetailModel
{

    private RestServiceAdapter<StructTreeService> service = RestServiceAdapter
        .get(GWT.create(StructTreeService.class));

    @Override
    public Single<TreeNode<DocCardKindStructureBean>> loadStructTree(String id)
    {

        return service.toSingle(rs -> rs.loadStructTree(id));
    }

}
