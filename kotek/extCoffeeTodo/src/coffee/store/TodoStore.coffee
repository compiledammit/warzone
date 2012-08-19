Ext.define( 'ExtCoffeeTodo.store.TodoStore',
	extend: 'Ext.data.Store'
	requires: [ 'ExtCoffeeTodo.model.Todo' ]

	constructor: ( cfg ) ->
		cfg = cfg or {}

		@callParent(
			[
				Ext.apply(
					autoLoad: true
					model: 'ExtCoffeeTodo.model.Todo'
					proxy:
						type: 'ajax'
						api:
							create  : 'todo/ajaxCreate',
							read    : 'todo/ajaxList',
							update  : 'todo/ajaxSave',
						reader:
							type: 'json'
					sorters: [
						property: 'dateCreated'
						direction: 'DESC'
					]
					filters: [ @completedFilter ]
				,
					cfg )
			]
		)


	onUpdateRecords: ->
		@callParent( arguments )
		@filterBy( @completedFilter )


	onCreateRecords: ->
		@callParent( arguments )
		@filterBy( @completedFilter )


	_showCompleted: false


	showCompleted: ( flag = @_showCompleted ) ->
		@_showCompleted = flag
		@filterBy( @completedFilter )


	completedFilter: ( record ) ->
		if @_showCompleted then return true else return ( not record.get( 'complete' ) )

)