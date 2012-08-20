package extcoffeetodo

import grails.converters.JSON

class TodoController {

	static allowedMethods = [ajaxList: "GET", ajaxSave: "POST", ajaxComplete: "POST"]

	def ajaxList() {
		render Todo.findAll( "from Todo t order by t.dateCreated desc" ) as JSON
	}

	def ajaxCreate() {
		def todo = new Todo(
				description : request.JSON.description,
				complete : request.JSON.complete
		).save( failOnError : true )

		render todo as JSON
	}

	def ajaxSave() {
		def todo = Todo.get( request.JSON.id )

		if ( todo )
		{
			todo.description = request.JSON.description
			todo.complete = request.JSON.complete
			todo.save()
			render todo as JSON
		}

	}
}
