
<%@ page import="webbluefinder.Scene" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scene.label', default: 'Scene')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-scene" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scene" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fromType" title="${message(code: 'scene.fromType.label', default: 'From Type')}" />
					
						<g:sortableColumn property="toType" title="${message(code: 'scene.toType.label', default: 'To Type')}" />
					
						<g:sortableColumn property="property" title="${message(code: 'scene.property.label', default: 'Property')}" />
					
						<g:sortableColumn property="queryable" title="${message(code: 'scene.queryable.label', default: 'Queryable')}" />
					
						<th><g:message code="scene.process.label" default="Process" /></th>
					
						<g:sortableColumn property="processStep" title="${message(code: 'scene.processStep.label', default: 'Process Step')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${sceneInstanceList}" status="i" var="sceneInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${sceneInstance.id}">${fieldValue(bean: sceneInstance, field: "fromType")}</g:link></td>
					
						<td>${fieldValue(bean: sceneInstance, field: "toType")}</td>
					
						<td>${fieldValue(bean: sceneInstance, field: "property")}</td>
					
						<td><g:formatBoolean boolean="${sceneInstance.queryable}" /></td>
					
						<td>${fieldValue(bean: sceneInstance, field: "process")}</td>
					
						<td>${fieldValue(bean: sceneInstance, field: "processStep")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${sceneInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
