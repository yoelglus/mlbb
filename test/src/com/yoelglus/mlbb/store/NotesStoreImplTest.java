package com.yoelglus.mlbb.store;

import com.yoelglus.mlbb.models.Note;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
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
    public void testSaveNotesWithText()
    {
        Note note1 = new Note("This is note 1");
        Note note2 = new Note("This is note 2");
        Note note3 = new Note("This is note 1");
        int note1Id = mNoteStore.saveNote(note1);
        int note2Id = mNoteStore.saveNote(note2);
        int note3Id = mNoteStore.saveNote(note3);
        assertThat(mNoteStore.loadNote(note1Id), equalTo(note1));
        assertThat(mNoteStore.loadNote(note2Id), equalTo(note2));
        assertThat(mNoteStore.loadNote(note3Id), equalTo(note3));
        assertThat(mNoteStore.loadNote(note1Id), not(equalTo(note3)));
    }
}