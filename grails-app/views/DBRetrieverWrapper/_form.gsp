<%@ page import="webbluefinder.DBRetrieverWrapper" %>



<div class="fieldcontain ${hasErrors(bean: DBRetrieverWrapperInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="DBRetrieverWrapper.state.label" default="State" />
		
	</label>
	<g:select name="state" from="${DBRetrieverWrapperInstance.constraints.state.inList}" value="${DBRetrieverWrapperInstance?.state}" valueMessagePrefix="DBRetrieverWrapper.state" noSelection="['': '']"/>
</div>

