Ext.define( "JasmineExample.controller.MainController",
  extend: "Deft.mvc.ViewController"

  control:
    companyGridPanel:
      selectionchange: "onCompanySelected"
    panel2: {}


  config:
    currentCompany: null

  init: ->
    @callParent( arguments )


  ###*
  * Update the title for one of the tab panels
  * @param title The new title for the panel.
  ###
  updatePanelTitle: ( title ) ->
    @getPanel2().title = title


  onCompanySelected: ( selectionModel, model, index ) ->
    @setCurrentCompany( model[ 0 ] )

)