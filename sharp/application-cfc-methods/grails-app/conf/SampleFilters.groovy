class SampleFilters {

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                log.info "before filter in controller: '$controllerName' with action: '$actionName'"
            }
            after = { Map model ->
                log.info "after filter in controller: '$controllerName' with action: '$actionName'"
            }
            afterView = { Exception e ->
                log.info "afterView filter in controller: '$controllerName' with action: '$actionName'"
            }
        }
    }
}
