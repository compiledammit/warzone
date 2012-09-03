<%@ page import="com.sharp.agg.feed.Category" %>



<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'category', 'error')} ">
	<label for="category">
		<g:message code="category.category.label" default="Category" />
		
	</label>
	<g:textField name="category" maxlength="100" value="${categoryInstance?.category}"/>
</div>

