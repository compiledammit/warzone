
<%@ page import="com.compiledammit.FavoriteStyle; com.compiledammit.FavoriteBrand; com.compiledammit.FavoriteBeer; com.compiledammit.AppUser" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<g:set var="entityName" value="${message(code: 'appUser.label', default: 'AppUser')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-appUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<content tag="menuItems">
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        </content>
		<div id="show-appUser" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list appUser">
			
				<g:if test="${appUserInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="appUser.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${appUserInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${appUserInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="appUser.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${appUserInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${appUserInstance?.accountExpired}">
				<li class="fieldcontain">
					<span id="accountExpired-label" class="property-label"><g:message code="appUser.accountExpired.label" default="Account Expired" /></span>
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${appUserInstance?.accountExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${appUserInstance?.accountLocked}">
				<li class="fieldcontain">
					<span id="accountLocked-label" class="property-label"><g:message code="appUser.accountLocked.label" default="Account Locked" /></span>
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${appUserInstance?.accountLocked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${appUserInstance?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="appUser.enabled.label" default="Enabled" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${appUserInstance?.enabled}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${appUserInstance?.favorites}">
				<li class="fieldcontain">
					<span id="favorites-label" class="property-label"><g:message code="appUser.favorites.label" default="Favorites" /></span>
						<g:each in="${appUserInstance.favorites?}" var="f">
                            <g:if test="${f.instanceOf(FavoriteBeer)}">
                                <span class="property-value" aria-labelledby="favorites-label"><g:link controller="beer" action="show" id="${f.beer.id}">${f.beer.name.encodeAsHTML()}</g:link></span>
                            </g:if>
                            <g:if test="${f.instanceOf(FavoriteBrand)}">
                                <span class="property-value" aria-labelledby="favorites-label"><g:link controller="brand" action="show" id="${f.brand.id}">${f.brand.name.encodeAsHTML()}</g:link></span>
                            </g:if>
                            <g:if test="${f.instanceOf(FavoriteStyle)}">
                                <span class="property-value" aria-labelledby="favorites-label"><g:link controller="style" action="show" id="${f.style.id}">${f.style.name.encodeAsHTML()}</g:link></span>
                            </g:if>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${appUserInstance?.passwordExpired}">
				<li class="fieldcontain">
					<span id="passwordExpired-label" class="property-label"><g:message code="appUser.passwordExpired.label" default="Password Expired" /></span>
					
						<span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${appUserInstance?.passwordExpired}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${appUserInstance?.id}" />
					<g:link class="edit" action="edit" id="${appUserInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
