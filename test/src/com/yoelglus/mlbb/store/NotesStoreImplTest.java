package com.yoelglus.mlbb.store;

import com.yoelglus.mlbb.models.Note;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class NotesStoreImplTest {

    private INotesStore mNoteStore;

    @Before
    public void setUp() throws Exception {
        mNoteStore = new NotesStoreImpl();
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test(expected = NullPointerException.class)
    public void testSaveNullNote() throws Exception {
        mNoteStore.saveNote(null);
    }

    @Test
    public void testSaveNoteWithoutText()
    {
        Note emptyNote = new Note();
        int noteId = mNoteStore.saveNote(emptyNote);
        assertThat(mNoteStore.loadNote(noteId), equalTo(emptyNote));
    }

    @Test
    public void testLoadNote() throws Exception {

    }
}