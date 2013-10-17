<%@ page import="webbluefinder.Scene" %>



<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'fromType', 'error')} ">
	<label for="fromType">
		<g:message code="scene.fromType.label" default="From Type" />
		
	</label>
	<g:textField name="fromType" value="${sceneInstance?.fromType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'processStep', 'error')} required">
	<label for="processStep">
		<g:message code="scene.processStep.label" default="Process Step" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="processStep" type="number" value="${sceneInstance.processStep}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'property', 'error')} ">
	<label for="property">
		<g:message code="scene.property.label" default="Property" />
		
	</label>
	<g:textField name="property" value="${sceneInstance?.property}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'queryable', 'error')} ">
	<label for="queryable">
		<g:message code="scene.queryable.label" default="Queryable" />
		
	</label>
	<g:checkBox name="queryable" value="${sceneInstance?.queryable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'sceneErrors', 'error')} ">
	<label for="sceneErrors">
		<g:message code="scene.sceneErrors.label" default="Scene Errors" />
		
	</label>
	<g:textField name="sceneErrors" value="${sceneInstance?.sceneErrors}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'toType', 'error')} ">
	<label for="toType">
		<g:message code="scene.toType.label" default="To Type" />
		
	</label>
	<g:textField name="toType" value="${sceneInstance?.toType}"/>
</div>

