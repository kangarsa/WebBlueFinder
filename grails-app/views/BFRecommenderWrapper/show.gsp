
<%@ page import="webbluefinder.BFRecommenderWrapper" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-BFRecommenderWrapper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-BFRecommenderWrapper" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list BFRecommenderWrapper">
			
				<g:if test="${BFRecommenderWrapperInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="BFRecommenderWrapper.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:link controller="processState" action="show" id="${BFRecommenderWrapperInstance?.state?.id}">${BFRecommenderWrapperInstance?.state?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFRecommenderWrapperInstance?.scene}">
				<li class="fieldcontain">
					<span id="scene-label" class="property-label"><g:message code="BFRecommenderWrapper.scene.label" default="Scene" /></span>
					
						<span class="property-value" aria-labelledby="scene-label"><g:link controller="scene" action="show" id="${BFRecommenderWrapperInstance?.scene?.id}">${BFRecommenderWrapperInstance?.scene?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFRecommenderWrapperInstance?.processErrors}">
				<li class="fieldcontain">
					<span id="processErrors-label" class="property-label"><g:message code="BFRecommenderWrapper.processErrors.label" default="Process Errors" /></span>
					
						<span class="property-value" aria-labelledby="processErrors-label"><g:fieldValue bean="${BFRecommenderWrapperInstance}" field="processErrors"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFRecommenderWrapperInstance?.bfrFrom}">
				<li class="fieldcontain">
					<span id="bfrFrom-label" class="property-label"><g:message code="BFRecommenderWrapper.bfrFrom.label" default="Bfr From" /></span>
					
						<span class="property-value" aria-labelledby="bfrFrom-label"><g:fieldValue bean="${BFRecommenderWrapperInstance}" field="bfrFrom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFRecommenderWrapperInstance?.bfrTo}">
				<li class="fieldcontain">
					<span id="bfrTo-label" class="property-label"><g:message code="BFRecommenderWrapper.bfrTo.label" default="Bfr To" /></span>
					
						<span class="property-value" aria-labelledby="bfrTo-label"><g:fieldValue bean="${BFRecommenderWrapperInstance}" field="bfrTo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFRecommenderWrapperInstance?.db}">
				<li class="fieldcontain">
					<span id="db-label" class="property-label"><g:message code="BFRecommenderWrapper.db.label" default="Db" /></span>
					
						<span class="property-value" aria-labelledby="db-label"><g:fieldValue bean="${BFRecommenderWrapperInstance}" field="db"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFRecommenderWrapperInstance?.maxRecommendations}">
				<li class="fieldcontain">
					<span id="maxRecommendations-label" class="property-label"><g:message code="BFRecommenderWrapper.maxRecommendations.label" default="Max Recommendations" /></span>
					
						<span class="property-value" aria-labelledby="maxRecommendations-label"><g:fieldValue bean="${BFRecommenderWrapperInstance}" field="maxRecommendations"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${BFRecommenderWrapperInstance?.neighbour}">
				<li class="fieldcontain">
					<span id="neighbour-label" class="property-label"><g:message code="BFRecommenderWrapper.neighbour.label" default="Neighbour" /></span>
					
						<span class="property-value" aria-labelledby="neighbour-label"><g:fieldValue bean="${BFRecommenderWrapperInstance}" field="neighbour"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${BFRecommenderWrapperInstance?.id}" />
					<g:link class="edit" action="edit" id="${BFRecommenderWrapperInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
