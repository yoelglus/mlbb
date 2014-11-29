package com.yoelglus.mlbb.models;

/**
 * Created by Yoel Gluschnaider on 29/11/14.
 * Represents a single note
 */
public class Note {

    private String mText;

    public Note() {
        this(null);
    }

    public Note(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }
}
