package com.yoelglus.mlbb.domain

import com.yoelglus.mlbb.domain.datastore.NotesStore
import com.yoelglus.mlbb.entities.Note
import rx.Subscriber

class GetNotes(val notesStore: NotesStore) {
    fun execute(subscriber: Subscriber<List<Note>>) {
        notesStore.getNotes().subscribe(subscriber)
    }

}