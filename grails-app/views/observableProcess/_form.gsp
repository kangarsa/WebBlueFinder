<%@ page import="webbluefinder.ObservableProcess" %>



<div class="fieldcontain ${hasErrors(bean: observableProcessInstance, field: 'observer', 'error')} required">
	<label for="observer">
		<g:message code="observableProcess.observer.label" default="Observer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="observer" name="observer.id" from="${webbluefinder.Scene.list()}" optionKey="id" required="" value="${observableProcessInstance?.observer?.id}" class="many-to-one"/>
</div>

