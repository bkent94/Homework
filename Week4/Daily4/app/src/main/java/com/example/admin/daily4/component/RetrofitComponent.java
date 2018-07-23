package com.example.admin.daily4.component;

import com.example.admin.daily4.MainActivity;
import com.example.admin.daily4.module.RetroFitProvider;

import dagger.Component;


@Component(modules = RetroFitProvider.class)
public interface RetrofitComponent {

    void provideRetrofit(MainActivity mainActivity);
}
