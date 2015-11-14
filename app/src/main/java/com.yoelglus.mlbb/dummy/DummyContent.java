package com.yoelglus.mlbb.dummy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DummyContent {

    public static List<DummyNote> notes = new LinkedList<>();

    public static Map<Integer, DummyNote> notesMap = new HashMap<>();

    static {
        for (int i=1; i<7; i++)
            addNote(new DummyNote(i, "Note " + i));
    }

    private static void addNote(DummyNote note) {
        notes.add(note);
        notesMap.put(note.id, note);
    }

    public static class DummyNote {
        public int id;
        public String content;
        public DummyNote(int id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
