package customvalidatorsforgolfleague

class Golfer {
	
	// GORM mappings
	static belongsTo = Team
	static hasMany = [ teams : Team ]

    String name

    static constraints = {
        // Validation: "Name" must non-empty, at least one character, and no more than 50
        name blank:  false, size: 1..50
    }
}
