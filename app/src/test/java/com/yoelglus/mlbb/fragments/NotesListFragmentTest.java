package com.yoelglus.mlbb.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.widget.ListView;
import android.widget.TextView;
import com.yoelglus.mlbb.dummy.DummyContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml")
public class NotesListFragmentTest {

    private static final int TEST_ITEMS_COUNT = 6;
    private static final int TEST_NOTE_INDEX = 1;
    private NotesListFragmentContainingActivity containingActivity;

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
        ListView notesListView = createFragmentAndGetListView();
        TextView noteTextView = (TextView) notesListView.getChildAt(TEST_NOTE_INDEX);
        assertEquals("Note " + TEST_NOTE_INDEX, noteTextView.getText().toString());
    }

    @Test
    public void testSelectingItemInvokesCallback() throws Exception {
        ListView notesListView = createFragmentAndGetListView();
        TextView noteTextView = (TextView) notesListView.getChildAt(TEST_NOTE_INDEX);
        notesListView.performItemClick(noteTextView, TEST_NOTE_INDEX, 0);
        assertEquals(TEST_NOTE_INDEX, containingActivity.selectedNoteId);
    }

    private ListView createFragmentAndGetListView() {
        createDummyNotesList(TEST_ITEMS_COUNT);
        NotesListFragment notesListFragment = createAndStartNotesListFragment();
        ListView notesListView = notesListFragment.getListView();
        forceListItemsCreation(notesListView);
        return notesListView;
    }

    private void forceListItemsCreation(ListView notesListView) {
        notesListView.layout(0, 0, 100, 1000);
    }

    private NotesListFragment createAndStartNotesListFragment() {
        NotesListFragment notesListFragment = new NotesListFragment();
        containingActivity = createContainingActivity();
        FragmentManager fragmentManager = containingActivity.getFragmentManager();
        fragmentManager.beginTransaction().add(notesListFragment, null).commit();
        return notesListFragment;
    }

    private void createDummyNotesList(int itemsCount) {
        List<DummyContent.DummyNote> notes = new LinkedList<DummyContent.DummyNote>();
        for (int i=0; i<itemsCount; i++)
            notes.add(new DummyContent.DummyNote(i, "Note " + i));
        DummyContent.notes = notes;
    }

    private static NotesListFragmentContainingActivity createContainingActivity() {
        ActivityController<NotesListFragmentContainingActivity> controller =
                Robolectric.buildActivity(NotesListFragmentContainingActivity.class);
        controller.create().start().resume();
        return controller.get();
    }

    private static class NotesListFragmentContainingActivity extends Activity implements NotesListFragment.Callbacks {

        private int selectedNoteId;

        @Override
        public void onNoteSelected(int noteId) {
            selectedNoteId = noteId;
        }
    }
}
