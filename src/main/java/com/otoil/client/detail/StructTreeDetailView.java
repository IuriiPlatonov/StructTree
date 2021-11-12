package com.otoil.client.detail;


import com.google.gwt.user.client.ui.IsWidget;
import com.otoil.client.dto.DocCardKindStructureBean;

import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.HasTreeData;


public interface StructTreeDetailView extends IsWidget
{
    HasTreeData<DefaultTreeNode<DocCardKindStructureBean>> getTree();
}
