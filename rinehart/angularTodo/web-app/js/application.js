function TodoController( $scope, $routeParams, $http ) {

    $scope.description = ""
    $scope.todos = []

    $scope.loadTodos = function() {
        $http.get("todo/ajaxList").success( function( data ) {
            $scope.todos = data
        })
    }

    $scope.addTodo = function() {
        $http.post(
            "todo/ajaxSave",
            {
                description : $scope.description
            }
        ).success( function( data ) {
            $scope.todos = data
            $scope.description = ""
        })
    }

    $scope.complete = function( id ) {
        $http.post("todo/ajaxComplete?id=" + id).success( function( data ) {
            $scope.loadTodos()
        })
    }


    $scope.loadTodos()
}
