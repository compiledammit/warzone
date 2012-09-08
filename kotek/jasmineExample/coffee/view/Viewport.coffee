Ext.define "JasmineExample.view.Viewport",
  extend: "Ext.container.Viewport"
  requires: [ "JasmineExample.view.MainTabPanel" ]
  renderTo: Ext.getBody()
  layout: "fit"

  initComponent: ->

    Ext.applyIf( @,
      items: [
        xtype: "jasmineExample-view-mainTabPanel"
      ]
    )

    @callParent( arguments )