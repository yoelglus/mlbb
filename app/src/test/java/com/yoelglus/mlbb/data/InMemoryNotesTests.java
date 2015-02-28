package com.yoelglus.mlbb.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InMemoryNotesTests {

    private static final long DUMMY_NOTE_1_ID = 1;
    private Notes notes;
    private static final Note DUMMY_NOTE_1 = new DummyNoteImpl(DUMMY_NOTE_1_ID);
    private static final long DUMMY_NOTE_2_ID = 2;
    private static final Note DUMMY_NOTE_2 = new DummyNoteImpl(DUMMY_NOTE_2_ID);

    @Before
    public void setUp() throws Exception {
        notes = new InMemoryNotes();
    }

    @Test
    public void testEmptyNotesReturnsSizeZero() throws Exception {
        assertEquals(0, notes.size());
    }

    @Test
    public void testAddingNoteChangesTheSize() throws Exception {
        notes.addNote(DUMMY_NOTE_1);
        assertEquals(1, notes.size());
        notes.addNote(DUMMY_NOTE_2);
        assertEquals(2, notes.size());
    }

    @Test(expected = NoSuchNoteException.class)
    public void testRemoveNoteOnEmptyListThrowsException() throws Exception {
        notes.removeNote(DUMMY_NOTE_1_ID);
    }

    @Test(expected = NoSuchNoteException.class)
    public void testRemoveNoteThatNotExistsThrowsException() throws Exception {
        notes.addNote(DUMMY_NOTE_1);
        notes.removeNote(DUMMY_NOTE_1_ID);
        notes.removeNote(DUMMY_NOTE_1_ID);
    }

    @Test
    public void testRemoveNotesChangesSize() throws Exception {
        notes.addNote(DUMMY_NOTE_1);
        assertEquals(1, notes.size());
        notes.removeNote(DUMMY_NOTE_1_ID);
        assertEquals(0, notes.size());
        notes.addNote(DUMMY_NOTE_1);
        notes.addNote(DUMMY_NOTE_2);
        notes.removeNote(DUMMY_NOTE_1_ID);
        assertEquals(1, notes.size());
    }

    @Test
    public void testGetNoteReturnsNullWhenNotesIsEmpty() throws Exception {
        assertNull(notes.getNote(DUMMY_NOTE_1_ID));
        assertNull(notes.getNote(DUMMY_NOTE_2_ID));
    }

    @Test
    public void testGetNoteReturnsAddedNote() throws Exception {
        notes.addNote(DUMMY_NOTE_1);
        notes.addNote(DUMMY_NOTE_2);
        assertEquals(DUMMY_NOTE_1, notes.getNote(DUMMY_NOTE_1_ID));
        assertEquals(DUMMY_NOTE_2, notes.getNote(DUMMY_NOTE_2_ID));
    }

    @Test
    public void testGetNoteReturnsNullOnDeletedNote() throws Exception {
        notes.addNote(DUMMY_NOTE_1);
        notes.removeNote(DUMMY_NOTE_1_ID);
        assertNull(notes.getNote(DUMMY_NOTE_1_ID));
    }


    private static class DummyNoteImpl implements Note {

        private long id;

        private DummyNoteImpl(long id) {
            this.id = id;
        }

        @Override
        public long getId() {
            return id;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Note))
                return false;
            Note otherNote = (Note)other;
            return this.id == otherNote.getId();
        }
    }

}
