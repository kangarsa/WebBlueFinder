<%@ page import="webbluefinder.DBRetrieverWrapper" %>



<div class="fieldcontain ${hasErrors(bean: DBRetrieverWrapperInstance, field: 'processState', 'error')} ">
	<label for="processState">
		<g:message code="DBRetrieverWrapper.processState.label" default="Process State" />
		
	</label>
	<g:select name="processState" from="${DBRetrieverWrapperInstance.constraints.processState.inList}" value="${DBRetrieverWrapperInstance?.processState}" valueMessagePrefix="DBRetrieverWrapper.processState" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: DBRetrieverWrapperInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="DBRetrieverWrapper.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${DBRetrieverWrapperInstance?.processErrors}"/>
</div>

