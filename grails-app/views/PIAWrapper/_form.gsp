<%@ page import="webbluefinder.PIAWrapper" %>



<div class="fieldcontain ${hasErrors(bean: PIAWrapperInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="PIAWrapper.state.label" default="State" />
		
	</label>
	<g:select name="state" from="${PIAWrapperInstance.constraints.state.inList}" value="${PIAWrapperInstance?.state}" valueMessagePrefix="PIAWrapper.state" noSelection="['': '']"/>
</div>

