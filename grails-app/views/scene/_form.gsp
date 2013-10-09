<%@ page import="webbluefinder.Scene" %>



<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'fromType', 'error')} required">
	<label for="fromType">
		<g:message code="scene.fromType.label" default="From Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fromType" required="" value="${sceneInstance?.fromType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'toType', 'error')} required">
	<label for="toType">
		<g:message code="scene.toType.label" default="To Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="toType" required="" value="${sceneInstance?.toType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'property', 'error')} required">
	<label for="property">
		<g:message code="scene.property.label" default="Property" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="property" required="" value="${sceneInstance?.property}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'queryable', 'error')} ">
	<label for="queryable">
		<g:message code="scene.queryable.label" default="Queryable" />
		
	</label>
	<g:checkBox name="queryable" value="${sceneInstance?.queryable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'process', 'error')} ">
	<label for="process">
		<g:message code="scene.process.label" default="Process" />
		
	</label>
	<g:select id="process" name="process.id" from="${webbluefinder.AbstractProcess.list()}" optionKey="id" value="${sceneInstance?.process?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

