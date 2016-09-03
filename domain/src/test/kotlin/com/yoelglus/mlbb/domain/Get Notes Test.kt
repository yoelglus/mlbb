package com.yoelglus.mlbb.domain

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.yoelglus.mlbb.domain.datastore.NotesStore
import com.yoelglus.mlbb.entities.Note
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber

class `Get Notes Test` {

    @Test
    fun `should return empty list of notes`() {
        val notesStore = mock<NotesStore>() {
            on { getNotes() } doReturn Observable.just(emptyList())
        }
        val subscriber = TestSubscriber<List<Note>>()

        val getNotes = GetNotes(notesStore)
        getNotes.execute(subscriber)

        assertThat(subscriber.onNextEvents[0], `is`(emptyList()))
    }
}

