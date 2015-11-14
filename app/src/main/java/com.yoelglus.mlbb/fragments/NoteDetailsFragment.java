package com.yoelglus.mlbb.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yoelglus.mlbb.R;
import com.yoelglus.mlbb.dummy.DummyContent;

public class NoteDetailsFragment extends Fragment {
    public static final String ARG_NOTE_ID = "note_id";
    private int noteId;

    public static NoteDetailsFragment create(int noteId) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_NOTE_ID, noteId);
        NoteDetailsFragment createdFragment = new NoteDetailsFragment();
        createdFragment.setArguments(arguments);
        return createdFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null || !getArguments().containsKey(NoteDetailsFragment.ARG_NOTE_ID))
            throw new IllegalArgumentException("Arguments should contain note ID!");
        noteId = getArguments().getInt(NoteDetailsFragment.ARG_NOTE_ID, -1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note_details, container, false);
        TextView contentView = (TextView) rootView.findViewById(R.id.note_content);
        contentView.setText(DummyContent.notesMap.get(noteId).content);
        return rootView;
    }
}
