package com.yoelglus.mlbb.viewmodels;

import com.yoelglus.mlbb.data.Note;
import com.yoelglus.mlbb.mvvm.Observable;

import java.util.ArrayList;
import java.util.List;

public class NotesListViewModelStub implements NotesListViewModel {

    private Observable<List<Observable<Note>>> observableNotesList;
    private List<Observable<Note>> notesList;

    public NotesListViewModelStub() {
        buildDummyNotesList();
        // avoid returning the original list so it will be immutable
        List<Observable<Note>> notesListCopy = new ArrayList<>(notesList);
        observableNotesList = new Observable<>(notesListCopy);
    }

    private void buildDummyNotesList() {
        notesList = new ArrayList<>();
        for (int i=0; i<7; i++) {
            notesList.add(new Observable<>(new Note(i, "Note Number " + i)));
        }
    }

    @Override
    public Observable<List<Observable<Note>>> getNotesList() {
        return observableNotesList;
    }
}
