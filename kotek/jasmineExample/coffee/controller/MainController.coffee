Ext.define( "JasmineExample.controller.MainController",
  extend: "Deft.mvc.ViewController"

  control:
    panel2: {}

  init: ->
    #all accessors will have been created
    #all event listeners will have been added
    console.log( "Got here!" )
    @callParent(arguments)


  updatePanelTitle: ( title ) ->
    @getPanel2().title = title
)