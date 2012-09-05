describe "The Main Tab Panel...", ->

  view = null
  viewController = null


  createComponentTestArea = ->
    if Ext.get( "componentTestArea" )? then Ext.removeNode( Ext.get( "componentTestArea" ).dom )
    Ext.DomHelper.append( Ext.getBody(), "<div id='componentTestArea' style='visibility: hidden'></div>" )


  beforeEach( ->

    createComponentTestArea()

    view = Ext.create( "JasmineExample.view.MainTabPanel",
      renderTo: "componentTestArea"
    )

    viewController = view.getController()
  )


  afterEach( ->
    view.destroy()
    view = null
    viewController = null
  )


  describe "During a successful startup...", ->


    it "has created the main tab panel view and view controller", ->
      expect( view ).toBeDefined()
      expect( view.rendered ).toBeTruthy()
      expect( view instanceof JasmineExample.view.MainTabPanel ).toBeTruthy()
      expect( viewController ).toBeDefined()
      expect( viewController instanceof JasmineExample.controller.MainController ).toBeTruthy()
      expect( viewController.getView() is view ).toBeTruthy()

