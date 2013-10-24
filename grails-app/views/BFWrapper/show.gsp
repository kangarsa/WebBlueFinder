
<%@ page import="webbluefinder.BFWrapper" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'BFWrapper.label', default: 'BFWrapper')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-BFWrapper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-BFWrapper" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list BFWrapper">
			
				<g:if test="${BFWrapperInstance?.observer}">
				<li class="fieldcontain">
					<span id="observer-label" class="property-label"><g:message code="BFWrapper.observer.label" default="Observer" /></span>
					
						<span class="property-value" aria-labelledby="observer-label"><g:link controller="scene" action="show" id="${BFWrapperInstance?.observer?.id}">${BFWrapperInstance?.observer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFWrapperInstance?.processState}">
				<li class="fieldcontain">
					<span id="processState-label" class="property-label"><g:message code="BFWrapper.processState.label" default="Process State" /></span>
					
						<span class="property-value" aria-labelledby="processState-label"><g:fieldValue bean="${BFWrapperInstance}" field="processState"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFWrapperInstance?.processErrors}">
				<li class="fieldcontain">
					<span id="processErrors-label" class="property-label"><g:message code="BFWrapper.processErrors.label" default="Process Errors" /></span>
					
						<span class="property-value" aria-labelledby="processErrors-label"><g:fieldValue bean="${BFWrapperInstance}" field="processErrors"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${BFWrapperInstance?.id}" />
					<g:link class="edit" action="edit" id="${BFWrapperInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
