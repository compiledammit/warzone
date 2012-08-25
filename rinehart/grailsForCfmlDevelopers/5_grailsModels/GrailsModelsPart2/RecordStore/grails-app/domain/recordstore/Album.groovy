package recordstore

class Album {

	// Mappings
	static hasMany = [
		songs : Song 
	]
	
	
	// Properties
	String name
	List songs
	
	static constraints = {
	}
}
