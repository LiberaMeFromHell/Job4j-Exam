package ru.job4j.daggerjob4j;

import dagger.Component;

@Component(modules = StoreModule.class)
public interface StoreComponent {
    void injectTo(MainActivity activity);
}
