
<%@ page import="webbluefinder.Scene" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scene.label', default: 'Scene')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-scene" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-scene" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list scene">
			
				<g:if test="${sceneInstance?.fromType}">
				<li class="fieldcontain">
					<span id="fromType-label" class="property-label"><g:message code="scene.fromType.label" default="From Type" /></span>
					
						<span class="property-value" aria-labelledby="fromType-label"><g:fieldValue bean="${sceneInstance}" field="fromType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.property}">
				<li class="fieldcontain">
					<span id="property-label" class="property-label"><g:message code="scene.property.label" default="Property" /></span>
					
						<span class="property-value" aria-labelledby="property-label"><g:fieldValue bean="${sceneInstance}" field="property"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.toType}">
				<li class="fieldcontain">
					<span id="toType-label" class="property-label"><g:message code="scene.toType.label" default="To Type" /></span>
					
						<span class="property-value" aria-labelledby="toType-label"><g:fieldValue bean="${sceneInstance}" field="toType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.queryable}">
				<li class="fieldcontain">
					<span id="queryable-label" class="property-label"><g:message code="scene.queryable.label" default="Queryable" /></span>
					
						<span class="property-value" aria-labelledby="queryable-label"><g:formatBoolean boolean="${sceneInstance?.queryable}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.process}">
				<li class="fieldcontain">
					<span id="process-label" class="property-label"><g:message code="scene.process.label" default="Process" /></span>
					
						<span class="property-value" aria-labelledby="process-label"><g:link controller="${sceneInstance?.process?.name}" action="show" id="${sceneInstance?.process?.id}">${sceneInstance?.process?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.processStep}">
				<li class="fieldcontain">
					<span id="processStep-label" class="property-label"><g:message code="scene.processStep.label" default="Process Step" /></span>
					
						<span class="property-value" aria-labelledby="processStep-label"><g:fieldValue bean="${sceneInstance}" field="processStep"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.sceneErrors}">
				<li class="fieldcontain">
					<span id="sceneErrors-label" class="property-label"><g:message code="scene.sceneErrors.label" default="Scene Errors" /></span>
					
						<span class="property-value" aria-labelledby="sceneErrors-label"><g:fieldValue bean="${sceneInstance}" field="sceneErrors"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.processState}">
				<li class="fieldcontain">
					<span id="processState-label" class="property-label"><g:message code="scene.processState.label" default="Process State" /></span>
					
						<span class="property-value" aria-labelledby="processState-label"><g:fieldValue bean="${sceneInstance}" field="processState"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${sceneInstance?.id}" />
					<g:link class="edit" action="edit" id="${sceneInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
