package com.otoil.client;


import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;


public interface StructTreeMainView extends IsWidget
{
    AcceptsOneWidget getMasterPanel();

    AcceptsOneWidget getDetailPanel();
}
