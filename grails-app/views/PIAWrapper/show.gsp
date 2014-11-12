
<%@ page import="webbluefinder.process.PIAWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'PIAWrapper.label', default: 'PIAWrapper')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-PIAWrapper" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="PIAWrapper.scene.label" default="Scene" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${PIAWrapperInstance?.scene?.id}">${PIAWrapperInstance?.scene?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="PIAWrapper.processErrors.label" default="Process Errors" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: PIAWrapperInstance, field: "processErrors")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
