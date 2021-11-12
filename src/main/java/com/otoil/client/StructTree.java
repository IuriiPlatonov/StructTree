package com.otoil.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.otoil.client.impl.StructTreeClientFactoryImpl;


public class StructTree implements EntryPoint
{
    private StructTreeMainPresenter presenter;
    private EventBus eventBus;

    public StructTree()
    {
        eventBus = new SimpleEventBus();
        StructTreeClientFactory factory = StructTreeClientFactoryImpl.INSTANCE;
        presenter = new StructTreeMainPresenter(factory);
    }

    @Override
    public void onModuleLoad()
    {
        presenter.start(null, eventBus);
    }
}
