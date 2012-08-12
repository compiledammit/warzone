package customvalidatorsforgolfleague

import grails.test.mixin.*
import org.junit.*

@TestFor(Team)
class TeamTests {

    void testMaxGolfersPerTeamIsEnforced() {
        def league = new League()
        def season = new Season(
                league : league,
                maxGolfersPerTeam: 5
        )
        def team = new Team( season : season )

        // I don't know if a team with no golfers is legit, so not testing

        // Test a team with the right number of golfers
        (1..season.maxGolfersPerTeam).each {
            team.addToGolfers( new Golfer() )
        }
        assert team.validate()

        // Add one more, should fail
        team.addToGolfers( new Golfer() )
        assert !team.validate()

        println team.errors.getFieldError( "golfers" )

    }
}
