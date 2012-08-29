package recordstore

class Album {

	// Mappings
	static hasMany = [
		songs : Song,
		artists : Artist
	]
	
	
	// Properties
	String name
	List songs
	
	static constraints = {
	}
}
