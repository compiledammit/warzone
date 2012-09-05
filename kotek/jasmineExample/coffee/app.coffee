Ext.Loader.setConfig( enabled: true )


Ext.application
  autoCreateViewport: true
  name: "JasmineExample"


Ext.onReady ->

  Deft.Injector.configure(

    companyStore: "JasmineExample.store.CompanyStore"

  )
