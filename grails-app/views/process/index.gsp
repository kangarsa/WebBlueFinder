
<%@ page import="webbluefinder.process.Process" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'process.label', default: 'Process')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-process" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="process.scene.label" default="Scene" /></th>
			
				<g:sortableColumn property="processErrors" title="${message(code: 'process.processErrors.label', default: 'Process Errors')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${processInstanceList}" status="i" var="processInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${processInstance.id}">${fieldValue(bean: processInstance, field: "scene")}</g:link></td>
			
				<td>${fieldValue(bean: processInstance, field: "processErrors")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${processInstanceCount}" />
	</div>
</section>

</body>

</html>
