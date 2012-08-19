Ext.define 'ExtCoffeeTodo.view.TodoPanel',
	extend: 'Ext.grid.Panel'
	alias: 'widget.extcoffeetodo-view-todoPanel'
	requires: 'Ext.ux.CheckColumn'
	inject: [ 'todoStore' ]
	controller: 'ExtCoffeeTodo.controller.TodoController'
	layout: 'anchor'

	cellEditing: Ext.create( 'Ext.grid.plugin.CellEditing',
		clicksToEdit: 1
	)

	initComponent: ->

		Ext.applyIf( @,

			store: @todoStore
			emptyText: "There are no Todos yet."

			columns: [
				header: "Todo Description"
				dataIndex: "description"
				flex: 2
				editor:
					allowBlank: false
			,
				xtype: "checkcolumn"
				header: "Complete"
				dataIndex: "complete"
				stopSelection: false
				listeners:
					checkchange: ( column, rowIndex, checked, opts ) =>
						@todoStore.sync()
			]

			listeners:
				edit: ( editor, event ) =>
					@todoStore.sync()

			selModel:
				selType: "cellmodel"

			frame: true

			tbar: [
				text: "Add Todo"
				handler: =>
					newTodo = Ext.create( "ExtCoffeeTodo.model.Todo",
						description: "New Todo"
						complete: false
					)
					@todoStore.insert( 0, newTodo )
					@cellEditing.startEditByPosition(
						row: 0
						column: 0
					)
			,
				'->'
			,
				xtype: "checkbox"
				boxLabel: "Show Completed?"
				boxLabelCls: "toolbar-box-label"
				margin: "0 10 10 0"
				listeners:
					change: ( field, value ) =>
						@todoStore.showCompleted( value )
			]

			plugins: [
				@cellEditing
			]

		)

		@callParent( arguments )