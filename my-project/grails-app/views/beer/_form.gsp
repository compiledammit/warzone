<%@ page import="com.compiledammit.Beer" %>



<div class="fieldcontain ${hasErrors(bean: beerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="beer.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="150" required="" value="${beerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: beerInstance, field: 'abv', 'error')} required">
	<label for="abv">
		<g:message code="beer.abv.label" default="Abv" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="abv" value="${fieldValue(bean: beerInstance, field: 'abv')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: beerInstance, field: 'brand', 'error')} required">
	<label for="brand">
		<g:message code="beer.brand.label" default="Brand" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="brand" name="brand.id" from="${com.compiledammit.Brand.list()}" optionKey="id" optionValue="name" required="" value="${beerInstance?.brand?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: beerInstance, field: 'style', 'error')} required">
	<label for="style">
		<g:message code="beer.style.label" default="Style" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="style" name="style.id" from="${com.compiledammit.Style.list()}" optionKey="id" optionValue="name" required="" value="${beerInstance?.style?.id}" class="many-to-one"/>
</div>

