<%@ page import="com.compiledammit.AdminUser" %>



<div class="fieldcontain ${hasErrors(bean: adminUserInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="adminUser.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${adminUserInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adminUserInstance, field: 'specialAdminUserProperty', 'error')} ">
	<label for="specialAdminUserProperty">
		<g:message code="adminUser.specialAdminUserProperty.label" default="Special Admin User Property" />
		
	</label>
	<g:textField name="specialAdminUserProperty" value="${adminUserInstance?.specialAdminUserProperty}"/>
</div>

