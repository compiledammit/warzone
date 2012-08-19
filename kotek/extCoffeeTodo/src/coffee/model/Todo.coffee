Ext.define "ExtCoffeeTodo.model.Todo",
	extend: "Ext.data.Model"
	fields: [
		name: "id"
	,
		name: "description"
		type: "string"
	,
		name: "complete"
		type: "boolean"
	,
		name: "dateCreated"
		type: "date"
		#dateFormat: "timestamp"
	,
		name: "lastUpdated"
		type: "date"
		#dateFormat: "timestamp"
	]
