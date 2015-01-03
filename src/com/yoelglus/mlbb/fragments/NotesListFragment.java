package com.yoelglus.mlbb.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.yoelglus.mlbb.dummy.DummyContent;

public class NotesListFragment extends ListFragment {

    public static interface Callbacks {
        void onNoteSelected(int noteId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<DummyContent.DummyNote>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.notes));
    }
}
