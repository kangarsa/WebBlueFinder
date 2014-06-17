<%@ page import="webbluefinder.DumpLoader" %>



			<div class="${hasErrors(bean: dumpLoaderInstance, field: 'scene', 'error')} ">
				<label for="scene" class="control-label"><g:message code="dumpLoader.scene.label" default="Scene" /></label>
				<div>
					<g:select class="form-control" id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${dumpLoaderInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: dumpLoaderInstance, field: 'scene', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: dumpLoaderInstance, field: 'processErrors', 'error')} ">
				<label for="processErrors" class="control-label"><g:message code="dumpLoader.processErrors.label" default="Process Errors" /></label>
				<div>
					<g:textField class="form-control" name="processErrors" readonly="readonly" value="${dumpLoaderInstance?.processErrors}"/>
					<span class="help-inline">${hasErrors(bean: dumpLoaderInstance, field: 'processErrors', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: dumpLoaderInstance, field: 'dumpPath', 'error')} ">
				<label for="dumpPath" class="control-label"><g:message code="dumpLoader.dumpPath.label" default="Dump Path" /></label>
				<div>
					<g:textField class="form-control" name="dumpPath" value="${dumpLoaderInstance?.dumpPath}"/>
					<span class="help-inline">${hasErrors(bean: dumpLoaderInstance, field: 'dumpPath', 'error')}</span>
				</div>
			</div>

