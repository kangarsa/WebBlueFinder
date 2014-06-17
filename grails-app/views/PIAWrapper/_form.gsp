<%@ page import="webbluefinder.PIAWrapper" %>



			<div class="${hasErrors(bean: PIAWrapperInstance, field: 'scene', 'error')} ">
				<label for="scene" class="control-label"><g:message code="PIAWrapper.scene.label" default="Scene" /></label>
				<div>
					<g:select class="form-control" id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${PIAWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: PIAWrapperInstance, field: 'scene', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: PIAWrapperInstance, field: 'processErrors', 'error')} ">
				<label for="processErrors" class="control-label"><g:message code="PIAWrapper.processErrors.label" default="Process Errors" /></label>
				<div>
					<g:textField class="form-control" name="processErrors" readonly="readonly" value="${PIAWrapperInstance?.processErrors}"/>
					<span class="help-inline">${hasErrors(bean: PIAWrapperInstance, field: 'processErrors', 'error')}</span>
				</div>
			</div>

