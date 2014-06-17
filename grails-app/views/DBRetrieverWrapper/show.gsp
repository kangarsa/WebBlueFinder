
<%@ page import="webbluefinder.DBRetrieverWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-DBRetrieverWrapper" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="DBRetrieverWrapper.scene.label" default="Scene" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${DBRetrieverWrapperInstance?.scene?.id}">${DBRetrieverWrapperInstance?.scene?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="DBRetrieverWrapper.processErrors.label" default="Process Errors" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: DBRetrieverWrapperInstance, field: "processErrors")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
