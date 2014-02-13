
<%@ page import="webbluefinder.Process" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'process.label', default: 'Process')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-process" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-process" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="process.state.label" default="State" /></th>
					
						<th><g:message code="process.scene.label" default="Scene" /></th>
					
						<g:sortableColumn property="processErrors" title="${message(code: 'process.processErrors.label', default: 'Process Errors')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${processInstanceList}" status="i" var="processInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${processInstance.id}">${fieldValue(bean: processInstance, field: "state")}</g:link></td>
					
						<td>${fieldValue(bean: processInstance, field: "scene")}</td>
					
						<td>${fieldValue(bean: processInstance, field: "processErrors")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${processInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
