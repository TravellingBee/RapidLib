package com.marno.mbasiclib.basic;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by Marno on 2016/10/26/14:23
 * Function：根据Fragment生命周期判断Retrofit的网络请求与否
 * Desc：
 */

public abstract class RxFragment extends Fragment {
    protected final PublishSubject<FragmentEvent> lifecycleSubject = PublishSubject.create();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }

    //监听Frament声明周期，当Fragment销毁后，停止网络请求
    @NonNull
    public <T> Observable.Transformer<T, T> bindUntilEvent(@NonNull final FragmentEvent event) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> sourceObservable) {
                Observable<FragmentEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<FragmentEvent, Boolean>() {
                            @Override
                            public Boolean call(FragmentEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return sourceObservable.takeUntil(compareLifecycleObservable);
            }
        };
    }

    public enum FragmentEvent {
        ATTACH,
        CREATE,
        CREATE_VIEW,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY_VIEW,
        DESTROY,
        DETACH
    }
}
