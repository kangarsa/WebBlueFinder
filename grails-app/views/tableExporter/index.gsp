
<%@ page import="webbluefinder.TableExporter" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tableExporter.label', default: 'TableExporter')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tableExporter" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tableExporter" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="tableExporter.scene.label" default="Scene" /></th>
					
						<g:sortableColumn property="processErrors" title="${message(code: 'tableExporter.processErrors.label', default: 'Process Errors')}" />
					
						<g:sortableColumn property="dbFrom" title="${message(code: 'tableExporter.dbFrom.label', default: 'Db From')}" />
					
						<g:sortableColumn property="dbTo" title="${message(code: 'tableExporter.dbTo.label', default: 'Db To')}" />
					
						<g:sortableColumn property="tableFrom" title="${message(code: 'tableExporter.tableFrom.label', default: 'Table From')}" />
					
						<g:sortableColumn property="tableTo" title="${message(code: 'tableExporter.tableTo.label', default: 'Table To')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tableExporterInstanceList}" status="i" var="tableExporterInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tableExporterInstance.id}">${fieldValue(bean: tableExporterInstance, field: "scene")}</g:link></td>
					
						<td>${fieldValue(bean: tableExporterInstance, field: "processErrors")}</td>
					
						<td>${fieldValue(bean: tableExporterInstance, field: "dbFrom")}</td>
					
						<td>${fieldValue(bean: tableExporterInstance, field: "dbTo")}</td>
					
						<td>${fieldValue(bean: tableExporterInstance, field: "tableFrom")}</td>
					
						<td>${fieldValue(bean: tableExporterInstance, field: "tableTo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tableExporterInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
