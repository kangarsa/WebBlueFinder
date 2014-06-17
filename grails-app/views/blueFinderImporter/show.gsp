
<%@ page import="webbluefinder.BlueFinderImporter" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'blueFinderImporter.label', default: 'BlueFinderImporter')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-blueFinderImporter" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="blueFinderImporter.scene.label" default="Scene" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${blueFinderImporterInstance?.scene?.id}">${blueFinderImporterInstance?.scene?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="blueFinderImporter.processErrors.label" default="Process Errors" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: blueFinderImporterInstance, field: "processErrors")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="blueFinderImporter.dbFrom.label" default="Db From" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: blueFinderImporterInstance, field: "dbFrom")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="blueFinderImporter.dbTo.label" default="Db To" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: blueFinderImporterInstance, field: "dbTo")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="blueFinderImporter.overwrite.label" default="Overwrite" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${blueFinderImporterInstance?.overwrite}" /></td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
