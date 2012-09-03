<%@ page import="com.compiledammit.Beer" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <g:set var="entityName" value="${message(code: 'beer.label', default: 'Beer')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-beer" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>
<content tag="menuItems">
    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
</content>

<div id="list-beer" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'beer.name.label', default: 'Name')}"/>

            <g:sortableColumn property="abv" title="${message(code: 'beer.abv.label', default: 'Abv')}"/>

            <th><g:message code="beer.brand.label" default="Brand"/></th>

            <th><g:message code="beer.style.label" default="Style"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${beerInstanceList}" status="i" var="beerInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${beerInstance.id}">${fieldValue(bean: beerInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: beerInstance, field: "abv")}</td>

                <td>${fieldValue(bean: beerInstance, field: "brand.name")}</td>

                <td>${fieldValue(bean: beerInstance, field: "style.name")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${beerInstanceTotal}"/>
    </div>
</div>
</body>
</html>
