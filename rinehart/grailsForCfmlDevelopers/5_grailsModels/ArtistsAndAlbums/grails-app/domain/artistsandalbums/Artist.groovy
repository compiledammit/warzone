package artistsandalbums

class Artist {
	
	// Relationship Mappings
	static hasMany = [
		albums : Album 
	]
	
	// Instance Properties
	String name
	
	static constraints = {
	}
}
