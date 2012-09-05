
<%@ page import="com.sharp.agg.feed.Feed" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'feed.label', default: 'Feed')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${feedInstance?.title}">
						<dt><g:message code="feed.title.label" default="Title" /></dt>
						
							<dd><g:fieldValue bean="${feedInstance}" field="title"/></dd>
						
					</g:if>
				
					<g:if test="${feedInstance?.url}">
						<dt><g:message code="feed.url.label" default="Url" /></dt>
						
							<dd><g:fieldValue bean="${feedInstance}" field="url"/></dd>
						
					</g:if>
				
					<g:if test="${feedInstance?.lastChecked}">
						<dt><g:message code="feed.lastChecked.label" default="Last Checked" /></dt>
						
							<dd><g:formatDate date="${feedInstance?.lastChecked}" /></dd>
						
					</g:if>
				
					<g:if test="${feedInstance?.createdBy}">
						<dt><g:message code="feed.createdBy.label" default="Created By" /></dt>
						
							<dd><g:link controller="user" action="show" id="${feedInstance?.createdBy?.id}">${feedInstance?.createdBy?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${feedInstance?.dateCreated}">
						<dt><g:message code="feed.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${feedInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${feedInstance?.entries}">
						<dt><g:message code="feed.entries.label" default="Entries" /></dt>
						
							<g:each in="${feedInstance.entries}" var="e">
							<dd><g:link controller="entry" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${feedInstance?.isApproved}">
						<dt><g:message code="feed.isApproved.label" default="Is Approved" /></dt>
						
							<dd><g:formatBoolean boolean="${feedInstance?.isApproved}" /></dd>
						
					</g:if>
				
					<g:if test="${feedInstance?.lastUpdated}">
						<dt><g:message code="feed.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${feedInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${feedInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${feedInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
