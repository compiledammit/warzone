<%@ page import="genericservice.Fudge" %>



<div class="fieldcontain ${hasErrors(bean: fudgeInstance, field: 'flavor', 'error')} ">
	<label for="flavor">
		<g:message code="fudge.flavor.label" default="Flavor" />
		
	</label>
	<g:textField name="flavor" value="${fudgeInstance?.flavor}"/>
</div>

