package com.example.admin.daily4.module;

import com.example.admin.daily4.RetroFitHelper;
import com.example.admin.daily4.component.DaggerRetrofitComponent;
import com.example.admin.daily4.component.RetrofitComponent;

import dagger.Module;
import dagger.Provides;

@Module
public class RetroFitProvider {

    @Provides
    RetroFitHelper getRetrofitHelper(){
        return new RetroFitHelper();
    }


}
