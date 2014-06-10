<%@ page import="webbluefinder.BlueFinderImporter" %>



<div class="fieldcontain ${hasErrors(bean: blueFinderImporterInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="blueFinderImporter.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${blueFinderImporterInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blueFinderImporterInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="blueFinderImporter.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${blueFinderImporterInstance?.processErrors}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blueFinderImporterInstance, field: 'dbFrom', 'error')} required">
	<label for="dbFrom">
		<g:message code="blueFinderImporter.dbFrom.label" default="Db From" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dbFrom" required="" value="${blueFinderImporterInstance?.dbFrom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blueFinderImporterInstance, field: 'dbTo', 'error')} required">
	<label for="dbTo">
		<g:message code="blueFinderImporter.dbTo.label" default="Db To" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dbTo" required="" value="${blueFinderImporterInstance?.dbTo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blueFinderImporterInstance, field: 'overwrite', 'error')} ">
	<label for="overwrite">
		<g:message code="blueFinderImporter.overwrite.label" default="Overwrite" />
		
	</label>
	<g:checkBox name="overwrite" value="${blueFinderImporterInstance?.overwrite}" />

</div>

