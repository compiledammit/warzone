<%@ page import="com.sharp.agg.feed.Entry" %>



<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'aggregatedOn', 'error')} required">
	<label for="aggregatedOn">
		<g:message code="entry.aggregatedOn.label" default="Aggregated On" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="aggregatedOn" precision="day"  value="${entryInstance?.aggregatedOn}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'contents', 'error')} ">
	<label for="contents">
		<g:message code="entry.contents.label" default="Contents" />
		
	</label>
	<g:textField name="contents" value="${entryInstance?.contents}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'link', 'error')} ">
	<label for="link">
		<g:message code="entry.link.label" default="Link" />
		
	</label>
	<g:textField name="link" value="${entryInstance?.link}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'postedOn', 'error')} required">
	<label for="postedOn">
		<g:message code="entry.postedOn.label" default="Posted On" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="postedOn" precision="day"  value="${entryInstance?.postedOn}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="entry.tags.label" default="Tags" />
		
	</label>
	<g:select name="tags" from="${com.sharp.agg.feed.Tag.list()}" multiple="multiple" optionKey="id" size="5" value="${entryInstance?.tags*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="entry.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${entryInstance?.title}"/>
</div>

