<%@ page import="webbluefinder.Process" %>



			<div class="${hasErrors(bean: processInstance, field: 'scene', 'error')} ">
				<label for="scene" class="control-label"><g:message code="process.scene.label" default="Scene" /></label>
				<div>
					<g:select class="form-control" id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${processInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: processInstance, field: 'scene', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: processInstance, field: 'processErrors', 'error')} ">
				<label for="processErrors" class="control-label"><g:message code="process.processErrors.label" default="Process Errors" /></label>
				<div>
					<g:textField class="form-control" name="processErrors" readonly="readonly" value="${processInstance?.processErrors}"/>
					<span class="help-inline">${hasErrors(bean: processInstance, field: 'processErrors', 'error')}</span>
				</div>
			</div>

