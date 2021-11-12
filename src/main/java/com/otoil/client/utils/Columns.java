package com.otoil.client.utils;

import java.util.Date;
import java.util.function.Consumer;

import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Image;
import com.otoil.client.dto.RequestDocumentCardBean;
import com.otoil.shared.DefaultTreeNamedBean;

import ru.ot.gwt.utils.client.BeanProperty;
import ru.ot.wevelns.client.NSTreeNodeDataGrid;
import ru.ot.wevelns.client.cell.datebox.DateboxCell;
import ru.ot.wevelns.client.cellview.columns.AbstractDateColumn;
import ru.ot.wevelns.client.cellview.columns.StringColumn;
import ru.ot.wevelns.client.tree.DefaultTreeNode;

public class Columns
{
    
    public static <T extends DefaultTreeNamedBean> void addStringColumn(NSTreeNodeDataGrid<T> treeTable,
        BeanProperty<T, String> property, String title,
        int width, boolean visible)
    {
        Column<DefaultTreeNode<T>, String> column = new StringColumn<DefaultTreeNode<T>>()
        {
            @Override
            public String getValue(
                DefaultTreeNode<T> object)
            {
                return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width + "px");
        treeTable.setColumnVisible(column, visible);
    }
    
    public static <T extends DefaultTreeNamedBean> void addEditableStringColumn(NSTreeNodeDataGrid<T> treeTable, Consumer<RequestDocumentCardBean> consumer,
        BeanProperty<T, String> property, String title,
        int width, boolean visible)
    {
        Column<DefaultTreeNode<T>, String> column = new StringColumn<DefaultTreeNode<T>>()
        {
            @Override
            public String getValue(
                DefaultTreeNode<T> object)
            {
                return property.get(object.getValue());
            }
        };
        column.setSortable(true);

        column.setFieldUpdater((index, node, value) -> {
            property.set(node.getValue(), value);
            consumer.accept(new RequestDocumentCardBean(
                node.getValue().getId(), node.getValue().getName(),
                node.getValue().getOrderNumber()));;
          
        });
        
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width + "px");
        treeTable.setColumnVisible(column, visible);
    }
    
    
    public static <T extends DefaultTreeNamedBean> void addImageColumn(NSTreeNodeDataGrid<T> treeTable,
        BeanProperty<T, String> property, String title,
        int width, boolean visible)
    {

        Column<DefaultTreeNode<T>, String> column = new Column<DefaultTreeNode<T>, String>(
            new ImageCell())
        {

            @Override
            public void render(Context context,
                DefaultTreeNode<T> object,
                SafeHtmlBuilder sb)
            {
                Image img = new Image(property.get(object.getValue()));

                SafeHtml imageHtml = SafeHtmlUtils
                    .fromTrustedString(img.toString());
                sb.append(imageHtml);

                sb.toSafeHtml();
            }

            @Override
            public String getValue(
                DefaultTreeNode<T> object)
            {
                if (object == null)
                    return "";
                else
                    return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width, Unit.PX);
        treeTable.setColumnVisible(column, visible);

    }

    public static <T extends DefaultTreeNamedBean> void addDateColumn(NSTreeNodeDataGrid<T> treeTable,
        BeanProperty<T, Date> property, String title,
        int width, boolean visible)
    {
        AbstractDateColumn<DefaultTreeNode<T>, DateboxCell> column = new AbstractDateColumn<DefaultTreeNode<T>, DateboxCell>(
            new DateboxCell())
        {
            @Override
            public Date getValue(
                DefaultTreeNode<T> object)
            {
                return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width + "px");
        treeTable.setColumnVisible(column, visible);
    }

}
