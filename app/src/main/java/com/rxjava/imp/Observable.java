package com.rxjava.imp;

/**
 * Created by Administrator on 2018/10/11.
 */

public class Observable<T> {
    OnSubercibe onSubercibe;

    public Observable(OnSubercibe onSubercibe) {
        this.onSubercibe = onSubercibe;
    }

    public static <T> Observable<T> create(OnSubercibe<T> onSubercibe) {
        return new Observable(onSubercibe);
    }

    public void subscribe(Subscriber<? super T> subscriber){
        subscriber.onStart();
        onSubercibe.call(subscriber);

    }


    public interface OnSubercibe<T> {
        void call(Subscriber<? super T> subscriber);
    }

}
