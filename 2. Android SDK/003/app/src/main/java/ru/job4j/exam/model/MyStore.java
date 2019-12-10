/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.model;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

public final class MyStore implements Store {

    private List<Question> questions;
    private static MyStore myStore;

    private MyStore() {
        questions = Arrays.asList(
                new Question(
                        1, "How many primitive variables does Java have?",
                        Arrays.asList(
                                new Option(1, "1.1"), new Option(2, "1.2"),
                                new Option(3, "1.3"), new Option(4, "1.4")
                        ), 4
                ),
                new Question(
                        2, "What is Java Virtual Machine?",
                        Arrays.asList(
                                new Option(1, "2.1"), new Option(2, "2.2"),
                                new Option(3, "2.3"), new Option(4, "2.4")
                        ), 4
                ),
                new Question(
                        3, "What is happen if we try unboxing null?",
                        Arrays.asList(
                                new Option(1, "3.1"), new Option(2, "3.2"),
                                new Option(3, "3.3"), new Option(4, "3.4")
                        ), 4
                )
        );
    }

    public static synchronized MyStore getInstance() {
        if (myStore == null) {
            myStore = new MyStore();
        }
        return myStore;
    }

    @Override
    public String getQuestionText(final int i) {
        return questions.get(i).getText();
    }

    @Override
    public int getSize() {
        return questions.size();
    }

    @Override
    public int getOptionsID(final int questionIndex, final int optionIndex) {
        return questions.get(questionIndex).getOptions().get(optionIndex).getId();
    }

    @Override
    public String getOptionText(final int questionIndex, final int optionIndex) {
        return questions.get(questionIndex).getOptions().get(optionIndex).getText();
    }

    @Override
    public int getAnswer(final int i) {
        return questions.get(i).getAnswer();
    }

    @Override
    public void setChoose(final int questionIndex, final int choice) {
        questions.get(questionIndex).setChoose(choice);
        Log.d("tag", "setChoose: " + questions.get(questionIndex).getChoose());
    }

    @Override
    public int getChoice(final int questionIndex) {
        return questions.get(questionIndex).getChoose();
    }
}
