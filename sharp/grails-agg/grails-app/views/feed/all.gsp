
<%@ page import="com.sharp.agg.feed.Feed" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'feed.label', default: 'Feed')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-feed" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-feed" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /> (All)</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'feed.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="url" title="${message(code: 'feed.url.label', default: 'Url')}" />
					
						<th><g:message code="feed.createdBy.label" default="Created By" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'feed.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'feed.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${feedInstanceList}" status="i" var="feedInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${feedInstance.id}">${fieldValue(bean: feedInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: feedInstance, field: "url")}</td>
					
						<td>${fieldValue(bean: feedInstance, field: "createdBy")}</td>
					
						<td><g:formatDate date="${feedInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${feedInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${feedInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
