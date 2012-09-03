
<%@ page import="com.sharp.agg.feed.Entry" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
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
				
					<g:if test="${entryInstance?.title}">
						<dt><g:message code="entry.title.label" default="Title" /></dt>
						
							<dd><g:fieldValue bean="${entryInstance}" field="title"/></dd>
						
					</g:if>
				
					<g:if test="${entryInstance?.link}">
						<dt><g:message code="entry.link.label" default="Link" /></dt>
						
							<dd><g:fieldValue bean="${entryInstance}" field="link"/></dd>
						
					</g:if>
				
					<g:if test="${entryInstance?.contents}">
						<dt><g:message code="entry.contents.label" default="Contents" /></dt>
						
							<dd><g:fieldValue bean="${entryInstance}" field="contents"/></dd>
						
					</g:if>
				
					<g:if test="${entryInstance?.postedOn}">
						<dt><g:message code="entry.postedOn.label" default="Posted On" /></dt>
						
							<dd><g:formatDate date="${entryInstance?.postedOn}" /></dd>
						
					</g:if>
				
					<g:if test="${entryInstance?.categories}">
						<dt><g:message code="entry.categories.label" default="Categories" /></dt>
						
							<g:each in="${entryInstance.categories}" var="c">
							<dd><g:link controller="category" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
					<g:if test="${entryInstance?.dateCreated}">
						<dt><g:message code="entry.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${entryInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${entryInstance?.feed}">
						<dt><g:message code="entry.feed.label" default="Feed" /></dt>
						
							<dd><g:link controller="feed" action="show" id="${entryInstance?.feed?.id}">${entryInstance?.feed?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${entryInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${entryInstance?.id}">
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
