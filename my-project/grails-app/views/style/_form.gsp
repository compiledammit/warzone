<%@ page import="com.compiledammit.Style" %>



<div class="fieldcontain ${hasErrors(bean: styleInstance, field: 'beers', 'error')} ">
	<label for="beers">
		<g:message code="style.beers.label" default="Beers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${styleInstance?.beers?}" var="b">
    <li><g:link controller="beer" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="beer" action="create" params="['style.id': styleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'beer.label', default: 'Beer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: styleInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="style.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${styleInstance?.name}"/>
</div>

