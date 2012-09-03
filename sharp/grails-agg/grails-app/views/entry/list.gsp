
<%@ page import="com.sharp.agg.feed.Entry" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="title" title="${message(code: 'entry.title.label', default: 'Title')}" />
						
							<g:sortableColumn property="link" title="${message(code: 'entry.link.label', default: 'Link')}" />
						
							<g:sortableColumn property="contents" title="${message(code: 'entry.contents.label', default: 'Contents')}" />
						
							<g:sortableColumn property="postedOn" title="${message(code: 'entry.postedOn.label', default: 'Posted On')}" />
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'entry.dateCreated.label', default: 'Date Created')}" />
						
							<th class="header"><g:message code="entry.feed.label" default="Feed" /></th>
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${entryInstanceList}" var="entryInstance">
						<tr>
						
							<td>${fieldValue(bean: entryInstance, field: "title")}</td>
						
							<td>${fieldValue(bean: entryInstance, field: "link")}</td>
						
							<td>${fieldValue(bean: entryInstance, field: "contents")}</td>
						
							<td><g:formatDate date="${entryInstance.postedOn}" /></td>
						
							<td><g:formatDate date="${entryInstance.dateCreated}" /></td>
						
							<td>${fieldValue(bean: entryInstance, field: "feed")}</td>
						
							<td class="link">
								<g:link action="show" id="${entryInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${entryInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
