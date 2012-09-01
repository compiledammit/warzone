
<%@ page import="com.sharp.agg.feed.Entry" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-entry" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-entry" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list entry">
			
				<g:if test="${entryInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="entry.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${entryInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entryInstance?.link}">
				<li class="fieldcontain">
					<span id="link-label" class="property-label"><g:message code="entry.link.label" default="Link" /></span>
					
						<span class="property-value" aria-labelledby="link-label"><g:fieldValue bean="${entryInstance}" field="link"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entryInstance?.contents}">
				<li class="fieldcontain">
					<span id="contents-label" class="property-label"><g:message code="entry.contents.label" default="Contents" /></span>
					
						<span class="property-value" aria-labelledby="contents-label"><g:fieldValue bean="${entryInstance}" field="contents"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${entryInstance?.postedOn}">
				<li class="fieldcontain">
					<span id="postedOn-label" class="property-label"><g:message code="entry.postedOn.label" default="Posted On" /></span>
					
						<span class="property-value" aria-labelledby="postedOn-label"><g:formatDate date="${entryInstance?.postedOn}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${entryInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="entry.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${entryInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${entryInstance?.feed}">
				<li class="fieldcontain">
					<span id="feed-label" class="property-label"><g:message code="entry.feed.label" default="Feed" /></span>
					
						<span class="property-value" aria-labelledby="feed-label"><g:link controller="feed" action="show" id="${entryInstance?.feed?.id}">${entryInstance?.feed?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${entryInstance?.tags}">
				<li class="fieldcontain">
					<span id="tags-label" class="property-label"><g:message code="entry.tags.label" default="Tags" /></span>
					
						<g:each in="${entryInstance.tags}" var="t">
						<span class="property-value" aria-labelledby="tags-label"><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${entryInstance?.id}" />
					<g:link class="edit" action="edit" id="${entryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
