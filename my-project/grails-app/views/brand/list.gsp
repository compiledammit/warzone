<%@ page import="com.compiledammit.Brand" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <g:set var="entityName" value="${message(code: 'brand.label', default: 'Brand')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-brand" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>
<content tag="menuItems">
    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
</content>

<div id="list-brand" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'brand.name.label', default: 'Name')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${brandInstanceList}" status="i" var="brandInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${brandInstance.id}">${fieldValue(bean: brandInstance, field: "name")}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${brandInstanceTotal}"/>
    </div>
</div>
</body>
</html>
