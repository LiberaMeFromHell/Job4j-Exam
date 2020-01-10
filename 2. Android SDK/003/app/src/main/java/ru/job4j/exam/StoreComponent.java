package ru.job4j.exam;

import javax.inject.Singleton;

import dagger.Component;
import ru.job4j.exam.model.database.ExamBaseHelper;
import ru.job4j.exam.view.ExamUpdateFragment;
import ru.job4j.exam.view.QuestionFragment;

@Component(modules = {StoreModule.class, ExamBaseHelper.class, App.class})
@Singleton
public interface StoreComponent {
    void inject(QuestionFragment questionFragment);
    void inject(ExamUpdateFragment examUpdateFragment);
}
