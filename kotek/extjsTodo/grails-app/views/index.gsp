<!doctype html>
<html ng-app>
<head>
    <title>Todo</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
</head>
<body ng-controller="TodoController">

    <form ng-submit="addTodo()">
        <input placeholder="New Todo" type="text" style="width:400px" ng-model="description" />
        <input type="submit" value="Ok" />
    </form>

    <table>
        <tr ng-repeat="todo in todos">
            <td>
                <input type="checkbox" ng-click="complete( todo.id )" checked="{{ todo.completed }}" id="{{ todo.id }}" />
                <label for="{{ todo.id }}">{{ todo.description }}</label>
            </td>
        </tr>
    </table>

<script src="js/angular-1.0.1.min.js"></script>
<script src="js/application.js"></script>
</body>
</html>
