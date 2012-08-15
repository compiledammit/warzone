package genericservice

class BaconController {

    boolean byName = false  //does not work if baconService is renamed "service"

    BaconService service


    def index() {
        return [bacons : Bacon.list()]
    }

    def save(){
        def b = Bacon.get(params.id)
        params.fatPercent = Math.random()
        params.saltPercent = Math.random()
        bindData(b, params)
        service.save(b)
        redirect( action:  "index" )
    }
}
