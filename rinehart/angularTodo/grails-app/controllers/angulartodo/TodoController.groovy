package angulartodo

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class TodoController {

    static allowedMethods = [ajaxList: "GET", ajaxSave: "POST", ajaxComplete: "POST"]

    def ajaxList() {
        render Todo.findAll( "from Todo t where t.complete = false order by t.dateCreated asc" ) as JSON
    }
    
    def ajaxSave() {
        def todo = new Todo(
            description : request.JSON.description,
            complete : false
        ).save( failOnError : true )

        ajaxList()
    }
    
    def ajaxComplete() {
        def todo = Todo.get( params.id )
        
        if ( todo )
        {
            todo.complete = true
            todo.save()
        }

        render ""
    }
}
