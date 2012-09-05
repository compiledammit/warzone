describe "During initialization and setup, the application...", ->


  it "has loaded ExtJS 4", ->
    expect( Ext ).toBeDefined()
    expect( Ext.getVersion() ).toBeTruthy()
    expect( Ext.getVersion().major ).toEqual( 4 )


  it "has loaded Application with expected Application name", ->
    expect( window.Application ).toBeDefined()
    expect( window.Application.name ).toEqual( "JasmineExample" )


  it "has created DeftJS IoC items", ->
    expect( Deft.ioc.Injector.canResolve( "companyStore" ) ).toBeTruthy()
    expect( Deft.ioc.Injector.canResolve( "some_$unknown$_alias" ) ).toBeFalsy()


  it "can inject dependencies into a target object", ->
    target =
      companyStore: null
      someOtherProperty: null
      config: {}

    Deft.ioc.Injector.inject( "companyStore", target )

    expect( target.companyStore ).toBeTruthy()
    expect( target.someOtherProperty ).toBeFalsy()
