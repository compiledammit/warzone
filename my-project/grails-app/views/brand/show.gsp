<%@ page import="com.compiledammit.Brand" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <g:set var="entityName" value="${message(code: 'brand.label', default: 'Brand')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-brand" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>
<content tag="menuItems">
    <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    <li><g:link class="create" action="create"><g:message code="default.new.label"  args="[entityName]"/></g:link></li>
</content>

<div id="show-brand" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list brand">

        <g:if test="${brandInstance?.beers}">
            <li class="fieldcontain">
                <span id="beers-label" class="property-label"><g:message code="brand.beers.label"
                                                                         default="Beers"/></span>

                <g:each in="${brandInstance.beers}" var="b">
                    <span class="property-value" aria-labelledby="beers-label"><g:link controller="beer" action="show"
                                                                                       id="${b.id}">${b?.name?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${brandInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="brand.name.label" default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${brandInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${brandInstance?.id}"/>
            <g:link class="edit" action="edit" id="${brandInstance?.id}"><g:message code="default.button.edit.label"
                                                                                    default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
