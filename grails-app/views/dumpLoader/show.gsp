
<%@ page import="webbluefinder.DumpLoader" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dumpLoader.label', default: 'DumpLoader')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-dumpLoader" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dumpLoader" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dumpLoader">
			
				<g:if test="${dumpLoaderInstance?.scene}">
				<li class="fieldcontain">
					<span id="scene-label" class="property-label"><g:message code="dumpLoader.scene.label" default="Scene" /></span>
					
						<span class="property-value" aria-labelledby="scene-label"><g:link controller="scene" action="show" id="${dumpLoaderInstance?.scene?.id}">${dumpLoaderInstance?.scene?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${dumpLoaderInstance?.processErrors}">
				<li class="fieldcontain">
					<span id="processErrors-label" class="property-label"><g:message code="dumpLoader.processErrors.label" default="Process Errors" /></span>
					
						<span class="property-value" aria-labelledby="processErrors-label"><g:fieldValue bean="${dumpLoaderInstance}" field="processErrors"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dumpLoaderInstance?.dumpPath}">
				<li class="fieldcontain">
					<span id="dumpPath-label" class="property-label"><g:message code="dumpLoader.dumpPath.label" default="Dump Path" /></span>
					
						<span class="property-value" aria-labelledby="dumpPath-label"><g:fieldValue bean="${dumpLoaderInstance}" field="dumpPath"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:dumpLoaderInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${dumpLoaderInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
