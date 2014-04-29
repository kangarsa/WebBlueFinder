
<%@ page import="webbluefinder.BFRecommenderWrapper" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-BFRecommenderWrapper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-BFRecommenderWrapper" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="BFRecommenderWrapper.state.label" default="State" /></th>
					
						<th><g:message code="BFRecommenderWrapper.scene.label" default="Scene" /></th>
					
						<g:sortableColumn property="processErrors" title="${message(code: 'BFRecommenderWrapper.processErrors.label', default: 'Process Errors')}" />
					
						<g:sortableColumn property="bfrFrom" title="${message(code: 'BFRecommenderWrapper.bfrFrom.label', default: 'Bfr From')}" />
					
						<g:sortableColumn property="bfrTo" title="${message(code: 'BFRecommenderWrapper.bfrTo.label', default: 'Bfr To')}" />
					
						<g:sortableColumn property="db" title="${message(code: 'BFRecommenderWrapper.db.label', default: 'Db')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${BFRecommenderWrapperInstanceList}" status="i" var="BFRecommenderWrapperInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${BFRecommenderWrapperInstance.id}">${fieldValue(bean: BFRecommenderWrapperInstance, field: "state")}</g:link></td>
					
						<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "scene")}</td>
					
						<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "processErrors")}</td>
					
						<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "bfrFrom")}</td>
					
						<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "bfrTo")}</td>
					
						<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "db")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${BFRecommenderWrapperInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
