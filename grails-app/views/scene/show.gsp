
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
			
				<g:if test="${sceneInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="scene.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${sceneInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
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
			
				<g:if test="${sceneInstance?.process}">
				<li class="fieldcontain">
					<span id="process-label" class="property-label"><g:message code="scene.process.label" default="Process" /></span>
					
						<span class="property-value" aria-labelledby="process-label"><g:link controller="process" action="show" id="${sceneInstance?.process?.id}">${sceneInstance?.process?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.processState}">
				<li class="fieldcontain">
					<span id="processState-label" class="property-label"><g:message code="scene.processState.label" default="Process State" /></span>
					
						<span class="property-value" aria-labelledby="processState-label"><g:fieldValue bean="${sceneInstance}" field="processState"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.piaMinLimit}">
				<li class="fieldcontain">
					<span id="piaMinLimit-label" class="property-label"><g:message code="scene.piaMinLimit.label" default="Pia Min Limit" /></span>
					
						<span class="property-value" aria-labelledby="piaMinLimit-label"><g:fieldValue bean="${sceneInstance}" field="piaMinLimit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.piaMaxLimit}">
				<li class="fieldcontain">
					<span id="piaMaxLimit-label" class="property-label"><g:message code="scene.piaMaxLimit.label" default="Pia Max Limit" /></span>
					
						<span class="property-value" aria-labelledby="piaMaxLimit-label"><g:fieldValue bean="${sceneInstance}" field="piaMaxLimit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.piaIterationsLimit}">
				<li class="fieldcontain">
					<span id="piaIterationsLimit-label" class="property-label"><g:message code="scene.piaIterationsLimit.label" default="Pia Iterations Limit" /></span>
					
						<span class="property-value" aria-labelledby="piaIterationsLimit-label"><g:fieldValue bean="${sceneInstance}" field="piaIterationsLimit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.piaClean}">
				<li class="fieldcontain">
					<span id="piaClean-label" class="property-label"><g:message code="scene.piaClean.label" default="Pia Clean" /></span>
					
						<span class="property-value" aria-labelledby="piaClean-label"><g:formatBoolean boolean="${sceneInstance?.piaClean}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${sceneInstance?.previousProcess}">
				<li class="fieldcontain">
					<span id="previousProcess-label" class="property-label"><g:message code="scene.previousProcess.label" default="Previous Process" /></span>
					
						<g:each in="${sceneInstance.previousProcess}" var="p">
						<span class="property-value" aria-labelledby="previousProcess-label"><g:link controller="process" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
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
			<fieldset class="actions">
				<g:render template="actions"/>
			</fieldset>
		</div>
	</body>
</html>
