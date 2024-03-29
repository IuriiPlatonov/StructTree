package com.otoil.client.i18n;


import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;


public interface StructTreeConstant extends Constants
{
    StructTreeConstant INSTANCE = GWT
            .create(StructTreeConstant.class);

    @DefaultStringValue("Name")
    String name();

    @DefaultStringValue("Ordered number")
    String orderedNumber();

    @DefaultStringValue("Change date")
    String changeDate();

    @DefaultStringValue("Image")
    String image();

    @DefaultStringValue("Attributes name:")
    String attributesName();

    @DefaultStringValue("Values:")
    String values();
    
    @DefaultStringValue("Document cards table")
    String docCardTable();   
    
    @DefaultStringValue("Detail")
    String detail();   
    
    @DefaultStringValue("Save")
    String save();
}
