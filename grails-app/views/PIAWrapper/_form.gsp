<%@ page import="webbluefinder.PIAWrapper" %>



<div class="fieldcontain ${hasErrors(bean: PIAWrapperInstance, field: 'error', 'error')} ">
	<label for="error">
		<g:message code="PIAWrapper.error.label" default="Error" />
		
	</label>
	<g:textField name="error" value="${PIAWrapperInstance?.error}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: PIAWrapperInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="PIAWrapper.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${PIAWrapperInstance?.state}"/>
</div>

