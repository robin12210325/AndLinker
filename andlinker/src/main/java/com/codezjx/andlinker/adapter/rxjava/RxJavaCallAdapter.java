package com.codezjx.andlinker.adapter.rxjava;

import com.codezjx.andlinker.Call;
import com.codezjx.andlinker.CallAdapter;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Scheduler;


/**
 * Created by codezjx on 2017/10/16.<br/>
 */
final class RxJavaCallAdapter<R> implements CallAdapter<R, Observable<R>> {
    
    private final Scheduler mScheduler;

    RxJavaCallAdapter(Scheduler scheduler) {
        mScheduler = scheduler;
    }

    @Override
    public Observable<R> adapt(Call<R> call) {
        OnSubscribe<R> subscribe = new CallExecuteOnSubscribe<>(call);
        Observable<R> observable = Observable.create(subscribe);
        
        if (mScheduler != null) {
            observable = observable.subscribeOn(mScheduler);
        }

        return observable;
    }


}
