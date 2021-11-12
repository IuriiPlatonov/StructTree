package com.otoil.client.detail;


import com.otoil.client.dto.DocCardKindStructureBean;
import io.reactivex.Single;
import ru.ot.gwt.utils.shared.tree.TreeNode;
import ru.ot.mvp.client.presenters.Model;


public interface StructTreeDetailModel extends Model
{
    Single<TreeNode<DocCardKindStructureBean>> loadStructTree(String id);
}
