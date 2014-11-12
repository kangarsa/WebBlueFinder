<%@ page import="webbluefinder.process.DBRetrieverWrapper" %>



			<div class="${hasErrors(bean: DBRetrieverWrapperInstance, field: 'scene', 'error')} ">
				<label for="scene" class="control-label"><g:message code="DBRetrieverWrapper.scene.label" default="Scene" /></label>
				<div>
					<g:select class="form-control" id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${DBRetrieverWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: DBRetrieverWrapperInstance, field: 'scene', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: DBRetrieverWrapperInstance, field: 'processErrors', 'error')} ">
				<label for="processErrors" class="control-label"><g:message code="DBRetrieverWrapper.processErrors.label" default="Process Errors" /></label>
				<div>
					<g:textField class="form-control" name="processErrors" readonly="readonly" value="${DBRetrieverWrapperInstance?.processErrors}"/>
					<span class="help-inline">${hasErrors(bean: DBRetrieverWrapperInstance, field: 'processErrors', 'error')}</span>
				</div>
			</div>

