<%@ page import="com.compiledammit.Style" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <g:set var="entityName" value="${message(code: 'style.label', default: 'Style')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-style" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>
<content tag="menuItems">
    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
</content>

<div id="edit-style" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${styleInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${styleInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${styleInstance?.id}"/>
        <g:hiddenField name="version" value="${styleInstance?.version}"/>
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:actionSubmit class="save" action="update"
                            value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate=""
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
