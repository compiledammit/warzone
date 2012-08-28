class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }
        "/"(controller: 'home', action: 'index')
        "500"(view: '/error')
        "404"(controller: 'home', action: 'notFound')
    }
}
