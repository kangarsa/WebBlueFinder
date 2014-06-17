
<%@ page import="webbluefinder.Properties" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'properties.label', default: 'Properties')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-properties" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="title" title="${message(code: 'properties.title.label', default: 'Title')}" />
			
				<g:sortableColumn property="hostname" title="${message(code: 'properties.hostname.label', default: 'Hostname')}" />
			
				<g:sortableColumn property="database" title="${message(code: 'properties.database.label', default: 'Database')}" />
			
				<g:sortableColumn property="dbuser" title="${message(code: 'properties.dbuser.label', default: 'Dbuser')}" />
			
				<g:sortableColumn property="dbpass" title="${message(code: 'properties.dbpass.label', default: 'Dbpass')}" />
			
				<g:sortableColumn property="dbImportedPIA" title="${message(code: 'properties.dbImportedPIA.label', default: 'Db Imported PIA')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${propertiesInstanceList}" status="i" var="propertiesInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${propertiesInstance.id}">${fieldValue(bean: propertiesInstance, field: "title")}</g:link></td>
			
				<td>${fieldValue(bean: propertiesInstance, field: "hostname")}</td>
			
				<td>${fieldValue(bean: propertiesInstance, field: "database")}</td>
			
				<td>${fieldValue(bean: propertiesInstance, field: "dbuser")}</td>
			
				<td>${fieldValue(bean: propertiesInstance, field: "dbpass")}</td>
			
				<td>${fieldValue(bean: propertiesInstance, field: "dbImportedPIA")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${propertiesInstanceCount}" />
	</div>
</section>

</body>

</html>
