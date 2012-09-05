Ext.define( "JasmineExample.model.Company",
  extend: "Ext.data.Model"

  fields: [
    name: "id"
    type: "auto"
    default: null
  ,
    name: "company"
  ,
    name: "price"
    type: "float"
  ,
    name: "change"
    type: "float"
  ,
    name: "pctChange"
    type: "float"
  ,
    name: "lastChange"
    type: "date"
    dateFormat: "n/j h:ia"
  ,
    name: "industry"
  ]
)