package nl.wesselbarten.newsapp.util.event

/**
 * An [Event] without content.
 */
object EmptyEvent : Event<Unit>(Unit)