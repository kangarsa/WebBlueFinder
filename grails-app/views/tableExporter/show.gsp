
<%@ page import="webbluefinder.TableExporter" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tableExporter.label', default: 'TableExporter')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tableExporter" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tableExporter" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tableExporter">
			
				<g:if test="${tableExporterInstance?.scene}">
				<li class="fieldcontain">
					<span id="scene-label" class="property-label"><g:message code="tableExporter.scene.label" default="Scene" /></span>
					
						<span class="property-value" aria-labelledby="scene-label"><g:link controller="scene" action="show" id="${tableExporterInstance?.scene?.id}">${tableExporterInstance?.scene?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tableExporterInstance?.processErrors}">
				<li class="fieldcontain">
					<span id="processErrors-label" class="property-label"><g:message code="tableExporter.processErrors.label" default="Process Errors" /></span>
					
						<span class="property-value" aria-labelledby="processErrors-label"><g:fieldValue bean="${tableExporterInstance}" field="processErrors"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tableExporterInstance?.dbFrom}">
				<li class="fieldcontain">
					<span id="dbFrom-label" class="property-label"><g:message code="tableExporter.dbFrom.label" default="Db From" /></span>
					
						<span class="property-value" aria-labelledby="dbFrom-label"><g:fieldValue bean="${tableExporterInstance}" field="dbFrom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tableExporterInstance?.dbTo}">
				<li class="fieldcontain">
					<span id="dbTo-label" class="property-label"><g:message code="tableExporter.dbTo.label" default="Db To" /></span>
					
						<span class="property-value" aria-labelledby="dbTo-label"><g:fieldValue bean="${tableExporterInstance}" field="dbTo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tableExporterInstance?.tableFrom}">
				<li class="fieldcontain">
					<span id="tableFrom-label" class="property-label"><g:message code="tableExporter.tableFrom.label" default="Table From" /></span>
					
						<span class="property-value" aria-labelledby="tableFrom-label"><g:fieldValue bean="${tableExporterInstance}" field="tableFrom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tableExporterInstance?.tableTo}">
				<li class="fieldcontain">
					<span id="tableTo-label" class="property-label"><g:message code="tableExporter.tableTo.label" default="Table To" /></span>
					
						<span class="property-value" aria-labelledby="tableTo-label"><g:fieldValue bean="${tableExporterInstance}" field="tableTo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tableExporterInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tableExporterInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
