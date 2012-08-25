package recordstore

class Artist {

	String name
	
	static hasOne = [
		biography : Biography
	]
	
	static belongsTo = [
		album : Album
	]
	
	static hasMany = [
		album : Album
	]

	static constraints = {
		biography( unique: true )
	}
}



