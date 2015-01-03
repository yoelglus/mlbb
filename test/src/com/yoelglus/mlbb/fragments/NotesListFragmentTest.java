package com.yoelglus.mlbb.fragments;

import android.widget.ListView;
import android.widget.TextView;
import com.yoelglus.mlbb.dummy.DummyContent;
import com.yoelglus.mlbb.fragments.NotesListFragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.robolectric.util.FragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "../AndroidManifest.xml")
public class NotesListFragmentTest {

    private static final int TEST_ITEMS_COUNT = 6;
    private static final int TEST_NOTE_INDEX = 1;

    @Test
    public void testEmptyListCreatesEmptyAdapter() throws Exception {
        createDummyNotesList(0);
        NotesListFragment notesListFragment = createAndStartNotesListFragment();
        assertEquals(0, notesListFragment.getListAdapter().getCount());
    }

    @Test
    public void testAdapterCreatedWithItems() throws Exception {
        createDummyNotesList(TEST_ITEMS_COUNT);
        NotesListFragment notesListFragment = createAndStartNotesListFragment();
        assertEquals(TEST_ITEMS_COUNT, notesListFragment.getListAdapter().getCount());
    }

    @Test
    public void testListItemViewsContent() throws Exception {
        createDummyNotesList(TEST_ITEMS_COUNT);
        NotesListFragment notesListFragment = createAndStartNotesListFragment();
        ListView notesListView = notesListFragment.getListView();
        forceListItemsCreation(notesListView);
        TextView noteTextView = (TextView) notesListView.getChildAt(TEST_NOTE_INDEX);
        assertEquals("Note " + TEST_NOTE_INDEX, noteTextView.getText().toString());
    }

    private void forceListItemsCreation(ListView notesListView) {
        notesListView.layout(0, 0, 100, 1000);
    }

    private NotesListFragment createAndStartNotesListFragment() {
        NotesListFragment notesListFragment = new NotesListFragment();
        startFragment(notesListFragment);
        return notesListFragment;
    }

    private void createDummyNotesList(int itemsCount) {
        List<DummyContent.DummyNote> notes = new LinkedList<DummyContent.DummyNote>();
        for (int i=0; i<itemsCount; i++)
            notes.add(new DummyContent.DummyNote(i, "Note " + i));
        DummyContent.notes = notes;
    }
}
