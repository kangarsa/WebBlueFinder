<%@ page import="webbluefinder.BFEvaluationWrapper" %>



			<div class="${hasErrors(bean: BFEvaluationWrapperInstance, field: 'scene', 'error')} ">
				<label for="scene" class="control-label"><g:message code="BFEvaluationWrapper.scene.label" default="Scene" /></label>
				<div>
					<g:select class="form-control" id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${BFEvaluationWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: BFEvaluationWrapperInstance, field: 'scene', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: BFEvaluationWrapperInstance, field: 'processErrors', 'error')} ">
				<label for="processErrors" class="control-label"><g:message code="BFEvaluationWrapper.processErrors.label" default="Process Errors" /></label>
				<div>
					<g:textField class="form-control" name="processErrors" readonly="readonly" value="${BFEvaluationWrapperInstance?.processErrors}"/>
					<span class="help-inline">${hasErrors(bean: BFEvaluationWrapperInstance, field: 'processErrors', 'error')}</span>
				</div>
			</div>

