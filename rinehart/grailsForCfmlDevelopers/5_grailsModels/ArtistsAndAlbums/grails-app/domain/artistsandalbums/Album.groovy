package artistsandalbums

class Album {

	// Mappings
	static mapping = {
		artists	cascade: "save-update" 
	}
	
	// Relationship Mappings
	static hasMany = [
		songs : Song,
		artists : Artist
	]
	
	static belongsTo = Artist
	
	// Instance Properties
	String Name
	
	static constraints = {
	}
}
