package com.otoil.client.master;


import com.otoil.client.dto.RequestDocumentCardBean;
import com.otoil.client.dto.ResponseDocumentCardBean;

import io.reactivex.Single;
import ru.ot.gwt.utils.shared.tree.TreeNode;
import ru.ot.mvp.client.presenters.Model;


public interface StructTreeMasterModel extends Model
{
    Single<Boolean> saveDocumentCard(RequestDocumentCardBean request);

    Single<TreeNode<ResponseDocumentCardBean>> loadDocTree();
}
