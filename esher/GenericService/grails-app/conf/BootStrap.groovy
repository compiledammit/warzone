import genericservice.Bacon
import genericservice.Fudge

class BootStrap {

    def init = { servletContext ->

        if( !Bacon.count() ){
            new Bacon(fatPercent: Math.random(), saltPercent: Math.random()).save(flush:true)
            new Bacon(fatPercent: Math.random(), saltPercent: Math.random()).save(flush:true)
        }

        if( !Fudge.count() ){
            new Fudge(flavor: 'Mint Chocolate').save(flush:true)
        }

    }
    def destroy = {
    }
}
