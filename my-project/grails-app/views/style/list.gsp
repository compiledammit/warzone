<%@ page import="com.compiledammit.Style" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <g:set var="entityName" value="${message(code: 'style.label', default: 'Style')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-style" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>
<content tag="menuItems">
    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
</content>

<div id="list-style" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'style.name.label', default: 'Name')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${styleInstanceList}" status="i" var="styleInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${styleInstance.id}">${fieldValue(bean: styleInstance, field: "name")}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${styleInstanceTotal}"/>
    </div>
</div>
</body>
</html>
