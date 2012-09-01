
<%@ page import="com.sharp.agg.feed.Entry" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-entry" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-entry" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'entry.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="link" title="${message(code: 'entry.link.label', default: 'Link')}" />
					
						<g:sortableColumn property="contents" title="${message(code: 'entry.contents.label', default: 'Contents')}" />
					
						<g:sortableColumn property="postedOn" title="${message(code: 'entry.postedOn.label', default: 'Posted On')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'entry.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="entry.feed.label" default="Feed" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${entryInstanceList}" status="i" var="entryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: entryInstance, field: "link")}</td>
					
						<td>${fieldValue(bean: entryInstance, field: "contents")}</td>
					
						<td><g:formatDate date="${entryInstance.postedOn}" /></td>
					
						<td><g:formatDate date="${entryInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: entryInstance, field: "feed")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${entryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
