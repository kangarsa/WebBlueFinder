<%@ page import="webbluefinder.BFWrapper" %>



<div class="fieldcontain ${hasErrors(bean: BFWrapperInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="BFWrapper.state.label" default="State" />
		
	</label>
	<g:select name="state" from="${BFWrapperInstance.constraints.state.inList}" value="${BFWrapperInstance?.state}" valueMessagePrefix="BFWrapper.state" noSelection="['': '']"/>
</div>

