package com.example.admin.mvpdagger.view.di;

import com.example.admin.mvpdagger.view.github.GithubPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class GithubModule {
    //each method would return the dependency required
    @Provides
    GithubPresenter providesGithubPresenter(){
        return new GithubPresenter();
    }
}
