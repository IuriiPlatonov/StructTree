package com.otoil.client.detail;


import com.otoil.client.StructTreeClientFactory;
import com.otoil.client.dto.DocCardKindStructureBean;
import com.otoil.client.event.SendDetailIdEvent;

import io.reactivex.disposables.CompositeDisposable;
import ru.ot.gwt.utils.client.rx.RxGwtEvent;
import ru.ot.mvp.client.presenters.AbstractPresenter;
import ru.ot.ot_132_5_0030.client.rest.helpers.tree.FetchedTreeProviderAdapter;


public class StructTreeDetailPresenter
        extends AbstractPresenter<StructTreeDetailModel, StructTreeDetailView>
{

    private FetchedTreeProviderAdapter<DocCardKindStructureBean> provider = new FetchedTreeProviderAdapter<>();

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    public StructTreeDetailPresenter(StructTreeClientFactory factory)
    {
        super(factory.getDetailModel(), factory.getDetailView());
        provider.addDataDisplay(view.getTree());
    }

    @Override
    public void bind()
    {
        subscriptions.add(
            RxGwtEvent.observeOn(eventBus, SendDetailIdEvent.TYPE).subscribe(
                x -> model.loadStructTree(x.getId()).subscribe((treeData) -> {
                    provider.setTree(treeData);
                    provider.refresh();
                })));
    }

    @Override
    public void onCancel()
    {
        subscriptions.clear();
        super.onCancel();
    }

    @Override
    public void onStop()
    {
        subscriptions.clear();
        super.onStop();
    }
}
