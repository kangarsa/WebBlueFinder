
<%@ page import="webbluefinder.BlueFinderImporter" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'blueFinderImporter.label', default: 'BlueFinderImporter')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-blueFinderImporter" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-blueFinderImporter" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list blueFinderImporter">
			
				<g:if test="${blueFinderImporterInstance?.scene}">
				<li class="fieldcontain">
					<span id="scene-label" class="property-label"><g:message code="blueFinderImporter.scene.label" default="Scene" /></span>
					
						<span class="property-value" aria-labelledby="scene-label"><g:link controller="scene" action="show" id="${blueFinderImporterInstance?.scene?.id}">${blueFinderImporterInstance?.scene?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${blueFinderImporterInstance?.processErrors}">
				<li class="fieldcontain">
					<span id="processErrors-label" class="property-label"><g:message code="blueFinderImporter.processErrors.label" default="Process Errors" /></span>
					
						<span class="property-value" aria-labelledby="processErrors-label"><g:fieldValue bean="${blueFinderImporterInstance}" field="processErrors"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blueFinderImporterInstance?.dbFrom}">
				<li class="fieldcontain">
					<span id="dbFrom-label" class="property-label"><g:message code="blueFinderImporter.dbFrom.label" default="Db From" /></span>
					
						<span class="property-value" aria-labelledby="dbFrom-label"><g:fieldValue bean="${blueFinderImporterInstance}" field="dbFrom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blueFinderImporterInstance?.dbTo}">
				<li class="fieldcontain">
					<span id="dbTo-label" class="property-label"><g:message code="blueFinderImporter.dbTo.label" default="Db To" /></span>
					
						<span class="property-value" aria-labelledby="dbTo-label"><g:fieldValue bean="${blueFinderImporterInstance}" field="dbTo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${blueFinderImporterInstance?.overwrite}">
				<li class="fieldcontain">
					<span id="overwrite-label" class="property-label"><g:message code="blueFinderImporter.overwrite.label" default="Overwrite" /></span>
					
						<span class="property-value" aria-labelledby="overwrite-label"><g:formatBoolean boolean="${blueFinderImporterInstance?.overwrite}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:blueFinderImporterInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${blueFinderImporterInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
