package component.student

import component.template.EditAddProps
import csstype.ClassName
import react.FC
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.span
import react.useState
import ru.altmanea.webapp.common.Item
import ru.altmanea.webapp.data.Student
import web.html.InputType

val CStudentAdd = FC<EditAddProps<Student>>("StudentAdd") { props ->
    var firstname by useState("")
    var surname by useState("")
    var group by useState("")
    ReactHTML.table {
        ReactHTML.tr {
            ReactHTML.td { +"Имя" }
            ReactHTML.td {
                input {
                    type = InputType.text
                    value = firstname
                    onChange = { firstname = it.target.value }
                }
            }
        }
        ReactHTML.tr {
            ReactHTML.td { +"Фамилия" }
            ReactHTML.td {
                input {
                    type = InputType.text
                    value = surname
                    onChange = { surname = it.target.value }
                }
            }
        }
        ReactHTML.tr {
            ReactHTML.td { +"Группа" }
            ReactHTML.td {
                input {
                    type = InputType.text
                    value = group
                    onChange = { group = it.target.value }
                }
            }
            ReactHTML.td {
                button {
                    +"✓"
                    onClick = {
                        props.saveElement(Student(firstname, surname, group))
                    }
                }
            }
        }
        ReactHTML.tr {

        }
    }
}
