package customvalidatorsforgolfleague

class Season {

	League league

    Date startDate
    Date endDate

    int maxGolfersPerTeam
	
    static constraints = {
		maxGolfersPerTeam min: 1
        
    endDate validator: { value, season, errors ->
        if ( value && value > season.startDate ) {
            errors.rejectValue( "endDate", "season.endDate.afterStartDate", "End date cannot be after start date.")
            return false
        }

        return true
    }
    }
}
