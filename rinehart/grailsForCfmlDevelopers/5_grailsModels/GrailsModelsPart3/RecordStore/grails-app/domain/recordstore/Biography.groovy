package recordstore

class Biography {

	static belongsTo = [
		artist : Artist	
	]
	
	String content
	
    static constraints = {
    }
}
