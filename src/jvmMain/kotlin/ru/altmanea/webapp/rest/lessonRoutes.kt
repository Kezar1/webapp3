package ru.altmanea.webapp.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.altmanea.webapp.config.Config
import ru.altmanea.webapp.repo.lessonsRepo
import ru.altmanea.webapp.repo.studentsRepo
import ru.altmanea.webapp.repo.teachersRepo

fun Route.lessonRoutes() {
    route(Config.lessonsPath) {
        repoRoutes(lessonsRepo)

        get("{idL}/students/{idS}") {
            val idL = call.parameters["idL"]
                ?: return@get call.respondText(
                    "Missing or malformed lesson id",
                    status = HttpStatusCode.BadRequest
                )
            val lesson = lessonsRepo.read(listOf(idL)).getOrNull(0)
                ?: return@get call.respondText(
                    "No lesson with id $idL",
                    status = HttpStatusCode.NotFound
                )
            val idS = call.parameters["idS"] ?: return@get call.respondText(
                "Missing or malformed student id",
                status = HttpStatusCode.BadRequest
            )
            studentsRepo.read(listOf(idS)).getOrNull(0)
                ?: return@get call.respondText(
                    "No student with id $idS",
                    status = HttpStatusCode.NotFound
                )
            if (lesson.elem.students.find { it.studentId == idS } != null)
                return@get call.respondText(
                    "Student already in lesson",
                    status = HttpStatusCode.BadRequest
                )

            val newLesson = lesson.elem.addStudent(idS)
            lessonsRepo.update(lesson.id, newLesson)

            call.respondText(
                "Student added correctly",
                status = HttpStatusCode.OK
            )
        }
        get("{idL}/teachers/{idT}") {
            val idL = call.parameters["idL"]
                ?: return@get call.respondText(
                    "Missing or malformed lesson id",
                    status = HttpStatusCode.BadRequest
                )
            val lesson = lessonsRepo.read(listOf(idL)).getOrNull(0)
                ?: return@get call.respondText(
                    "No lesson with id $idL",
                    status = HttpStatusCode.NotFound
                )
            val idT = call.parameters["idT"] ?: return@get call.respondText(
                "Missing or malformed teacher id",
                status = HttpStatusCode.BadRequest
            )
            teachersRepo.read(listOf(idT)).getOrNull(0)
                ?: return@get call.respondText(
                    "No teacher with id $idT",
                    status = HttpStatusCode.NotFound
                )

            val newLesson = lesson.elem.addTeacher(idT)
            lessonsRepo.update(lesson.id, newLesson)

            call.respondText(
                "Teacher added correctly",
                status = HttpStatusCode.OK
            )

        }
    }
}
