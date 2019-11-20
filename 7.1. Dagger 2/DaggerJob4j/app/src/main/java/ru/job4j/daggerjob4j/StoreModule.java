package ru.job4j.daggerjob4j;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StoreModule {
    @Singleton
    @Provides
    public Store providesStore() {
        return new MemStore();
    }
}
