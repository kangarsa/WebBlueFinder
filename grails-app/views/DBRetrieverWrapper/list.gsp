
<%@ page import="webbluefinder.process.DBRetrieverWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-DBRetrieverWrapper" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="DBRetrieverWrapper.scene.label" default="Scene" /></th>
			
				<g:sortableColumn property="processErrors" title="${message(code: 'DBRetrieverWrapper.processErrors.label', default: 'Process Errors')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${DBRetrieverWrapperInstanceList}" status="i" var="DBRetrieverWrapperInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${DBRetrieverWrapperInstance.id}">${fieldValue(bean: DBRetrieverWrapperInstance, field: "scene")}</g:link></td>
			
				<td>${fieldValue(bean: DBRetrieverWrapperInstance, field: "processErrors")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${DBRetrieverWrapperInstanceCount}" />
	</div>
</section>

</body>

</html>
