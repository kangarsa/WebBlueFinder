<%@ page import="webbluefinder.BFEvaluationWrapper" %>



<div class="fieldcontain ${hasErrors(bean: BFEvaluationWrapperInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="BFEvaluationWrapper.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${BFEvaluationWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: BFEvaluationWrapperInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="BFEvaluationWrapper.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${BFEvaluationWrapperInstance?.processErrors}"/>

</div>

