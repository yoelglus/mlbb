package com.yoelglus.mlbb.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.yoelglus.mlbb.domain.datastore.NotesStore
import com.yoelglus.mlbb.entities.Note
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber
import rx.schedulers.TestScheduler

class `Get Notes Test` {

    private lateinit var getNotes: GetNotes
    private lateinit var notesStore: NotesStore
    private val testSubscriber = TestSubscriber<List<Note>>()
    private val ioScheduler = TestScheduler()

    @Before
    fun setUp() {
        notesStore = mock()
        getNotes = GetNotes(notesStore, ioScheduler)
    }

    @Test
    fun `should return empty list of notes`() {
        givenNotesStoreWillReturn(emptyList())

        getNotes.execute(testSubscriber)
        ioScheduler.triggerActions()

        testSubscriber.assertReceivedOnNext(listOf(emptyList()))
    }

    @Test
    fun `should return the list`() {
        givenNotesStoreWillReturn(listOf(Note("first"), Note("second")))

        getNotes.execute(testSubscriber)
        ioScheduler.triggerActions()

        testSubscriber.assertReceivedOnNext(listOf(listOf(Note("first"), Note("second"))))
    }

    @Test
    fun `should observe on io scheduler`() {
        givenNotesStoreWillReturn(listOf(Note("first"), Note("second")))

        getNotes.execute(testSubscriber)
        testSubscriber.assertNoValues()
        ioScheduler.triggerActions()

        testSubscriber.assertReceivedOnNext(listOf(listOf(Note("first"), Note("second"))))

    }

    private fun givenNotesStoreWillReturn(notes: List<Note>) {
        whenever(notesStore.getNotes()).thenReturn(Observable.just(notes))
    }
}

