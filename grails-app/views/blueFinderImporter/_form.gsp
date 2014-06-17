<%@ page import="webbluefinder.BlueFinderImporter" %>



			<div class="${hasErrors(bean: blueFinderImporterInstance, field: 'scene', 'error')} ">
				<label for="scene" class="control-label"><g:message code="blueFinderImporter.scene.label" default="Scene" /></label>
				<div>
					<g:select class="form-control" id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${blueFinderImporterInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: blueFinderImporterInstance, field: 'scene', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: blueFinderImporterInstance, field: 'processErrors', 'error')} ">
				<label for="processErrors" class="control-label"><g:message code="blueFinderImporter.processErrors.label" default="Process Errors" /></label>
				<div>
					<g:textField class="form-control" name="processErrors" readonly="readonly" value="${blueFinderImporterInstance?.processErrors}"/>
					<span class="help-inline">${hasErrors(bean: blueFinderImporterInstance, field: 'processErrors', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: blueFinderImporterInstance, field: 'dbFrom', 'error')} ">
				<label for="dbFrom" class="control-label"><g:message code="blueFinderImporter.dbFrom.label" default="Db From" /></label>
				<div>
					<g:textField class="form-control" name="dbFrom" value="${blueFinderImporterInstance?.dbFrom}"/>
					<span class="help-inline">${hasErrors(bean: blueFinderImporterInstance, field: 'dbFrom', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: blueFinderImporterInstance, field: 'dbTo', 'error')} ">
				<label for="dbTo" class="control-label"><g:message code="blueFinderImporter.dbTo.label" default="Db To" /></label>
				<div>
					<g:textField class="form-control" name="dbTo" value="${blueFinderImporterInstance?.dbTo}"/>
					<span class="help-inline">${hasErrors(bean: blueFinderImporterInstance, field: 'dbTo', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: blueFinderImporterInstance, field: 'overwrite', 'error')} ">
				<label for="overwrite" class="control-label"><g:message code="blueFinderImporter.overwrite.label" default="Overwrite" /></label>
				<div>
					<bs:checkBox name="overwrite" value="${blueFinderImporterInstance?.overwrite}" />
					<span class="help-inline">${hasErrors(bean: blueFinderImporterInstance, field: 'overwrite', 'error')}</span>
				</div>
			</div>

