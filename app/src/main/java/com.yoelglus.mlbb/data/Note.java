package com.yoelglus.mlbb.data;

public final class Note {

    private final int id;
    private final String content;

    public Note(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }
}
