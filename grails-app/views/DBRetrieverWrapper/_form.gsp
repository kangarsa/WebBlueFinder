<%@ page import="webbluefinder.DBRetrieverWrapper" %>



<div class="fieldcontain ${hasErrors(bean: DBRetrieverWrapperInstance, field: 'error', 'error')} ">
	<label for="error">
		<g:message code="DBRetrieverWrapper.error.label" default="Error" />
		
	</label>
	<g:textField name="error" value="${DBRetrieverWrapperInstance?.error}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: DBRetrieverWrapperInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="DBRetrieverWrapper.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${DBRetrieverWrapperInstance?.state}"/>
</div>

