<%@ page import="com.sharp.agg.feed.Tag" %>



<div class="fieldcontain ${hasErrors(bean: instance, field: 'tag', 'error')} ">
	<label for="tag">
		<g:message code="tag.tag.label" default="Tag" />
		
	</label>
	<g:textField name="tag" value="${instance?.tag}"/>
</div>

