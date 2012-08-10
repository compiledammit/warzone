package sayhello

class HelloController {

	def index() { }
	
	def sayHi() {
		return [
			greeting : "Hi there, ${ params.name }" 
		]
	}
}
