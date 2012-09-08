Ext.define "JasmineExample.view.TestPanel1",
  extend: "Ext.panel.Panel"
  alias: "widget.jasmineExample-view-testpanel1"
  inject: [ "companyStore" ]

  layout: "anchor"
  title: "Panel Title"

  initComponent: ->

    Ext.applyIf( @,

      items: [
        xtype: "container"
        id: "gridContainer"
        layout: "anchor"
        anchor: "100% 100%"

        items: [
          xtype: "gridpanel"
          id: "gridPanel"
          store: @companyStore
          columnLines: true
          anchor: "100% 100%"

          columns: [
            xtype: "gridcolumn"
            dataIndex: "company"
            text: "Company"
            flex: 1
          ,
            xtype: "numbercolumn"
            dataIndex: "price"
            text: "Price"
            renderer: Ext.util.Format.usMoney
          ,
            xtype: "numbercolumn"
            dataIndex: "change"
            text: "Change"
            format: "0.00"
          ,
            xtype: "numbercolumn"
            dataIndex: "pctChange"
            text: "% Change"
            format: "0.00"
          ,
            xtype: "datecolumn"
            dataIndex: "lastChange"
            text: "Last Change"
          ,
            xtype: "gridcolumn"
            dataIndex: "industry"
            text: "Industry"
          ]
        ]
      ]
    )

    @callParent( arguments )