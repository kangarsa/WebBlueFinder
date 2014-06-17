
<%@ page import="webbluefinder.Properties" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'properties.label', default: 'Properties')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-properties" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="properties.title.label" default="Title" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: propertiesInstance, field: "title")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="properties.hostname.label" default="Hostname" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: propertiesInstance, field: "hostname")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="properties.database.label" default="Database" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: propertiesInstance, field: "database")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="properties.dbuser.label" default="Dbuser" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: propertiesInstance, field: "dbuser")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="properties.dbpass.label" default="Dbpass" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: propertiesInstance, field: "dbpass")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="properties.dbImportedPIA.label" default="Db Imported PIA" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: propertiesInstance, field: "dbImportedPIA")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="properties.dbPassImportedPIA.label" default="Db Pass Imported PIA" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: propertiesInstance, field: "dbPassImportedPIA")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="properties.dbUserImportedPIA.label" default="Db User Imported PIA" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: propertiesInstance, field: "dbUserImportedPIA")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
