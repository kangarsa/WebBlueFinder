
<%@ page import="webbluefinder.PIAWrapper" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'PIAWrapper.label', default: 'PIAWrapper')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-PIAWrapper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-PIAWrapper" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list PIAWrapper">
			
				<g:if test="${PIAWrapperInstance?.observer}">
				<li class="fieldcontain">
					<span id="observer-label" class="property-label"><g:message code="PIAWrapper.observer.label" default="Observer" /></span>
					
						<span class="property-value" aria-labelledby="observer-label"><g:link controller="scene" action="show" id="${PIAWrapperInstance?.observer?.id}">${PIAWrapperInstance?.observer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${PIAWrapperInstance?.processState}">
				<li class="fieldcontain">
					<span id="processState-label" class="property-label"><g:message code="PIAWrapper.processState.label" default="Process State" /></span>
					
						<span class="property-value" aria-labelledby="processState-label"><g:fieldValue bean="${PIAWrapperInstance}" field="processState"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${PIAWrapperInstance?.processErrors}">
				<li class="fieldcontain">
					<span id="processErrors-label" class="property-label"><g:message code="PIAWrapper.processErrors.label" default="Process Errors" /></span>
					
						<span class="property-value" aria-labelledby="processErrors-label"><g:fieldValue bean="${PIAWrapperInstance}" field="processErrors"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${PIAWrapperInstance?.id}" />
					<g:link class="edit" action="edit" id="${PIAWrapperInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
