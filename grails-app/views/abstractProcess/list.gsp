
<%@ page import="webbluefinder.AbstractProcess" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'abstractProcess.label', default: 'AbstractProcess')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-abstractProcess" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-abstractProcess" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="error" title="${message(code: 'abstractProcess.error.label', default: 'Error')}" />
					
						<g:sortableColumn property="state" title="${message(code: 'abstractProcess.state.label', default: 'State')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${abstractProcessInstanceList}" status="i" var="abstractProcessInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${abstractProcessInstance.id}">${fieldValue(bean: abstractProcessInstance, field: "error")}</g:link></td>
					
						<td>${fieldValue(bean: abstractProcessInstance, field: "state")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${abstractProcessInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
