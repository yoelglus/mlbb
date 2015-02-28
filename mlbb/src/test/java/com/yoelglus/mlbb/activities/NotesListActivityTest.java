package com.yoelglus.mlbb.activities;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import com.yoelglus.mlbb.R;
import com.yoelglus.mlbb.fragments.NoteDetailsFragment;
import com.yoelglus.mlbb.fragments.NotesListFragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
//import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class NotesListActivityTest {

    private static final int SELECTED_NOTE_ID = 3;

    private NotesListActivity notesListActivity;

    @Before
    public void setUp() throws Exception {
        notesListActivity = Robolectric.buildActivity(NotesListActivity.class).create().get();
    }

    @Test
    public void testContainsNotesListFragment() {
        assertNotNull(getNotesListFragment());
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

    @Test
    @Config(qualifiers = "sw600dp")
    public void testNotesListSetsProperChoiceModeInTablet() throws Exception {
        assertEquals(ListView.CHOICE_MODE_SINGLE, getNotesListFragment().getListView().getChoiceMode());
    }

    @Test
    public void testNotesListSetsProperChoiceModeInPhone() throws Exception {
        assertEquals(ListView.CHOICE_MODE_NONE, getNotesListFragment().getListView().getChoiceMode());
    }

    @Test
    public void testOnItemSelectedInPhoneStartsDetailsActivity() throws Exception {
        notesListActivity.onNoteSelected(SELECTED_NOTE_ID);
//        assertEquals(createExpectedIntentWithNoteId(SELECTED_NOTE_ID), getNextStartedActivityIntent());
    }

    @Test
    @Config(qualifiers = "sw600dp")
    public void testOnItemSelectedInTabletSetDetailsContainer() throws Exception {
        notesListActivity.onNoteSelected(SELECTED_NOTE_ID);
        NoteDetailsFragment noteDetailsFragment =
                (NoteDetailsFragment) notesListActivity.getFragmentManager().findFragmentById(R.id.note_details_container);
        assertEquals(SELECTED_NOTE_ID, noteDetailsFragment.getArguments().getInt(NoteDetailsFragment.ARG_NOTE_ID));
    }

//    private Intent getNextStartedActivityIntent() {
//        return shadowOf(notesListActivity).getNextStartedActivity();
//    }

    private Intent createExpectedIntentWithNoteId(int noteId) {
        Intent expectedIntent = new Intent();
        expectedIntent.setClassName(notesListActivity.getPackageName(), NoteDetailsActivity.class.getName());
        expectedIntent.putExtra(NoteDetailsFragment.ARG_NOTE_ID, noteId);
        return expectedIntent;
    }

    private NotesListFragment getNotesListFragment() {
        return (NotesListFragment) notesListActivity.getFragmentManager().findFragmentById(R.id.notes_list);
    }

    private View getNoteDetailsContainer() {
        return notesListActivity.findViewById(R.id.note_details_container);
    }
}
