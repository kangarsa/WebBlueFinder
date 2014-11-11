<%@ page import="webbluefinder.BFPathFinderWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'BFPathFinderWrapper.label', default: 'BFPathFinderWrapper')}" />
	<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>

<body>

	<section id="edit-BFPathFinderWrapper" class="first">

		<g:hasErrors bean="${BFPathFinderWrapperInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${BFPathFinderWrapperInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:form method="post" class="form-horizontal" role="form" >
			<g:hiddenField name="id" value="${BFPathFinderWrapperInstance?.id}" />
			<g:hiddenField name="version" value="${BFPathFinderWrapperInstance?.version}" />
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
