Ext.define( 'ExtCoffeeTodo.store.TodoStore',
	extend: 'Ext.data.Store'
	requires: [ 'ExtCoffeeTodo.model.Todo' ]


	# Store interacts with remote API
	constructor: ->
		config =
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

		@callParent( [ config ] )


	onUpdateRecords: ->
		@filterBy( @completedFilter )


	onCreateRecords: ->
		@filterBy( @completedFilter )
		@resort()


	_showCompleted: false

	showCompleted: ( flag = @_showCompleted ) ->
		@_showCompleted = flag
		@filterBy( @completedFilter )


	completedFilter: ( record ) ->
		if @_showCompleted then return true else return ( not record.get( 'complete' ) )


	resort: ->
		@sort(
			property : @sorters.get( 0 ).property
			direction: @sorters.get( 0 ).direction
		)

)