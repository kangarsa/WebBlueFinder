
<%@ page import="webbluefinder.Process" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'process.label', default: 'Process')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-process" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-process" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list process">
			
				<g:if test="${processInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="process.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:link controller="processState" action="show" id="${processInstance?.state?.id}">${processInstance?.state?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${processInstance?.scene}">
				<li class="fieldcontain">
					<span id="scene-label" class="property-label"><g:message code="process.scene.label" default="Scene" /></span>
					
						<span class="property-value" aria-labelledby="scene-label"><g:link controller="scene" action="show" id="${processInstance?.scene?.id}">${processInstance?.scene?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${processInstance?.processErrors}">
				<li class="fieldcontain">
					<span id="processErrors-label" class="property-label"><g:message code="process.processErrors.label" default="Process Errors" /></span>
					
						<span class="property-value" aria-labelledby="processErrors-label"><g:fieldValue bean="${processInstance}" field="processErrors"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${processInstance?.id}" />
					<g:link class="edit" action="edit" id="${processInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
