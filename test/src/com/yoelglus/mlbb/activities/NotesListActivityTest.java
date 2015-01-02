package com.yoelglus.mlbb.activities;

import android.app.Activity;
import android.view.View;
import com.yoelglus.mlbb.R;
import com.yoelglus.mlbb.fragments.NotesListFragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "../AndroidManifest.xml")
public class NotesListActivityTest {

    private Activity notesListActivity;

    @Before
    public void setUp() throws Exception {
        notesListActivity = Robolectric.buildActivity(NotesListActivity.class).create().get();
    }

    @Test
    public void testContainsNotesListFragment() {
        NotesListFragment notesListFragment = (NotesListFragment) notesListActivity.getFragmentManager().findFragmentById(R.id.notes_list);
        assertNotNull(notesListFragment);
    }

    @Test
    public void testNoDetailsContainerInPhone() throws Exception {
        assertNull(getNoteDetailsContainer());
    }

    @Test
    @Config(qualifiers = "sw600dp")
    public void testHasDetailsContainerInTablet() throws Exception {
        assertNotNull(getNoteDetailsContainer());
    }

    private View getNoteDetailsContainer() {
        return notesListActivity.findViewById(R.id.note_details_container);
    }
}
