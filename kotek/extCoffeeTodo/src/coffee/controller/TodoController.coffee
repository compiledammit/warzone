Ext.define( "ExtCoffeeTodo.controller.TodoController",
	extend: "Deft.mvc.ViewController"
	requires: [ "ExtCoffeeTodo.store.TodoStore" ]
	inject: [ "todoStore" ]

	# Handle view events
	control:
		showCompletedCheckbox:
			change: "toggleShowCompleted"
		completeColumn:
			checkchange: "syncTodoStore"
		view:
			edit: "syncTodoStore"
		addButton:
			click: "addNewTodo"

	config:
		todoStore: null


	init: ->
		@callParent( arguments )


	addNewTodo: ->
		newTodo = Ext.create( "ExtCoffeeTodo.model.Todo",
			complete: false
		)
		@getTodoStore().insert( 0, newTodo )
		@getView().cellEditing.startEditByPosition(
			row: 0
			column: 0
		)


	toggleShowCompleted: ( field, value ) ->
		@getTodoStore().showCompleted( value )


	syncTodoStore: ->
		@getTodoStore().sync()
		@getView().getSelectionModel().deselectAll()

)



