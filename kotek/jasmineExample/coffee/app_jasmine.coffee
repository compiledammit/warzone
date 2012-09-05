Ext.Loader.setConfig( enabled: true )


Ext.application
  autoCreateViewport: false
  name: "JasmineExample"

  launch: ->
    #Create a reference to the ExtJS Application object so we can perform tests against it.
    window.Application = @


Ext.onReady ->

  Deft.Injector.configure(

    companyStore: "JasmineExample.store.CompanyStore"

  )

  execJasmine()
