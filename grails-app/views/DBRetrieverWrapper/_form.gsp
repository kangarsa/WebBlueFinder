<%@ page import="webbluefinder.DBRetrieverWrapper" %>



<div class="fieldcontain ${hasErrors(bean: DBRetrieverWrapperInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="DBRetrieverWrapper.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${DBRetrieverWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: DBRetrieverWrapperInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="DBRetrieverWrapper.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${DBRetrieverWrapperInstance?.processErrors}"/>
</div>

