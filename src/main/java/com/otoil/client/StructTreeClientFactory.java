package com.otoil.client;

import com.otoil.client.detail.StructTreeDetailModel;
import com.otoil.client.detail.StructTreeDetailView;
import com.otoil.client.master.StructTreeMasterModel;
import com.otoil.client.master.StructTreeMasterView;

import ru.ot.mvp.client.factory.ClientFactory;

public interface StructTreeClientFactory extends ClientFactory
{
    StructTreeMainModel getMainModel();

    StructTreeMainView getMainView();

    StructTreeMasterModel getMasterModel();

    StructTreeMasterView getMasterView();

    StructTreeDetailModel getDetailModel();
    
    StructTreeDetailView getDetailView();

}
