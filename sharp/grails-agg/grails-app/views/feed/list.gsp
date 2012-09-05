
<%@ page import="com.sharp.agg.feed.Feed" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'feed.label', default: 'Feed')}" />
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
						
							<g:sortableColumn property="title" title="${message(code: 'feed.title.label', default: 'Title')}" />
						
							<g:sortableColumn property="url" title="${message(code: 'feed.url.label', default: 'Url')}" />
						
							<g:sortableColumn property="lastChecked" title="${message(code: 'feed.lastChecked.label', default: 'Last Checked')}" />
						
							<th class="header"><g:message code="feed.createdBy.label" default="Created By" /></th>
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'feed.dateCreated.label', default: 'Date Created')}" />
						
							<g:sortableColumn property="isApproved" title="${message(code: 'feed.isApproved.label', default: 'Is Approved')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${feedInstanceList}" var="feedInstance">
						<tr>
						
							<td>${fieldValue(bean: feedInstance, field: "title")}</td>
						
							<td>${fieldValue(bean: feedInstance, field: "url")}</td>
						
							<td><g:formatDate date="${feedInstance.lastChecked}" /></td>
						
							<td>${fieldValue(bean: feedInstance, field: "createdBy")}</td>
						
							<td><g:formatDate date="${feedInstance.dateCreated}" /></td>
						
							<td><g:formatBoolean boolean="${feedInstance.isApproved}" /></td>
						
							<td class="link">
								<g:link action="show" id="${feedInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${feedInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
