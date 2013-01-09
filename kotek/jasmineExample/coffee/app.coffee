Ext.Loader.setConfig( enabled: true )


Ext.application(
  autoCreateViewport: false
  name: "JasmineExample"
)


Ext.require([
  "JasmineExample.view.Viewport"
	"JasmineExample.store.CompanyStore"
])


Ext.onReady( ->

  Deft.Injector.configure(

    companyStore: "JasmineExample.store.CompanyStore"

  )

  Ext.create( "JasmineExample.view.Viewport" )

)