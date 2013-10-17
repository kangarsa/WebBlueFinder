<%@ page import="webbluefinder.AbstractProcess" %>



<div class="fieldcontain ${hasErrors(bean: abstractProcessInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="abstractProcess.state.label" default="State" />
		
	</label>
	<g:select name="state" from="${abstractProcessInstance.constraints.state.inList}" value="${abstractProcessInstance?.state}" valueMessagePrefix="abstractProcess.state" noSelection="['': '']"/>
</div>

