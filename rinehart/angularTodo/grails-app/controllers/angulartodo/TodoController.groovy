package angulartodo

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class TodoController {

    static allowedMethods = [ajaxList: "GET", ajaxSave: "POST", ajaxComplete: "POST"]

    /**
     * Query for all incomplete Todos and return them as a JSON array
     */
    def ajaxList() {
        render Todo.findAll( "from Todo t where t.complete = false order by t.dateCreated asc" ) as JSON
    }

    /**
     * Get the description from the request's JSON structure, then return
     * the current list of Todos
     */
    def ajaxSave() {
        def todo = new Todo(
            description : request.JSON.description,
            complete : false
        ).save( failOnError : true )

        ajaxList()
    }

    /**
     * Mark whichever Todo desired complete
     */
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
