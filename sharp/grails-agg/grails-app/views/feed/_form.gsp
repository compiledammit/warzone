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

<div class="fieldcontain ${hasErrors(bean: feedInstance, field: 'lastChecked', 'error')} required">
	<label for="lastChecked">
		<g:message code="feed.lastChecked.label" default="Last Checked" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="lastChecked" precision="day"  value="${feedInstance?.lastChecked}"  />
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
	
<ul class="one-to-many">
<g:each in="${feedInstance?.entries?}" var="e">
    <li><g:link controller="entry" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="entry" action="create" params="['feed.id': feedInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'entry.label', default: 'Entry')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: feedInstance, field: 'isApproved', 'error')} ">
	<label for="isApproved">
		<g:message code="feed.isApproved.label" default="Is Approved" />
		
	</label>
	<g:checkBox name="isApproved" value="${feedInstance?.isApproved}" />
</div>

