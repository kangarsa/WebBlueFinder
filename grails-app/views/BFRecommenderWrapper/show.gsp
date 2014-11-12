
<%@ page import="webbluefinder.process.BFRecommenderWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-BFRecommenderWrapper" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFRecommenderWrapper.scene.label" default="Scene" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${BFRecommenderWrapperInstance?.scene?.id}">${BFRecommenderWrapperInstance?.scene?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFRecommenderWrapper.processErrors.label" default="Process Errors" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: BFRecommenderWrapperInstance, field: "processErrors")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFRecommenderWrapper.bfrFrom.label" default="Bfr From" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: BFRecommenderWrapperInstance, field: "bfrFrom")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFRecommenderWrapper.bfrTo.label" default="Bfr To" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: BFRecommenderWrapperInstance, field: "bfrTo")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFRecommenderWrapper.db.label" default="Db" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: BFRecommenderWrapperInstance, field: "db")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFRecommenderWrapper.maxRecommendations.label" default="Max Recommendations" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: BFRecommenderWrapperInstance, field: "maxRecommendations")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFRecommenderWrapper.neighbour.label" default="Neighbour" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: BFRecommenderWrapperInstance, field: "neighbour")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
