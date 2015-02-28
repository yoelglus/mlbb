package com.yoelglus.mlbb.data;

import java.util.HashMap;
import java.util.Map;

public class InMemoryNotes implements Notes {

    Map<Long, Note> notes = new HashMap<Long, Note>();

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public void addNote(Note note) {
        notes.put(note.getId(), note);
    }

    @Override
    public void removeNote(long noteId) throws NoSuchNoteException {
        if (notes.remove(noteId) == null)
            throw new NoSuchNoteException();
    }

    @Override
    public Note getNote(long noteId) {
        return notes.get(noteId);
    }

}
