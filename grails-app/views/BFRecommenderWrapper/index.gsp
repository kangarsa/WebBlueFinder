
<%@ page import="webbluefinder.process.BFRecommenderWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-BFRecommenderWrapper" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="BFRecommenderWrapper.scene.label" default="Scene" /></th>
			
				<g:sortableColumn property="processErrors" title="${message(code: 'BFRecommenderWrapper.processErrors.label', default: 'Process Errors')}" />
			
				<g:sortableColumn property="bfrFrom" title="${message(code: 'BFRecommenderWrapper.bfrFrom.label', default: 'Bfr From')}" />
			
				<g:sortableColumn property="bfrTo" title="${message(code: 'BFRecommenderWrapper.bfrTo.label', default: 'Bfr To')}" />
			
				<g:sortableColumn property="db" title="${message(code: 'BFRecommenderWrapper.db.label', default: 'Db')}" />
			
				<g:sortableColumn property="maxRecommendations" title="${message(code: 'BFRecommenderWrapper.maxRecommendations.label', default: 'Max Recommendations')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${BFRecommenderWrapperInstanceList}" status="i" var="BFRecommenderWrapperInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${BFRecommenderWrapperInstance.id}">${fieldValue(bean: BFRecommenderWrapperInstance, field: "scene")}</g:link></td>
			
				<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "processErrors")}</td>
			
				<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "bfrFrom")}</td>
			
				<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "bfrTo")}</td>
			
				<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "db")}</td>
			
				<td>${fieldValue(bean: BFRecommenderWrapperInstance, field: "maxRecommendations")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${BFRecommenderWrapperInstanceCount}" />
	</div>
</section>

</body>

</html>
