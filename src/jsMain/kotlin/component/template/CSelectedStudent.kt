package component.template

import csstype.*
import emotion.react.css
import emotion.styled.styled
import js.core.get
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import query.QueryError
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.body
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.legend
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr
import react.dom.html.ReactHTML.ul
import react.router.useParams
import ru.altmanea.webapp.common.Item
import ru.altmanea.webapp.config.Config
import ru.altmanea.webapp.data.Student
import tanstack.query.core.QueryKey
import tanstack.react.query.useQuery
import tanstack.react.query.useQueryClient
import tools.fetchText

val CSelectedStudent = FC<Props>() {
    val param = useParams()["name"]

    val query = useQuery<String, QueryError, String, QueryKey>(
        queryKey = arrayOf("editGroupStudent").unsafeCast<QueryKey>(),
        queryFn = {
            fetchText(Config.studentsPath + param)
        }
    )

    val query2 = useQuery<String, QueryError, String, QueryKey>(
        queryKey = arrayOf("allLessonsStudent").unsafeCast<QueryKey>(),
        queryFn = {
            fetchText(Config.studentsPath + param + "/lessons")
        }
    )

    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val item =
            Json.decodeFromString<Item<Student>>(query.data ?: "")

        table {
            className = ClassName("full-name")
            td {
                +item.elem.firstname
            }
            td {
                +item.elem.surname
            }
            td {
                +item.elem.group
            }
        }
        if (query.isLoading) div { +"Loading .." }
        else if (query.isError) div { +"Error!" }
        else {
            val items = Json.decodeFromString<List<String?>>(query2.data ?: "").filterNotNull()

            table {
                className = ClassName("table-lessons")
                if (items.isEmpty()) {
                    tr {
                        td {
                            +"No lessons"
                        }
                    }
                } else

                    +"Lesson"
                items.forEach {
                    tr {
                        ul {
                            li {
                                td {
                                    +it
                                }
                            }

                        }
                    }
                }
            }
            footer {
                div{
                    +"Copyrigth Vlad Kadykov. Group 20z"
                }
            }
        }
    }
}
