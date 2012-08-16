package genericservice

class BaconController {

    def service
    def setBaconService( BaconService bs ){ service = bs }


    def index() {
        return [bacons : Bacon.list()]
    }

    def save(){
        def b = Bacon.get(params.id)
         bindData(b, [fatPercent : Math.random(), saltPercent : Math.random()])
        service.save(b)
        redirect( action:  "index" )
    }
}
