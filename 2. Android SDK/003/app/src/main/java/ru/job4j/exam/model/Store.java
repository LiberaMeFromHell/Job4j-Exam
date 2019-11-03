/**
 * The app demonstrates screen rotation event in Android.
 * @author Rustam Galimov
 * @since 30.10.2019
 * @version 1.0
 */
package ru.job4j.exam.model;

public interface Store {

    String getQuestionText(int i);

    int getSize();

    int getOptionsID(int questionIndex, int optionIndex);

    String getOptionText(int questionIndex, int optionIndex);

    int getAnswer(int i);

    void setChoose(int questionIndex, int choice);

    int getChoice(int questionIndex);
}
