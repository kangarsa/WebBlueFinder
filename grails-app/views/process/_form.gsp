<%@ page import="webbluefinder.Process" %>



<div class="fieldcontain ${hasErrors(bean: processInstance, field: 'processState', 'error')} ">
	<label for="processState">
		<g:message code="process.processState.label" default="Process State" />
		
	</label>
	<g:select name="processState" from="${processInstance.constraints.processState.inList}" value="${processInstance?.processState}" valueMessagePrefix="process.processState" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: processInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="process.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${processInstance?.processErrors}"/>
</div>

