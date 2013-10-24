<%@ page import="webbluefinder.BFWrapper" %>



<div class="fieldcontain ${hasErrors(bean: BFWrapperInstance, field: 'processState', 'error')} ">
	<label for="processState">
		<g:message code="BFWrapper.processState.label" default="Process State" />
		
	</label>
	<g:select name="processState" from="${BFWrapperInstance.constraints.processState.inList}" value="${BFWrapperInstance?.processState}" valueMessagePrefix="BFWrapper.processState" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFWrapperInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="BFWrapper.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${BFWrapperInstance?.processErrors}"/>
</div>

