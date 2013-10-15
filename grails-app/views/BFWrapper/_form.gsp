<%@ page import="webbluefinder.BFWrapper" %>



<div class="fieldcontain ${hasErrors(bean: BFWrapperInstance, field: 'error', 'error')} ">
	<label for="error">
		<g:message code="BFWrapper.error.label" default="Error" />
		
	</label>
	<g:textField name="error" value="${BFWrapperInstance?.error}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: BFWrapperInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="BFWrapper.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${BFWrapperInstance?.state}"/>
</div>

