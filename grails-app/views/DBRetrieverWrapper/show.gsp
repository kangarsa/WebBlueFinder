
<%@ page import="webbluefinder.DBRetrieverWrapper" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-DBRetrieverWrapper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-DBRetrieverWrapper" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list DBRetrieverWrapper">
			
				<g:if test="${DBRetrieverWrapperInstance?.error}">
				<li class="fieldcontain">
					<span id="error-label" class="property-label"><g:message code="DBRetrieverWrapper.error.label" default="Error" /></span>
					
						<span class="property-value" aria-labelledby="error-label"><g:fieldValue bean="${DBRetrieverWrapperInstance}" field="error"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${DBRetrieverWrapperInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="DBRetrieverWrapper.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${DBRetrieverWrapperInstance}" field="state"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${DBRetrieverWrapperInstance?.id}" />
					<g:link class="edit" action="edit" id="${DBRetrieverWrapperInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>