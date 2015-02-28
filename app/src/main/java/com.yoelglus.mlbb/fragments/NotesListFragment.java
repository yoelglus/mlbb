package com.yoelglus.mlbb.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.yoelglus.mlbb.dummy.DummyContent;

public class NotesListFragment extends ListFragment {

    public static interface Callbacks {
        void onNoteSelected(int noteId);
    }

    private static Callbacks dummyCallbacks = new Callbacks() {
        @Override
        public void onNoteSelected(int noteId) {

        }
    };

    private Callbacks callbacks = dummyCallbacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<DummyContent.DummyNote>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.notes));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks))
            throw new IllegalStateException(activity.getClass().getName() + " must implement NotesListFragment.Callbacks.");
        callbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = dummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        callbacks.onNoteSelected(DummyContent.notes.get(position).id);
    }
}
