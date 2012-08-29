<!doctype html>

<!-- Adding 'ng-app' tells Angular to start its magic at this point in the DOM -->
<html ng-app>
<head>
    <title>Todo</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
</head>

<!-- Anything inside here should use the $scope of TodoController -->
<body ng-controller="TodoController">

    <!-- When this form is submitted, cancel the action and run TodoController.addTodo() -->
    <form ng-submit="addTodo()">
        <!-- Bind the value of this text input to TodoController.description -->
        <input placeholder="New Todo" type="text" style="width:400px" ng-model="description" />
        <input type="submit" value="Ok" />
    </form>

    <table>
        <!-- Do this for every Todo in TodoController.todos -->
        <tr ng-repeat="todo in todos">
            <td>
                <!-- Call complete( id ) for this one when it's clicked -->
                <input type="checkbox" ng-click="complete( todo.id )" checked="{{ todo.completed }}" id="{{ todo.id }}" />
                <label for="{{ todo.id }}">{{ todo.description }}</label>
            </td>
        </tr>
    </table>

<script src="js/angular-1.0.1.min.js"></script>
<script src="js/application.js"></script>
</body>
</html>
