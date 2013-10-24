<%@ page import="webbluefinder.Scene" %>



<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'fromType', 'error')} required">
	<label for="fromType">
		<g:message code="scene.fromType.label" default="From Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fromType" required="" value="${sceneInstance?.fromType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'property', 'error')} required">
	<label for="property">
		<g:message code="scene.property.label" default="Property" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="property" required="" value="${sceneInstance?.property}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'toType', 'error')} required">
	<label for="toType">
		<g:message code="scene.toType.label" default="To Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="toType" required="" value="${sceneInstance?.toType}"/>
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
	<g:textField name="sceneErrors" readonly="readonly" value="${sceneInstance?.sceneErrors}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'processState', 'error')} ">
	<label for="processState">
		<g:message code="scene.processState.label" default="Process State" />
		
	</label>
	<g:textField name="processState" value="${sceneInstance?.processState}"/>
</div>

