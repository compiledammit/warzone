package artistsandalbums

class Song {
	
	// Relationship Mappings
	static belongsTo = Album

	// Instance properties
	String name
	
	static constraints = {
	}
}
