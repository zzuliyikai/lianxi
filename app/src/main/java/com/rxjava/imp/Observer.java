package com.rxjava.imp;

/**
 * Created by Administrator on 2018/10/11.
 */

public interface Observer<T> {

    void onComplete();

    void onNext(T value);

    void onError(Throwable t);


}
