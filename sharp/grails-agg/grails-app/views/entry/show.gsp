
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
			
				<g:if test="${instance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="entry.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${instance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instance?.link}">
				<li class="fieldcontain">
					<span id="link-label" class="property-label"><g:message code="entry.link.label" default="Link" /></span>
					
						<span class="property-value" aria-labelledby="link-label"><g:fieldValue bean="${instance}" field="link"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instance?.contents}">
				<li class="fieldcontain">
					<span id="contents-label" class="property-label"><g:message code="entry.contents.label" default="Contents" /></span>
					
						<span class="property-value" aria-labelledby="contents-label"><g:fieldValue bean="${instance}" field="contents"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${instance?.postedOn}">
				<li class="fieldcontain">
					<span id="postedOn-label" class="property-label"><g:message code="entry.postedOn.label" default="Posted On" /></span>
					
						<span class="property-value" aria-labelledby="postedOn-label"><g:formatDate date="${instance?.postedOn}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${instance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="entry.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${instance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${instance?.feed}">
				<li class="fieldcontain">
					<span id="feed-label" class="property-label"><g:message code="entry.feed.label" default="Feed" /></span>
					
						<span class="property-value" aria-labelledby="feed-label"><g:link controller="feed" action="show" id="${instance?.feed?.id}">${instance?.feed?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${instance?.tags}">
				<li class="fieldcontain">
					<span id="tags-label" class="property-label"><g:message code="entry.tags.label" default="Tags" /></span>
					
						<g:each in="${instance.tags}" var="t">
						<span class="property-value" aria-labelledby="tags-label"><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${instance?.id}" />
					<g:link class="edit" action="edit" id="${instance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
