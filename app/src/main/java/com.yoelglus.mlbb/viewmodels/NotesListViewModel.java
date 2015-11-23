package com.yoelglus.mlbb.viewmodels;

import com.yoelglus.mlbb.data.Note;
import com.yoelglus.mlbb.mvvm.Observable;

import java.util.List;

public interface NotesListViewModel {
    Observable<List<Observable<Note>>> getNotesList();
}
