
<%@ page import="webbluefinder.DumpLoader" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'dumpLoader.label', default: 'DumpLoader')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-dumpLoader" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="dumpLoader.scene.label" default="Scene" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${dumpLoaderInstance?.scene?.id}">${dumpLoaderInstance?.scene?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="dumpLoader.processErrors.label" default="Process Errors" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: dumpLoaderInstance, field: "processErrors")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="dumpLoader.dumpPath.label" default="Dump Path" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: dumpLoaderInstance, field: "dumpPath")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
