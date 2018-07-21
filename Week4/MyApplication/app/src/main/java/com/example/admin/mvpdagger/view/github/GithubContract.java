package com.example.admin.mvpdagger.view.github;

import com.example.admin.mvpdagger.view.base.BasePresenter;
import com.example.admin.mvpdagger.view.base.BaseView;


//communication from presenter to view
public interface GithubContract {
    interface View extends BaseView {
        void onValidationResults(String validName);
    }

    //communication from view to presenter
    interface Presenter extends BasePresenter<View> {
        void validateName(String name);
    }
}
