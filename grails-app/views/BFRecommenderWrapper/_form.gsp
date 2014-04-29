<%@ page import="webbluefinder.BFRecommenderWrapper" %>



<div class="fieldcontain ${hasErrors(bean: BFRecommenderWrapperInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="BFRecommenderWrapper.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${BFRecommenderWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFRecommenderWrapperInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="BFRecommenderWrapper.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${BFRecommenderWrapperInstance?.processErrors}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFRecommenderWrapperInstance, field: 'bfrFrom', 'error')} ">
	<label for="bfrFrom">
		<g:message code="BFRecommenderWrapper.bfrFrom.label" default="Bfr From" />
		
	</label>
	<g:textField name="bfrFrom" value="${BFRecommenderWrapperInstance?.bfrFrom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFRecommenderWrapperInstance, field: 'bfrTo', 'error')} ">
	<label for="bfrTo">
		<g:message code="BFRecommenderWrapper.bfrTo.label" default="Bfr To" />
		
	</label>
	<g:textField name="bfrTo" value="${BFRecommenderWrapperInstance?.bfrTo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFRecommenderWrapperInstance, field: 'db', 'error')} ">
	<label for="db">
		<g:message code="BFRecommenderWrapper.db.label" default="Db" />
		
	</label>
	<g:textField name="db" value="${BFRecommenderWrapperInstance?.db}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFRecommenderWrapperInstance, field: 'maxRecommendations', 'error')} required">
	<label for="maxRecommendations">
		<g:message code="BFRecommenderWrapper.maxRecommendations.label" default="Max Recommendations" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxRecommendations" type="number" value="${BFRecommenderWrapperInstance.maxRecommendations}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFRecommenderWrapperInstance, field: 'neighbour', 'error')} required">
	<label for="neighbour">
		<g:message code="BFRecommenderWrapper.neighbour.label" default="Neighbour" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="neighbour" type="number" value="${BFRecommenderWrapperInstance.neighbour}" required=""/>
</div>

