<!-- 
	Use the 'form' tag to state which controller
	and action handle the post.
-->
<g:form controller="hello" action="sayHi">
	<div>
		<label for="name">Name:</label>
		<!-- Create a text input for name -->
		<g:textField name="name" maxLength="50" myCustomStuff="whatever" />
	</div>
	
	<input type="submit" value="Submit" />
</g:form>
