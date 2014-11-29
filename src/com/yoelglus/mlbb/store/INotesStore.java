package com.yoelglus.mlbb.store;

import com.yoelglus.mlbb.models.Note;

/**
 * Created by Yoel Gluschnaider on 29/11/14.
 */
public interface INotesStore {
    int saveNote(Note note);
    Note loadNote(int noteId);
}
