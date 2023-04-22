package ru.altmanea.webapp.common

import kotlinx.serialization.Serializable
import ru.altmanea.webapp.data.LessonId
import ru.altmanea.webapp.data.StudentId

typealias ItemId = String

@Serializable
class Item<E>(
    val elem: E,
    val id: ItemId,
)
