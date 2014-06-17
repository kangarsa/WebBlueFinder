
<%@ page import="webbluefinder.BFEvaluationWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'BFEvaluationWrapper.label', default: 'BFEvaluationWrapper')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-BFEvaluationWrapper" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="BFEvaluationWrapper.scene.label" default="Scene" /></th>
			
				<g:sortableColumn property="processErrors" title="${message(code: 'BFEvaluationWrapper.processErrors.label', default: 'Process Errors')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${BFEvaluationWrapperInstanceList}" status="i" var="BFEvaluationWrapperInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${BFEvaluationWrapperInstance.id}">${fieldValue(bean: BFEvaluationWrapperInstance, field: "scene")}</g:link></td>
			
				<td>${fieldValue(bean: BFEvaluationWrapperInstance, field: "processErrors")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${BFEvaluationWrapperInstanceCount}" />
	</div>
</section>

</body>

</html>
