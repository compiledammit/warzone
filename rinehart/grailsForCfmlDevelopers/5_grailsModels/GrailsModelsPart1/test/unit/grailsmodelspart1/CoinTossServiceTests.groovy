package grailsmodelspart1



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CoinTossService)
class CoinTossServiceTests {

	void testCoinTossCrud() {
		
		def ct = new CoinToss( wasHeads : true )
		
		ct.save()
		
	}
}
