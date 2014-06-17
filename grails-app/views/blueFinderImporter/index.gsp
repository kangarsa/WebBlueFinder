
<%@ page import="webbluefinder.BlueFinderImporter" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'blueFinderImporter.label', default: 'BlueFinderImporter')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-blueFinderImporter" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="blueFinderImporter.scene.label" default="Scene" /></th>
			
				<g:sortableColumn property="processErrors" title="${message(code: 'blueFinderImporter.processErrors.label', default: 'Process Errors')}" />
			
				<g:sortableColumn property="dbFrom" title="${message(code: 'blueFinderImporter.dbFrom.label', default: 'Db From')}" />
			
				<g:sortableColumn property="dbTo" title="${message(code: 'blueFinderImporter.dbTo.label', default: 'Db To')}" />
			
				<g:sortableColumn property="overwrite" title="${message(code: 'blueFinderImporter.overwrite.label', default: 'Overwrite')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${blueFinderImporterInstanceList}" status="i" var="blueFinderImporterInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${blueFinderImporterInstance.id}">${fieldValue(bean: blueFinderImporterInstance, field: "scene")}</g:link></td>
			
				<td>${fieldValue(bean: blueFinderImporterInstance, field: "processErrors")}</td>
			
				<td>${fieldValue(bean: blueFinderImporterInstance, field: "dbFrom")}</td>
			
				<td>${fieldValue(bean: blueFinderImporterInstance, field: "dbTo")}</td>
			
				<td><g:formatBoolean boolean="${blueFinderImporterInstance.overwrite}" /></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${blueFinderImporterInstanceCount}" />
	</div>
</section>

</body>

</html>
