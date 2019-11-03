/**
 * The app demonstrates screen rotation event in Android.
 * @author Rustam Galimov
 * @since 30.10.2019
 * @version 1.0
 */
package ru.job4j.exam.model;


public class Option {
    private int id;
    private String text;

    public Option(final int id, final String text) {
        this.id = id;
        this.text = text;
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
}

