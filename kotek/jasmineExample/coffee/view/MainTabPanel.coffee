Ext.define "JasmineExample.view.MainTabPanel",
  extend: "Ext.tab.Panel"
  alias: "widget.jasmineExample-view-mainTabPanel"
  requires: [ "JasmineExample.view.TestPanel1" ]
  controller: "JasmineExample.controller.MainController"

  cls: "titletabs"
  padding: "15, 0, 0, 0"
  activeTab: 0
  autoScroll: false
  layout: "anchor"

  initComponent: ->

    Ext.applyIf( @,
      items: [
        xtype: "jasmineExample-view-testpanel1"
        itemId: "panel1"
        title: "Panel 1"
        anchor: "100% 100%"
      ,
        xtype: "panel"
        itemId: "panel2"
        title: "Panel 2"
        anchor: "100% 100%"
      ]
    )

    @callParent( arguments )