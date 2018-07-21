package com.example.admin.mvpdagger.view.di;

import com.example.admin.mvpdagger.view.github.GithubActivity;

import dagger.Component;
import dagger.internal.DaggerCollections;

//contract for dependencies and dependent
@Component(modules=GithubModule.class)
public interface GithubComponent {

    void inject(GithubActivity activity);

}
