package com.yoelglus.mlbb.fragments;

import android.app.Fragment;
import android.os.Bundle;

public class NoteDetailsFragment extends Fragment {
    public static final String ARG_NOTE_ID = "note_id";

    public static NoteDetailsFragment create(int noteId) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_NOTE_ID, noteId);
        NoteDetailsFragment createdFragment = new NoteDetailsFragment();
        createdFragment.setArguments(arguments);
        return createdFragment;
    }
}
