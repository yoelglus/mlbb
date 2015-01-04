package com.yoelglus.mlbb.activities;

import android.app.Activity;
import android.os.Bundle;
import com.yoelglus.mlbb.R;
import com.yoelglus.mlbb.fragments.NoteDetailsFragment;

public class NoteDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        NoteDetailsFragment noteDetailsFragment =
                NoteDetailsFragment.create(getIntent().getIntExtra(NoteDetailsFragment.ARG_NOTE_ID, -1));
        getFragmentManager().beginTransaction().replace(R.id.note_details_container, noteDetailsFragment).commit();
    }
}
