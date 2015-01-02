package com.yoelglus.mlbb.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.yoelglus.mlbb.R;
import com.yoelglus.mlbb.fragments.NotesListFragment;

public class NotesListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        setUpChoiceModeInList();
    }

    private void setUpChoiceModeInList() {
        if (findViewById(R.id.note_details_container) != null)
        {
            NotesListFragment notesListFragment = (NotesListFragment) getFragmentManager().findFragmentById(R.id.notes_list);
            notesListFragment.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }
}
