package com.yoelglus.mlbb.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoteDetailsFragment extends Fragment {
    public static final String ARG_NOTE_ID = "note_id";

    public static NoteDetailsFragment create(int noteId) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_NOTE_ID, noteId);
        NoteDetailsFragment createdFragment = new NoteDetailsFragment();
        createdFragment.setArguments(arguments);
        return createdFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new View(getActivity());
    }
}
