Ext.define "ExtCoffeeTodo.view.Viewport",
  extend: "Ext.container.Viewport"
  requires: [ "ExtCoffeeTodo.view.TodoPanel" ]
  renderTo: Ext.getBody()
  layout: "fit"

  initComponent: ->

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