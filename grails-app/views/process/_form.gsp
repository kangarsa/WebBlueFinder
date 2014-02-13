<%@ page import="webbluefinder.Process" %>



<div class="fieldcontain ${hasErrors(bean: processInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="process.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${processInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: processInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="process.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${processInstance?.processErrors}"/>
</div>

