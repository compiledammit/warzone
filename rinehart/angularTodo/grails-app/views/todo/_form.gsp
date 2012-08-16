<%@ page import="angulartodo.Todo" %>



<div class="fieldcontain ${hasErrors(bean: todoInstance, field: 'complete', 'error')} ">
	<label for="complete">
		<g:message code="todo.complete.label" default="Complete" />
		
	</label>
	<g:checkBox name="complete" value="${todoInstance?.complete}" />
</div>

<div class="fieldcontain ${hasErrors(bean: todoInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="todo.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${todoInstance?.description}"/>
</div>

