package com.example.admin.mvpdagger.view.base;

//common methods for presenter
public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
