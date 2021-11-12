package com.otoil.client.detail.impl;


import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.client.detail.StructTreeDetailView;

import com.otoil.client.dto.DocCardKindStructureBean;
import com.otoil.client.i18n.StructTreeConstant;
import com.otoil.client.utils.Columns;
import com.otoil.client.utils.STBeanProperties;

import ru.ot.wevelns.client.NSBlock;
import ru.ot.wevelns.client.NSTreeNodeDataGrid;

import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.HasTreeData;


public class StructTreeDetailViewImpl extends VerticalPanel
        implements StructTreeDetailView
{

    private final static StructTreeConstant CONSTANTS = StructTreeConstant.INSTANCE;
    private NSTreeNodeDataGrid<DocCardKindStructureBean> treeTable = new NSTreeNodeDataGrid<DocCardKindStructureBean>();;

    @Override
    public Widget asWidget()
    {
        return this;
    }

    public StructTreeDetailViewImpl()
    {
        init();
    }

    public void init()
    {
        initTreeNodeDataGrid();

        NSBlock block = new NSBlock(CONSTANTS.detail());
        block.setSize("50vw", "100vh");
        block.setWidget(treeTable);

        this.add(block);
    }

    private void initTreeNodeDataGrid()
    {
        Columns.addStringColumn(treeTable, STBeanProperties.stName, CONSTANTS.name(), 200,
            true);
        Columns.addStringColumn(treeTable, STBeanProperties.stId, "id", 200,
            true);
        Columns.addStringColumn(treeTable, STBeanProperties.stPath, "path", 500,
            true);
    }

    @Override
    public HasTreeData<DefaultTreeNode<DocCardKindStructureBean>> getTree()
    {
        // TODO Auto-generated method stub
        return treeTable;
    }

}
