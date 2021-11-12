package com.otoil.client.impl;


import com.otoil.client.StructTreeClientFactory;
import com.otoil.client.StructTreeMainModel;
import com.otoil.client.StructTreeMainView;
import com.otoil.client.detail.StructTreeDetailModel;
import com.otoil.client.detail.StructTreeDetailView;
import com.otoil.client.detail.impl.StructTreeDetailModelImpl;
import com.otoil.client.detail.impl.StructTreeDetailViewImpl;
import com.otoil.client.master.StructTreeMasterModel;
import com.otoil.client.master.StructTreeMasterView;
import com.otoil.client.master.impl.StructTreeMasterModelImpl;
import com.otoil.client.master.impl.StructTreeMasterViewImpl;

import ru.ot.mvp.client.history.AsyncPlaceController;


public enum StructTreeClientFactoryImpl implements StructTreeClientFactory
{

    INSTANCE;

    @Override
    public StructTreeMainModel getMainModel()
    {
        return new StructTreeMainModelImpl();
    }

    @Override
    public StructTreeMainView getMainView()
    {
        return new StructTreeMainViewImpl();
    }

    @Override
    public StructTreeMasterModel getMasterModel()
    {
        return new StructTreeMasterModelImpl();
    }

    @Override
    public StructTreeMasterView getMasterView()
    {
        return new StructTreeMasterViewImpl();
    }

    @Override
    public StructTreeDetailView getDetailView()
    {
        return new StructTreeDetailViewImpl();
    }

    @Override
    public StructTreeDetailModel getDetailModel()
    {
        return new StructTreeDetailModelImpl();
    }

    @Override
    public AsyncPlaceController getPlaceController()
    {
        return null;
    }

}
