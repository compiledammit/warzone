<%@ page import="com.compiledammit.FavoriteStyle; com.compiledammit.FavoriteBrand; com.compiledammit.FavoriteBeer; com.compiledammit.AppUser" %>



<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'username', 'error')} required">
    <label for="username">
        <g:message code="appUser.username.label" default="Username"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="username" required="" value="${appUserInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="appUser.password.label" default="Password"/>
        <span class="required-indicator">*</span>
    </label>
    <g:passwordField name="password" required="" value="${appUserInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'accountExpired', 'error')} ">
    <label for="accountExpired">
        <g:message code="appUser.accountExpired.label" default="Account Expired"/>

    </label>
    <g:checkBox name="accountExpired" value="${appUserInstance?.accountExpired}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'accountLocked', 'error')} ">
    <label for="accountLocked">
        <g:message code="appUser.accountLocked.label" default="Account Locked"/>

    </label>
    <g:checkBox name="accountLocked" value="${appUserInstance?.accountLocked}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'enabled', 'error')} ">
    <label for="enabled">
        <g:message code="appUser.enabled.label" default="Enabled"/>

    </label>
    <g:checkBox name="enabled" value="${appUserInstance?.enabled}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'favorites', 'error')} ">
    <label for="favorites">
        <g:message code="appUser.favorites.label" default="Favorites"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${appUserInstance?.favorites ?}" var="f">
            <g:if test="${f.instanceOf(FavoriteBeer)}">
                <li><g:link controller="beer" action="show" id="${f.beer.id}">${f.beer.name.encodeAsHTML()}</g:link></li>
            </g:if>
            <g:if test="${f.instanceOf(FavoriteBrand)}">
                <li><g:link controller="brand" action="show" id="${f.brand.id}">${f.brand.name.encodeAsHTML()}</g:link></li>
            </g:if>
            <g:if test="${f.instanceOf(FavoriteStyle)}">
                <li><g:link controller="style" action="show" id="${f.style.id}">${f.style.name.encodeAsHTML()}</g:link></li>
            </g:if>
        </g:each>
        <li class="add">
            <g:link controller="favorite" action="create" params="['appUser.id': appUserInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'favorite.label', default: 'Favorite')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'passwordExpired', 'error')} ">
    <label for="passwordExpired">
        <g:message code="appUser.passwordExpired.label" default="Password Expired"/>

    </label>
    <g:checkBox name="passwordExpired" value="${appUserInstance?.passwordExpired}"/>
</div>

