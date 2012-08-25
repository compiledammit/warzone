package recordstore

class Song {
	
	// Mappings
	static belongsTo = [
		album : Album
	]
	
	String name
	// Many-to-one Album
	Album album
		
	static constraints = {
	}
}
