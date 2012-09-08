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


  ###*
  * Filter the Companies using the specified industry value.
  * @param value The industry to filter on.
  ###
  filterIndustry: ( value ) ->
    @filter( "industry", value )

)