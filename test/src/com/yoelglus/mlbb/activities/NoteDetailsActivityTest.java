package com.yoelglus.mlbb.activities;

import android.content.Intent;
import com.yoelglus.mlbb.R;
import com.yoelglus.mlbb.fragments.NoteDetailsFragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "../AndroidManifest.xml")
public class NoteDetailsActivityTest {

    private static final int TEST_NOTE_ID = 2;

    @Test
    public void testTestNoteDetailsFragmentExists() throws Exception {
        assertNotNull(createActivityAndGetNoteDetailsFragment(TEST_NOTE_ID));
    }

    @Test
    public void testCreateWithNoteIdSetsFragmentNoteId() throws Exception {
        NoteDetailsFragment noteDetailsFragment = createActivityAndGetNoteDetailsFragment(TEST_NOTE_ID);
        int actualNoteId = noteDetailsFragment.getArguments().getInt(NoteDetailsFragment.ARG_NOTE_ID);
        assertEquals(actualNoteId, TEST_NOTE_ID);
    }

    private NoteDetailsFragment createActivityAndGetNoteDetailsFragment(int testNoteId) {
        Intent intent = new Intent().putExtra(NoteDetailsFragment.ARG_NOTE_ID, testNoteId);
        NoteDetailsActivity noteDetailsActivity = Robolectric.buildActivity(NoteDetailsActivity.class).withIntent(intent).create().get();
        return (NoteDetailsFragment) noteDetailsActivity.getFragmentManager().findFragmentById(R.id.note_details_container);
    }

}
