<%@ page import="webbluefinder.Finalized" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'finalized.label', default: 'Finalized')}" />
	<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>

<body>

	<section id="edit-finalized" class="first">

		<g:hasErrors bean="${finalizedInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${finalizedInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:form method="post" class="form-horizontal" role="form" >
			<g:hiddenField name="id" value="${finalizedInstance?.id}" />
			<g:hiddenField name="version" value="${finalizedInstance?.version}" />
			<g:hiddenField name="_method" value="PUT" />
			
			<g:render template="form"/>
			
			<div class="form-actions margin-top-medium">
				<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
	            <button class="btn" type="reset"><g:message code="default.button.reset.label" default="Reset" /></button>
			</div>
		</g:form>

	</section>

</body>

</html>
