Ext.Loader.setConfig
	enabled: true
	paths:
		"ExtCoffeeTodo": "app/"
		"Ext.ux": "lib/ux/"


Ext.application
  autoCreateViewport: true
  name: "ExtCoffeeTodo"


Ext.require 'ExtCoffeeTodo.store.TodoStore'


Ext.onReady ->

	#Configure IoC
  Deft.Injector.configure
    todoStore: "ExtCoffeeTodo.store.TodoStore"
