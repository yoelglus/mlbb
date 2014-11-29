package com.yoelglus.mlbb.store;

import com.yoelglus.mlbb.models.Note;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yoel Gluschnaider on 29/11/14.
 * Notes store
 */
public class NotesStoreImpl implements INotesStore {

    private int mCurrentId = 0;
    private final Map<Integer, Note> mNotes;

    public NotesStoreImpl()
    {
        mNotes = new HashMap<Integer, Note>();
    }

    @Override
    public int saveNote(Note note) {
        if (note == null)
            throw new NullPointerException("Note shouldn't be null");
        int id = mCurrentId++;
        mNotes.put(id, note);
        return id;
    }

    @Override
    public Note loadNote(int noteId) {
        return mNotes.get(noteId);
    }
}
