
<%@ page import="webbluefinder.Scene" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'scene.label', default: 'Scene')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-scene" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="name" title="${message(code: 'scene.name.label', default: 'Name')}" />
			
				<g:sortableColumn property="fromType" title="${message(code: 'scene.fromType.label', default: 'From Type')}" />
			
				<g:sortableColumn property="property" title="${message(code: 'scene.property.label', default: 'Property')}" />
			
				<g:sortableColumn property="toType" title="${message(code: 'scene.toType.label', default: 'To Type')}" />
			
				<th><g:message code="scene.process.label" default="Process" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${sceneInstanceList}" status="i" var="sceneInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${sceneInstance.id}">${fieldValue(bean: sceneInstance, field: "name")}</g:link></td>
			
				<td>${fieldValue(bean: sceneInstance, field: "fromType")}</td>
			
				<td>${fieldValue(bean: sceneInstance, field: "property")}</td>
			
				<td>${fieldValue(bean: sceneInstance, field: "toType")}</td>
			
				<td>${fieldValue(bean: sceneInstance, field: "process")}</td>
						
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${sceneInstanceCount}" />
	</div>
</section>

</body>

</html>
