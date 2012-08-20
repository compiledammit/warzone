package grailsmodelspart1

import groovy.sql.Sql

class CoinTossService {

	def dataSource 
	
	List headsOrTailsReport() {
		return new Sql( dataSource ).rows("""
			select
				count(1) as times_landed_this_side,
				was_heads
			from
				coin_toss
			group by
				was_heads
		""")
	}
	
}
	
	List list() {
		return CoinToss.list()
	}
	
	CoinToss save( coinToss ) {
		coinToss.save()
	}
	
	CoinToss delete( coinToss ) {
		coinToss.delete()
	}
}