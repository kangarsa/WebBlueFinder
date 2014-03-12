
<%@ page import="webbluefinder.Properties" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'properties.label', default: 'Properties')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-properties" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-properties" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'properties.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="hostname" title="${message(code: 'properties.hostname.label', default: 'Hostname')}" />
					
						<g:sortableColumn property="database" title="${message(code: 'properties.database.label', default: 'Database')}" />
					
						<g:sortableColumn property="dbuser" title="${message(code: 'properties.dbuser.label', default: 'Dbuser')}" />
					
						<g:sortableColumn property="dbpass" title="${message(code: 'properties.dbpass.label', default: 'Dbpass')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${propertiesInstanceList}" status="i" var="propertiesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${propertiesInstance.id}">${fieldValue(bean: propertiesInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: propertiesInstance, field: "hostname")}</td>
					
						<td>${fieldValue(bean: propertiesInstance, field: "database")}</td>
					
						<td>${fieldValue(bean: propertiesInstance, field: "dbuser")}</td>
					
						<td>${fieldValue(bean: propertiesInstance, field: "dbpass")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${propertiesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
