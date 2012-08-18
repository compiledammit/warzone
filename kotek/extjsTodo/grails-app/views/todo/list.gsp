
<%@ page import="extjstodo.Todo" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'todo.label', default: 'Todo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-todo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-todo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="complete" title="${message(code: 'todo.complete.label', default: 'Complete')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'todo.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'todo.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'todo.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${todoInstanceList}" status="i" var="todoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${todoInstance.id}">${fieldValue(bean: todoInstance, field: "complete")}</g:link></td>
					
						<td><g:formatDate date="${todoInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: todoInstance, field: "description")}</td>
					
						<td><g:formatDate date="${todoInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${todoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
