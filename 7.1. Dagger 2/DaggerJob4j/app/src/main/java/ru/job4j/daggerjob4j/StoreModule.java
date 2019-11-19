package ru.job4j.daggerjob4j;

import dagger.Module;
import dagger.Provides;

@Module
public class StoreModule {
    @Provides
    public Store providesStore() {
        return new MemStore();
    }
}
