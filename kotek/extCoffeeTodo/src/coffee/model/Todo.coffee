Ext.define( "ExtCoffeeTodo.model.Todo",
	extend: "Ext.data.Model"

	# Models can encapsulate business logic,
	# handle data conversion, etc.
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
	,
		name: "lastUpdated"
		type: "date"
	]

)
