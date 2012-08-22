<%@ page import="com.compiledammit.Golfer" %>



<div class="fieldcontain ${hasErrors(bean: golferInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="golfer.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${golferInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: golferInstance, field: 'specialGolferProperty', 'error')} ">
	<label for="specialGolferProperty">
		<g:message code="golfer.specialGolferProperty.label" default="Special Golfer Property" />
		
	</label>
	<g:textField name="specialGolferProperty" value="${golferInstance?.specialGolferProperty}"/>
</div>

