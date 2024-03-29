package com.otoil.shared;


import ru.ot.gwt.utils.client.bean.DefaultNamedBean;


public class DefaultTreeNamedBean extends DefaultNamedBean implements
        TreeNamedBean
{
    private String parentId;
    private String orderNumber;

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public DefaultTreeNamedBean()
    {
        super();
    }

    public DefaultTreeNamedBean(String id, String name)
    {
        super(id, name);
    }

    public DefaultTreeNamedBean(String id, String name, String parentId)
    {
        super(id, name);
        this.parentId = parentId;
    }

    @Override
    public String getParentId()
    {
        return parentId;
    }

    @Override
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    @Override
    public boolean hasChildren()
    {
        return true;
    }
}
