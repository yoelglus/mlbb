package com.yoelglus.mlbb.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yoelglus.mlbb.dummy.DummyContent;
import com.yoelglus.mlbb.viewmodels.NotesListViewModel;

public class NotesListFragment extends ListFragment {

    private static Callbacks dummyCallbacks = new Callbacks() {
        @Override
        public void onNoteSelected(int noteId) {

        }
    };

    private Callbacks callbacks = dummyCallbacks;
    private NotesListViewModel notesListViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.notes));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks))
            throw new IllegalStateException(context.getClass().getName() + " must implement NotesListFragment.Callbacks.");
        callbacks = (Callbacks)context;
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

    public interface Callbacks {
        void onNoteSelected(int noteId);
    }
}
