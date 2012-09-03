<%@ page import="com.sharp.agg.feed.Feed" %>



<div class="fieldcontain ${hasErrors(bean: feedInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="feed.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" maxlength="250" required="" value="${feedInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: feedInstance, field: 'url', 'error')} required">
	<label for="url">
		<g:message code="feed.url.label" default="Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="url" cols="40" rows="5" maxlength="1000" required="" value="${feedInstance?.url}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: feedInstance, field: 'addedOn', 'error')} required">
	<label for="addedOn">
		<g:message code="feed.addedOn.label" default="Added On" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="addedOn" precision="day"  value="${feedInstance?.addedOn}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: feedInstance, field: 'lastUpdatedOn', 'error')} required">
	<label for="lastUpdatedOn">
		<g:message code="feed.lastUpdatedOn.label" default="Last Updated On" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="lastUpdatedOn" precision="day"  value="${feedInstance?.lastUpdatedOn}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: feedInstance, field: 'createdBy', 'error')} required">
	<label for="createdBy">
		<g:message code="feed.createdBy.label" default="Created By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="createdBy" name="createdBy.id" from="${com.sharp.agg.user.User.list()}" optionKey="id" required="" value="${feedInstance?.createdBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: feedInstance, field: 'entries', 'error')} ">
	<label for="entries">
		<g:message code="feed.entries.label" default="Entries" />
		
	</label>
	<g:select name="entries" from="${com.sharp.agg.feed.Entry.list()}" multiple="multiple" optionKey="id" size="5" value="${feedInstance?.entries*.id}" class="many-to-many"/>
</div>

