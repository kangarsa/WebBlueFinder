
<%@ page import="webbluefinder.ConnectedPair" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'connectedPair.label', default: 'ConnectedPair')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-connectedPair" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="identifier" title="${message(code: 'connectedPair.identifier.label', default: 'Identifier')}" />
			
				<g:sortableColumn property="page" title="${message(code: 'connectedPair.page.label', default: 'Page')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${connectedPairInstanceList}" status="i" var="connectedPairInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${connectedPairInstance.id}">${fieldValue(bean: connectedPairInstance, field: "identifier")}</g:link></td>
			
				<td>${fieldValue(bean: connectedPairInstance, field: "page")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${connectedPairInstanceCount}" />
	</div>
</section>

</body>

</html>
