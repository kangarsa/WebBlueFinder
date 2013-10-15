
<%@ page import="webbluefinder.DBRetrieverWrapper" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-DBRetrieverWrapper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-DBRetrieverWrapper" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="error" title="${message(code: 'DBRetrieverWrapper.error.label', default: 'Error')}" />
					
						<g:sortableColumn property="state" title="${message(code: 'DBRetrieverWrapper.state.label', default: 'State')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${DBRetrieverWrapperInstanceList}" status="i" var="DBRetrieverWrapperInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${DBRetrieverWrapperInstance.id}">${fieldValue(bean: DBRetrieverWrapperInstance, field: "error")}</g:link></td>
					
						<td>${fieldValue(bean: DBRetrieverWrapperInstance, field: "state")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${DBRetrieverWrapperInstanceTotal}" />
			</div>
		</div>
	</body>
</html>