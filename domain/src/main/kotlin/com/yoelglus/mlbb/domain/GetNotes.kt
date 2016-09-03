package com.yoelglus.mlbb.domain

import com.yoelglus.mlbb.domain.datastore.NotesStore
import com.yoelglus.mlbb.entities.Note
import rx.Scheduler
import rx.Subscriber

class GetNotes(val notesStore: NotesStore, val ioScheduler: Scheduler, val mainScheduler: Scheduler) {
    fun execute(subscriber: Subscriber<List<Note>>) {
        notesStore.getNotes().observeOn(ioScheduler).subscribeOn(mainScheduler).subscribe(subscriber)
    }

}