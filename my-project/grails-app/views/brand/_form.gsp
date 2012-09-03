<%@ page import="com.compiledammit.Brand" %>



<div class="fieldcontain ${hasErrors(bean: brandInstance, field: 'beers', 'error')} ">
	<label for="beers">
		<g:message code="brand.beers.label" default="Beers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${brandInstance?.beers?}" var="b">
    <li><g:link controller="beer" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="beer" action="create" params="['brand.id': brandInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'beer.label', default: 'Beer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: brandInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="brand.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${brandInstance?.name}"/>
</div>

