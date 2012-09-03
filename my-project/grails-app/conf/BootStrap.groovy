import com.compiledammit.Brand
import com.compiledammit.Beer
import com.compiledammit.Style
import com.compiledammit.AppUser
import com.compiledammit.FavoriteBeer
import com.compiledammit.FavoriteBrand
import com.compiledammit.FavoriteStyle
import com.compiledammit.AppUserRole
import com.compiledammit.Role

class BootStrap {

    def init = { servletContext ->
        if (!Brand.count()) {
            new Brand(name: 'Sam Adams').save(failOnError: true)
        }
        if (!Style.count()) {
            new Style(name: 'Lager').save(failOnError: true)
            new Style(name: 'Ale').save(failOnError: true)
        }
        if (!Beer.count()) {
            new Beer(name: 'Boston Lager', abv: 5.5, brand: Brand.findByName('Sam Adams'), style: Style.findByName('Lager')).save(failOnError: true);
            new Beer(name: 'Summer Ale', abv: 5.7, brand: Brand.findByName('Sam Adams'), style: Style.findByName('Ale')).save(failOnError: true);
        }
        if(!AppUser.count())
        {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true, flush: true)
            def userRole = new Role(authority: 'ROLE_USER').save(failOnError: true, flush: true)

            def adminUser = new AppUser(username:'admin', password: 'password',enabled: true)
            def user = new AppUser(username:'user', password: 'password',enabled: true)
            adminUser.save(flush:  true, failOnError: true)
            user.save(flush:  true, failOnError: true)

            AppUserRole.create adminUser, adminRole, true
            AppUserRole.create user, userRole, true

            new FavoriteBeer(beer: Beer.findByName('Boston Lager'), appUser: AppUser.findByUsername('admin') ).save(failOnError: true)
            new FavoriteBeer(beer: Beer.findByName('Summer Ale'), appUser: AppUser.findByUsername('admin') ).save(failOnError: true)
            new FavoriteBrand(brand: Brand.findByName('Sam Adams'), appUser: AppUser.findByUsername('admin') ).save(failOnError: true)
            new FavoriteStyle(style: Style.findByName('Lager'), appUser: AppUser.findByUsername('admin') ).save(failOnError: true)
        }
    }

    def destroy = {
    }
}
