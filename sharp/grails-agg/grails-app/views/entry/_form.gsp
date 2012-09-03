<%@ page import="com.sharp.agg.feed.Entry" %>



<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="entry.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" maxlength="250" required="" value="${entryInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'link', 'error')} required">
	<label for="link">
		<g:message code="entry.link.label" default="Link" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="link" cols="40" rows="5" maxlength="1000" required="" value="${entryInstance?.link}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'contents', 'error')} required">
	<label for="contents">
		<g:message code="entry.contents.label" default="Contents" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="contents" cols="40" rows="5" maxlength="4000" required="" value="${entryInstance?.contents}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'postedOn', 'error')} required">
	<label for="postedOn">
		<g:message code="entry.postedOn.label" default="Posted On" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="postedOn" precision="day"  value="${entryInstance?.postedOn}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'categories', 'error')} ">
	<label for="categories">
		<g:message code="entry.categories.label" default="Categories" />
		
	</label>
	<g:select name="categories" from="${com.sharp.agg.feed.Category.list()}" multiple="multiple" optionKey="id" size="5" value="${entryInstance?.categories*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entryInstance, field: 'feed', 'error')} required">
	<label for="feed">
		<g:message code="entry.feed.label" default="Feed" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="feed" name="feed.id" from="${com.sharp.agg.feed.Feed.list()}" optionKey="id" required="" value="${entryInstance?.feed?.id}" class="many-to-one"/>
</div>

