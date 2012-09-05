import com.sharp.agg.feed.Feed
import com.sharp.agg.user.Role
import com.sharp.agg.user.User
import com.sharp.agg.user.UserRole
import org.springframework.web.context.support.WebApplicationContextUtils

class BootStrap {

    def init = { servletContext ->
        def appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)

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
        if (!Feed.count()) {
            def feed = new Feed(title: 'Compile Dammit!!', url: 'http://compiledammit.com/feed', isApproved: true, createdBy: User.findByUsername('admin'), dateCreated: new Date(), lastChecked: new Date(), lastUpdated: new Date()).save(flush: true, failOnError: true)
            appCtx.entryService.getEntries(feed)
            def feed2 = new Feed(title: 'Raymond Camden', url: 'http://feedproxy.google.com/RaymondCamdensColdfusionBlog', isApproved: true, createdBy: User.findByUsername('admin'), dateCreated: new Date(), lastChecked: new Date(), lastUpdated: new Date()).save(flush: true, failOnError: true)
            appCtx.entryService.getEntries(feed2)
            def feed3 = new Feed(title: 'cfsilence', url: 'http://feeds2.feedburner.com/cfsilence', isApproved: false, createdBy: User.findByUsername('admin'), dateCreated: new Date(), lastChecked: new Date(), lastUpdated: new Date()).save(flush: true, failOnError: true)
        }
    }
    def destroy = {
    }
}
