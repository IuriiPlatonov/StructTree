package com.otoil.client.master.impl;


import java.util.function.Consumer;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.client.dto.RequestDocumentCardBean;
import com.otoil.client.dto.ResponseDocumentCardBean;
import com.otoil.client.i18n.StructTreeConstant;
import com.otoil.client.master.StructTreeMasterView;
import com.otoil.client.utils.Columns;
import com.otoil.client.utils.STBeanProperties;

import io.reactivex.subjects.PublishSubject;
import ru.ot.wevelns.client.NSBlock;
import ru.ot.wevelns.client.NSTreeNodeDataGrid;
import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.TreeDataDisplay;


public class StructTreeMasterViewImpl extends VerticalPanel
        implements StructTreeMasterView
{

    private final static StructTreeConstant CONSTANTS = StructTreeConstant.INSTANCE;

    private NSTreeNodeDataGrid<ResponseDocumentCardBean> treeTable = new NSTreeNodeDataGrid<ResponseDocumentCardBean>();;

    private PublishSubject<RequestDocumentCardBean> treeSaveSubject = PublishSubject
        .create();

    public StructTreeMasterViewImpl()
    {
        init();
    }

    public void init()
    {
        initTreeNodeDataGrid();

        NSBlock block = new NSBlock(CONSTANTS.docCardTable());
        block.setSize("50vw", "100vh");
        block.setWidget(treeTable);

        this.add(block);

    }

    private void initTreeNodeDataGrid()
    {
        treeTable.setEditing(true);

        Consumer<RequestDocumentCardBean> saveEvent = treeSaveSubject::onNext;

        Columns.addEditableStringColumn(treeTable, saveEvent,
            STBeanProperties.dcName, CONSTANTS.name(), 200, true);
        Columns.addEditableStringColumn(treeTable, saveEvent,
            STBeanProperties.dcOrderedNumber, CONSTANTS.orderedNumber(), 95,
            true);
        Columns.addDateColumn(treeTable, STBeanProperties.dcChangeDate,
            CONSTANTS.changeDate(), 100, true);
        Columns.addImageColumn(treeTable, STBeanProperties.dcImage,
            CONSTANTS.image(), 45, true);

    }

    @Override
    public TreeDataDisplay<DefaultTreeNode<ResponseDocumentCardBean>> getTree()
    {
        return treeTable;
    }

    @Override
    public Widget asWidget()
    {
        return this;
    }

    @Override
    public PublishSubject<RequestDocumentCardBean> getTreeSaveSubject()
    {
        return treeSaveSubject;
    }

}
