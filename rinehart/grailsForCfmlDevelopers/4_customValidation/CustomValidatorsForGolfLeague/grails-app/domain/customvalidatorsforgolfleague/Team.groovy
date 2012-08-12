package customvalidatorsforgolfleague

class Team {
	
	// GORM mappings
	static hasMany = [ golfers : Golfer ]
	
	// Properties
	Season season

	// Defining a list to keep golfers in order
	List golfers
	
    static constraints = {
        golfers validator: { golfers, team, errors ->
            if ( golfers.size() > team.season.maxGolfersPerTeam ) {
                errors.rejectValue(
                        "golfers",
                        "team.golfers.tooMany",
                        "This season allows a maximum of ${ team.season.maxGolfersPerTeam } players per team."
                )
                return false
            }

            return true
        }
    }
}
