Ext.define "ExtCoffeeTodo.view.Viewport",
	extend: "Ext.container.Viewport"
	requires: [ "ExtCoffeeTodo.view.TodoPanel" ]

	initComponent: ->

		# Copy configuration onto this Component.
		# This can be expanded to allow great flexibility in how components are
		# configured (by passing in dynamic configuration objects).
		Ext.applyIf( @,
			items: [
				xtype: "container"
				layout:
					type: 'hbox'
					pack: 'center'
					padding: 10
				items: [
					xtype: "extcoffeetodo-view-todoPanel"
					width: 650
				]
			]
		)

		@callParent( arguments )