Ext.define( "JasmineExample.store.CompanyStore",
  extend: "Ext.data.Store"
  requires: [ "JasmineExample.model.Company" ]

  constructor: ( cfg ) ->
    cfg = cfg or {}

    @callParent(
      [
        Ext.apply(
          autoLoad: true
          model: "JasmineExample.model.Company"
          proxy:
            type: "ajax"
            url: "data/companies.json"
            reader:
              type: "json"
        ,
          cfg )
      ]
    )
    
    
  filterIndustry: ( value ) ->
    @filter( "industry", value )
)