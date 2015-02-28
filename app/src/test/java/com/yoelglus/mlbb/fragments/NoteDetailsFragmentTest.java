package com.yoelglus.mlbb.fragments;

import android.widget.TextView;
import com.yoelglus.mlbb.R;
import com.yoelglus.mlbb.dummy.DummyContent;
import org.apache.tools.ant.taskdefs.condition.Not;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.robolectric.util.FragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
public class NoteDetailsFragmentTest {

    private static final int TEST_NOTE_ID = 2;

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionIfNoNoteId() throws Exception {
        NoteDetailsFragment noteDetailsFragment = new NoteDetailsFragment();
        startFragment(noteDetailsFragment);
    }

    @Test
    public void testCreateWithNoteId() throws Exception {
        NoteDetailsFragment noteDetailsFragment = NoteDetailsFragment.create(TEST_NOTE_ID);
        startFragment(noteDetailsFragment);
        assertEquals(TEST_NOTE_ID, noteDetailsFragment.getNoteId());
    }

    @Test
    public void testShowsNoteContent() throws Exception {
        NoteDetailsFragment noteDetailsFragment = NoteDetailsFragment.create(TEST_NOTE_ID);
        startFragment(noteDetailsFragment);
        TextView noteContentTextView = (TextView) noteDetailsFragment.getView().findViewById(R.id.note_content);
        assertEquals(DummyContent.notesMap.get(TEST_NOTE_ID).content, noteContentTextView.getText().toString());
    }
}
