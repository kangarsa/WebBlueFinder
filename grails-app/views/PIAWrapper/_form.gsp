<%@ page import="webbluefinder.PIAWrapper" %>



<div class="fieldcontain ${hasErrors(bean: PIAWrapperInstance, field: 'processState', 'error')} ">
	<label for="processState">
		<g:message code="PIAWrapper.processState.label" default="Process State" />
		
	</label>
	<g:select name="processState" from="${PIAWrapperInstance.constraints.processState.inList}" value="${PIAWrapperInstance?.processState}" valueMessagePrefix="PIAWrapper.processState" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: PIAWrapperInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="PIAWrapper.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${PIAWrapperInstance?.processErrors}"/>
</div>

