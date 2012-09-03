<%@ page import="com.compiledammit.Beer" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <g:set var="entityName" value="${message(code: 'beer.label', default: 'Beer')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-beer" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<content tag="menuItems">
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
</content>

<div id="show-beer" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list beer">

        <g:if test="${beerInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="beer.name.label" default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${beerInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${beerInstance?.abv}">
            <li class="fieldcontain">
                <span id="abv-label" class="property-label"><g:message code="beer.abv.label" default="Abv"/></span>

                <span class="property-value" aria-labelledby="abv-label"><g:fieldValue bean="${beerInstance}"
                                                                                       field="abv"/></span>

            </li>
        </g:if>

        <g:if test="${beerInstance?.brand}">
            <li class="fieldcontain">
                <span id="brand-label" class="property-label"><g:message code="beer.brand.label"
                                                                         default="Brand"/></span>

                <span class="property-value" aria-labelledby="brand-label"><g:link controller="brand" action="show"
                                                                                   id="${beerInstance?.brand?.id}">${beerInstance?.brand?.name?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${beerInstance?.style}">
            <li class="fieldcontain">
                <span id="style-label" class="property-label"><g:message code="beer.style.label"
                                                                         default="Style"/></span>

                <span class="property-value" aria-labelledby="style-label"><g:link controller="style" action="show"
                                                                                   id="${beerInstance?.style?.id}">${beerInstance?.style?.name?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${beerInstance?.id}"/>
            <g:link class="edit" action="edit" id="${beerInstance?.id}"><g:message code="default.button.edit.label"
                                                                                   default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
