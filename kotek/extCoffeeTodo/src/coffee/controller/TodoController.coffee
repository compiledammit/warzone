Ext.define( 'ExtCoffeeTodo.controller.TodoController',
	extend: 'Deft.mvc.ViewController'
	inject: [ 'todoStore' ]

	#control:
	#grid : true
	#submitButton: 'panel > button[text="Submit"]'
	#view:
	#  show: 'related view is set automatically even if no listeners are attached.'

	#config:
	#valueToCreateSetterFor_EspeciallyInjections: null


	init: ->
		@callParent( arguments)

)



