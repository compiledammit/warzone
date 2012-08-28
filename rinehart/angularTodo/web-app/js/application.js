// Create a TodoController for Angular
function TodoController( $scope, $routeParams, $http ) {

    // description for a new todo
    $scope.description = ""

    // bindable list of todos
    $scope.todos = []

    // load all todos, copying to the "todos" list on success
    $scope.loadTodos = function() {
        $http.get("todo/ajaxList").success( function( data ) {
            $scope.todos = data
        })
    }

    // save a new todo, based on the "description" property
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

    // mark a todo complete, reloading the whole list on success
    $scope.complete = function( id ) {
        $http.post("todo/ajaxComplete?id=" + id).success( function( data ) {
            $scope.loadTodos()
        })
    }

    // when we first stat up, load todos
    $scope.loadTodos()
}
