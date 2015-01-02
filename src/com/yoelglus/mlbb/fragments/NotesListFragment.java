package com.yoelglus.mlbb.fragments;

import android.app.ListFragment;

public class NotesListFragment extends ListFragment {

    public static interface Callbacks {
        void onNoteSelected(int noteId);
    }

}
