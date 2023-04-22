package component.student

import component.template.ElementInListProps
import csstype.ClassName
import csstype.attr
import csstype.ident
import react.FC
import react.dom.html.ReactHTML.span
import react.router.dom.Link
import react.useId
import ru.altmanea.webapp.common.Item
import ru.altmanea.webapp.data.Student
import web.html.HTMLElement
import web.html.HtmlTagName


val CStudentInList = FC<ElementInListProps<Item<Student>>>("StudentInList") { props ->
    span{
        Link {
            +props.element.elem.fullname()
            to = props.element.id
        }
    }
}