import com.compiledammit.security.SecRole
import com.compiledammit.security.SecUser
import com.compiledammit.security.SecUserSecRole

class BootStrap {

    def init = { servletContext ->
        if (!SecUser.count()) {
            def adminRole = new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true, flush: true)
            def userRole = new SecRole(authority: 'ROLE_USER').save(failOnError: true, flush: true)

            def adminUser = new SecUser(username: 'admin', password: 'password', enabled: true, latitude: 41.1383, longitude: 81.8639)
            def user = new SecUser(username: 'user', password: 'password', enabled: true, latitude: 41.1383, longitude: 81.8639)

            adminUser.save(flush: true, failOnError: true)
            user.save(flush: true, failOnError: true)

            SecUserSecRole.create adminUser, adminRole, true
            SecUserSecRole.create user, userRole, true
        }
    }
    def destroy = {
    }
}
