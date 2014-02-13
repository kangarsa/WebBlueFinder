<%@ page import="webbluefinder.BFWrapper" %>



<div class="fieldcontain ${hasErrors(bean: BFWrapperInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="BFWrapper.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${BFWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFWrapperInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="BFWrapper.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${BFWrapperInstance?.processErrors}"/>
</div>

