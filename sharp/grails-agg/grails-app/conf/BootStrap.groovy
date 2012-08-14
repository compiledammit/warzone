import com.sharp.agg.user.Role
import com.sharp.agg.user.User
import com.sharp.agg.user.UserRole

class BootStrap {

    def init = { servletContext ->
        if (!User.count()) {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true, flush: true)
            def userRole = new Role(authority: 'ROLE_USER').save(failOnError: true, flush: true)

            def adminUser = new User(username: 'admin', password: 'password', enabled: true)
            def user = new User(username: 'user', password: 'password', enabled: true)

            adminUser.save(flush: true, failOnError: true)
            user.save(flush: true, failOnError: true)

            UserRole.create(adminUser, adminRole, true)
            UserRole.create(user, userRole, true)
        }
    }
    def destroy = {
    }
}
