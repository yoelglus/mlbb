package com.yoelglus.mlbb.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.yoelglus.mlbb.domain.datastore.NotesStore
import com.yoelglus.mlbb.entities.Note
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
    private val mainScheduler = TestScheduler()

    @Before
    fun setUp() {
        notesStore = mock()
        getNotes = GetNotes(notesStore, ioScheduler, mainScheduler)
    }

    @Test
    fun `should return empty list of notes`() {
        givenNotesStoreWillReturn(emptyList())

        executeAndTriggerSchdulers()

        testSubscriber.assertReceivedOnNext(listOf(emptyList()))
    }

    @Test
    fun `should return the list`() {
        givenNotesStoreWillReturn(listOf(Note("first"), Note("second")))

        executeAndTriggerSchdulers()

        testSubscriber.assertReceivedOnNext(listOf(listOf(Note("first"), Note("second"))))
    }

    @Test
    fun `should subscribe on io scheduler`() {
        givenNotesStoreWillReturn(listOf(Note("first"), Note("second")))

        getNotes.execute(testSubscriber)
        testSubscriber.assertNoValues()
        ioScheduler.triggerActions()
        mainScheduler.triggerActions()

        testSubscriber.assertReceivedOnNext(listOf(listOf(Note("first"), Note("second"))))
    }

    @Test
    fun `should observe on main scheduler`() {
        givenNotesStoreWillReturn(listOf(Note("first"), Note("second")))

        getNotes.execute(testSubscriber)
        ioScheduler.triggerActions()
        testSubscriber.assertNoValues()
        mainScheduler.triggerActions()

        testSubscriber.assertReceivedOnNext(listOf(listOf(Note("first"), Note("second"))))
    }

    @Test
    fun `should unsubscribe from data store`() {
        givenNotesStoreWillReturn(listOf(Note("first"), Note("second")))

        val subscription = getNotes.execute(testSubscriber)
        subscription.unsubscribe()

        testSubscriber.assertUnsubscribed()
    }

    private fun executeAndTriggerSchdulers() {
        getNotes.execute(testSubscriber)
        ioScheduler.triggerActions()
        mainScheduler.triggerActions()
    }

    private fun givenNotesStoreWillReturn(notes: List<Note>) {
        whenever(notesStore.getNotes()).thenReturn(Observable.just(notes))
    }
}

