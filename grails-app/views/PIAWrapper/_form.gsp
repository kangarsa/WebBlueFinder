<%@ page import="webbluefinder.PIAWrapper" %>



<div class="fieldcontain ${hasErrors(bean: PIAWrapperInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="PIAWrapper.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${PIAWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: PIAWrapperInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="PIAWrapper.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${PIAWrapperInstance?.processErrors}"/>
</div>

