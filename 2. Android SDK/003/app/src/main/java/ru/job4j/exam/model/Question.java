/**
 * The app demonstrates screen rotation event in Android.
 * @author Rustam Galimov
 * @since 30.10.2019
 * @version 1.0
 */
package ru.job4j.exam.model;

import java.util.List;

public class Question {
    private int id;
    private String text;
    private List<Option> options;
    private int answer;
    private int choose = -1;

    public Question(final int id, final String text, final List<Option> options, final int answer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(final List<Option> options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(final int answer) {
        this.answer = answer;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(final int choose) {
        this.choose = choose;
    }
}
