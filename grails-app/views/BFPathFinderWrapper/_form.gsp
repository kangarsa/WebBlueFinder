<%@ page import="webbluefinder.process.BFPathFinderWrapper" %>



			<div class="${hasErrors(bean: BFPathFinderWrapperInstance, field: 'scene', 'error')} ">
				<label for="scene" class="control-label"><g:message code="BFPathFinderWrapper.scene.label" default="Scene" /></label>
				<div>
					<g:select class="form-control" id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${BFPathFinderWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: BFPathFinderWrapperInstance, field: 'scene', 'error')}</span>
				</div>
			</div>

