Ext.define( 'JasmineExample.controller.MainController',
  extend: 'Deft.mvc.ViewController'

  #control:
    #grid : true
    #submitButton: 'panel > button[text="Submit"]'
    #view:
    #  show: 'related view is set automatically even if no listeners are attached.'

  #config:
    #valueToCreateSetterFor_EspeciallyInjections: null

  init: ->
    #all accessors will have been created
    #all event listeners will have been added
    console.log( 'Got here!' )
    @callParent(arguments)

)