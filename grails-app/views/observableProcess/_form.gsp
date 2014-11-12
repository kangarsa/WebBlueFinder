<%@ page import="webbluefinder.process.ObservableProcess" %>



			<div class="${hasErrors(bean: observableProcessInstance, field: 'observer', 'error')} required">
				<label for="observer" class="control-label"><g:message code="observableProcess.observer.label" default="Observer" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="observer" name="observer.id" from="${webbluefinder.Scene.list()}" optionKey="id" required="" value="${observableProcessInstance?.observer?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: observableProcessInstance, field: 'observer', 'error')}</span>
				</div>
			</div>

