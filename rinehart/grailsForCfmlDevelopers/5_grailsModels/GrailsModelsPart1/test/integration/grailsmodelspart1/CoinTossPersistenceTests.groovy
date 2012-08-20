package grailsmodelspart1

import static org.junit.Assert.*
import org.junit.*

class CoinTossPersistenceTests {
	
	def coinTossService

	@Test
	void we_can_crud_coin_tosses() {		
		// Make sure the service can list
		def tosses = coinTossService.list()
		def originalTossCount = tosses.size()
		
		// Make sure the service can create
		def ct = new CoinToss()
		coinTossService.save( ct )
		assert coinTossService.list().size() == originalTossCount + 1
		
		// Make sure the serivce can update without creating new instances
		ct.wasHeads = !ct.wasHeads
		coinTossService.save( ct )
		assert coinTossService.list().size() == originalTossCount + 1
		
		// Make sure we can delete
		coinTossService.delete( ct )
		assert coinTossService.list().size() == originalTossCount
	}
	
	@Test
	void we_can_run_the_heads_or_tails_report() {
		coinTossService.headsOrTailsReport()
	}
}
