package com.otoil.client;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.otoil.client.detail.StructTreeDetailPresenter;
import com.otoil.client.event.GetDetailIdEvent;
import com.otoil.client.event.SendDetailIdEvent;
import com.otoil.client.master.StructTreeMasterPresenter;

import io.reactivex.disposables.CompositeDisposable;
import ru.ot.gwt.utils.client.rx.RxGwtEvent;
import ru.ot.mvp.client.presenters.AbstractPresenter;


public class StructTreeMainPresenter
        extends AbstractPresenter<StructTreeMainModel, StructTreeMainView>
{

    private StructTreeMasterPresenter masterPresenter;
    private StructTreeDetailPresenter detailPresenter;

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    public StructTreeMainPresenter(StructTreeClientFactory factory)
    {
        super(factory.getMainModel(), factory.getMainView());
        masterPresenter = new StructTreeMasterPresenter(factory);
        detailPresenter = new StructTreeDetailPresenter(factory);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus)
    {
        super.start(panel, eventBus);

        detailPresenter.start(view.getDetailPanel(), eventBus);
        masterPresenter.start(view.getMasterPanel(), eventBus);

    }

    @Override
    public void bind()
    {
        subscriptions.add(
            RxGwtEvent.observeOn(eventBus, GetDetailIdEvent.TYPE).subscribe(
                x -> eventBus.fireEvent(new SendDetailIdEvent(x.getId()))));
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
