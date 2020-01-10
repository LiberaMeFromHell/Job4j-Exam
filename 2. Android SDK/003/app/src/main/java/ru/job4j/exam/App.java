/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.job4j.exam.model.database.ExamBaseHelper;

@Module
public class App extends Application {

    private static StoreComponent storeComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        storeComponent = DaggerStoreComponent.builder()
                .storeModule(new StoreModule())
                .examBaseHelper(new ExamBaseHelper(provideContext()))
                .build();
    }

    public static StoreComponent getStore() {
        return storeComponent;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.getApplicationContext();
    }
}
