package genericservice

class CrudService {

    def save( def domain ) {
        domain.save(flush:true)
    }

    def delete( def domain ){
        domain.delete()
    }
}
