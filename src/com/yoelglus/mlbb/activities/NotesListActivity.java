package com.yoelglus.mlbb.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import com.yoelglus.mlbb.R;
import com.yoelglus.mlbb.fragments.NoteDetailsFragment;
import com.yoelglus.mlbb.fragments.NotesListFragment;

public class NotesListActivity extends Activity implements NotesListFragment.Callbacks {

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        twoPane = findViewById(R.id.note_details_container) != null;
        setUpChoiceModeInList();
    }

    private void setUpChoiceModeInList() {
        if (twoPane) {
            NotesListFragment notesListFragment = (NotesListFragment) getFragmentManager().findFragmentById(R.id.notes_list);
            notesListFragment.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onNoteSelected(int noteId) {
        if (twoPane) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.note_details_container, NoteDetailsFragment.create(noteId)).commit();
        } else {
            Intent noteDetailsIntent = new Intent(this, NoteDetailsActivity.class);
            noteDetailsIntent.putExtra(NoteDetailsFragment.ARG_NOTE_ID, noteId);
            startActivity(noteDetailsIntent);
        }
    }
}
