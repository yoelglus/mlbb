package com.yoelglus.mlbb.domain.datastore

import com.yoelglus.mlbb.entities.Note
import rx.Observable

interface NotesStore {
    fun getNotes(): Observable<List<Note>>
}