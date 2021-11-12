package com.otoil.client.master;


import com.google.gwt.user.client.ui.IsWidget;
import com.otoil.client.dto.RequestDocumentCardBean;
import com.otoil.client.dto.ResponseDocumentCardBean;

import io.reactivex.subjects.PublishSubject;
import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.TreeDataDisplay;


public interface StructTreeMasterView extends IsWidget
{
    TreeDataDisplay<DefaultTreeNode<ResponseDocumentCardBean>> getTree();

    PublishSubject<RequestDocumentCardBean> getTreeSaveSubject();
}
