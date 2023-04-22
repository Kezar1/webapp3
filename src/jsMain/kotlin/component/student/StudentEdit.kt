package component.student

import component.template.EditItemProps
import csstype.ClassName
import react.FC
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.span
import react.useState
import ru.altmanea.webapp.data.Student
import web.html.InputType

val CStudentEdit = FC<EditItemProps<Student>>("StudentEdit") { props ->
    var firstname by useState(props.item.elem.firstname)
    var surname by useState(props.item.elem.surname)
    ReactHTML.table {
        ReactHTML.td {
            input {
                type = InputType.text
                value = firstname
                onChange = { firstname = it.target.value }
            }
        }
        ReactHTML.td {
            input {
                type = InputType.text
                value = surname
                onChange = { surname = it.target.value }
            }
        }
        ReactHTML.td {
            button {
                +"✓"
                onClick = {
                    props.saveElement(Student(firstname, surname, props.item.elem.group))
                }
            }
        }
    }
}
