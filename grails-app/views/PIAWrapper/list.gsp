
<%@ page import="webbluefinder.PIAWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'PIAWrapper.label', default: 'PIAWrapper')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-PIAWrapper" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="PIAWrapper.scene.label" default="Scene" /></th>
			
				<g:sortableColumn property="processErrors" title="${message(code: 'PIAWrapper.processErrors.label', default: 'Process Errors')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${PIAWrapperInstanceList}" status="i" var="PIAWrapperInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${PIAWrapperInstance.id}">${fieldValue(bean: PIAWrapperInstance, field: "scene")}</g:link></td>
			
				<td>${fieldValue(bean: PIAWrapperInstance, field: "processErrors")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${PIAWrapperInstanceCount}" />
	</div>
</section>

</body>

</html>
