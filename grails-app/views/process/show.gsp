
<%@ page import="webbluefinder.process.Process" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'process.label', default: 'Process')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-process" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="process.scene.label" default="Scene" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${processInstance?.scene?.id}">${processInstance?.scene?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="process.processErrors.label" default="Process Errors" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: processInstance, field: "processErrors")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
