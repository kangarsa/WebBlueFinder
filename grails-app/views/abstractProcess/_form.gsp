<%@ page import="webbluefinder.AbstractProcess" %>



<div class="fieldcontain ${hasErrors(bean: abstractProcessInstance, field: 'error', 'error')} ">
	<label for="error">
		<g:message code="abstractProcess.error.label" default="Error" />
		
	</label>
	<g:textField name="error" value="${abstractProcessInstance?.error}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: abstractProcessInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="abstractProcess.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${abstractProcessInstance?.state}"/>
</div>

