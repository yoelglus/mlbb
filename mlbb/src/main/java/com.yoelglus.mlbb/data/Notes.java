package com.yoelglus.mlbb.data;

public interface Notes {
    int size();

    void addNote(Note note);

    void removeNote(long noteId) throws NoSuchNoteException;

    Note getNote(long noteId);
}
