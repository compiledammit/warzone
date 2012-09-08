describe "The Main Tab Panel...", ->

  view = null
  viewController = null


  # A hidden div where we can add UI components to test them and their dependencies.
  createComponentTestArea = ->
    if Ext.get( "componentTestArea" )? then Ext.removeNode( Ext.get( "componentTestArea" ).dom )
    Ext.DomHelper.append( Ext.getBody(), "<div id='componentTestArea' style='visibility: hidden'></div>" )


  beforeEach( ->

    createComponentTestArea()

    # For each test, create a MainTabPanel instance and render it.
    view = Ext.create( "JasmineExample.view.MainTabPanel",
      renderTo: "componentTestArea"
    )

    # Grab a reference to the ViewController for the MainTabPanel.
    viewController = view.getController()
  )


  afterEach( ->
    # After each test, destroy the view and clean up.
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


    it "allows panel title to be changed", ->
      # We can test a ViewController method after the view is rendered and associated view controller configured.
      expect( viewController.getPanel2().title ).toEqual( "Panel 2" )
      viewController.updatePanelTitle( "My New Title" )
      expect( viewController.getPanel2().title ).toEqual( "My New Title" )


    it "allows store to be filtered", ->
      # We can test methods on a non-view object, including tests that involve asynchronous activities.
      # This really doesn't belong in the MainTabPanelTest, but for simplicty I'm including it here.
      store = Deft.ioc.Injector.resolve( "companyStore" )

      waitsFor( ( -> store.getCount() > 0 ), "Store data never loaded.", 2000 )

      runs( ->
        expect( store.filters.length ).toBe( 0 )
        store.filterIndustry( "Manufacturing" )
        expect( store.filters.length ).toBe( 1 )

        store.data.each ( thisCompany ) ->
          expect( thisCompany.get( "industry" ) ).toEqual( "Manufacturing" )

        store.clearFilter()
      )


