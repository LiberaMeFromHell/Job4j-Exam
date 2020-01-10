package ru.job4j.exam;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.job4j.exam.model.MemStore;
import ru.job4j.exam.model.Store;

@Module
public class StoreModule {

    @Provides
    @Singleton
    public Store providesStore() {
        return new MemStore();
    }
}
