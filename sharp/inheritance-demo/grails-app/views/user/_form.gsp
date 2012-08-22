<%@ page import="com.compiledammit.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${userInstance?.firstName}"/>
</div>

<div class="fieldcontain">
	<label for="userType">
		<g:message code="user.userType.label" default="User Type" />
	</label>
    <g:select name="userType" from="${userTypes}" />
</div>

