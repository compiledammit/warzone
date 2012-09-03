
<%@ page import="com.compiledammit.AppUser" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<g:set var="entityName" value="${message(code: 'appUser.label', default: 'AppUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-appUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<content tag="menuItems">
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        </content>
		<div id="list-appUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'appUser.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="accountExpired" title="${message(code: 'appUser.accountExpired.label', default: 'Account Expired')}" />
					
						<g:sortableColumn property="accountLocked" title="${message(code: 'appUser.accountLocked.label', default: 'Account Locked')}" />
					
						<g:sortableColumn property="enabled" title="${message(code: 'appUser.enabled.label', default: 'Enabled')}" />
					
						<g:sortableColumn property="passwordExpired" title="${message(code: 'appUser.passwordExpired.label', default: 'Password Expired')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${appUserInstanceList}" status="i" var="appUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${appUserInstance.id}">${fieldValue(bean: appUserInstance, field: "username")}</g:link></td>
					
						<td><g:formatBoolean boolean="${appUserInstance.accountExpired}" /></td>
					
						<td><g:formatBoolean boolean="${appUserInstance.accountLocked}" /></td>
					
						<td><g:formatBoolean boolean="${appUserInstance.enabled}" /></td>
					
						<td><g:formatBoolean boolean="${appUserInstance.passwordExpired}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${appUserInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
