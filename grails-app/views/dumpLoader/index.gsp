
<%@ page import="webbluefinder.DumpLoader" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'dumpLoader.label', default: 'DumpLoader')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-dumpLoader" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="dumpLoader.scene.label" default="Scene" /></th>
			
				<g:sortableColumn property="processErrors" title="${message(code: 'dumpLoader.processErrors.label', default: 'Process Errors')}" />
			
				<g:sortableColumn property="dumpPath" title="${message(code: 'dumpLoader.dumpPath.label', default: 'Dump Path')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${dumpLoaderInstanceList}" status="i" var="dumpLoaderInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${dumpLoaderInstance.id}">${fieldValue(bean: dumpLoaderInstance, field: "scene")}</g:link></td>
			
				<td>${fieldValue(bean: dumpLoaderInstance, field: "processErrors")}</td>
			
				<td>${fieldValue(bean: dumpLoaderInstance, field: "dumpPath")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${dumpLoaderInstanceCount}" />
	</div>
</section>

</body>

</html>
