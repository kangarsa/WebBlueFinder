
<%@ page import="webbluefinder.process.BFEvaluationWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'BFEvaluationWrapper.label', default: 'BFEvaluationWrapper')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-BFEvaluationWrapper" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFEvaluationWrapper.scene.label" default="Scene" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${BFEvaluationWrapperInstance?.scene?.id}">${BFEvaluationWrapperInstance?.scene?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFEvaluationWrapper.processErrors.label" default="Process Errors" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: BFEvaluationWrapperInstance, field: "processErrors")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
