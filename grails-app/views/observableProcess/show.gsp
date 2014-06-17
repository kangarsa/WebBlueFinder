
<%@ page import="webbluefinder.ObservableProcess" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'observableProcess.label', default: 'ObservableProcess')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-observableProcess" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="observableProcess.observer.label" default="Observer" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${observableProcessInstance?.observer?.id}">${observableProcessInstance?.observer?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
