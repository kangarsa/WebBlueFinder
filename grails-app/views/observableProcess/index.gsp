
<%@ page import="webbluefinder.process.ObservableProcess" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'observableProcess.label', default: 'ObservableProcess')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-observableProcess" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="observableProcess.observer.label" default="Observer" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${observableProcessInstanceList}" status="i" var="observableProcessInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${observableProcessInstance.id}">${fieldValue(bean: observableProcessInstance, field: "observer")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${observableProcessInstanceCount}" />
	</div>
</section>

</body>

</html>
