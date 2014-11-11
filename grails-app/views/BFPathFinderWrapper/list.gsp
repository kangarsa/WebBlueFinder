
<%@ page import="webbluefinder.BFPathFinderWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'BFPathFinderWrapper.label', default: 'BFPathFinderWrapper')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-BFPathFinderWrapper" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="BFPathFinderWrapper.scene.label" default="Scene" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${BFPathFinderWrapperInstanceList}" status="i" var="BFPathFinderWrapperInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${BFPathFinderWrapperInstance.id}">${fieldValue(bean: BFPathFinderWrapperInstance, field: "scene")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${BFPathFinderWrapperInstanceCount}" />
	</div>
</section>

</body>

</html>
