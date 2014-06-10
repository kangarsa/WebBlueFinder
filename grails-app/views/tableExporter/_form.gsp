<%@ page import="webbluefinder.TableExporter" %>



<div class="fieldcontain ${hasErrors(bean: tableExporterInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="tableExporter.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${tableExporterInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tableExporterInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="tableExporter.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${tableExporterInstance?.processErrors}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tableExporterInstance, field: 'dbFrom', 'error')} required">
	<label for="dbFrom">
		<g:message code="tableExporter.dbFrom.label" default="Db From" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dbFrom" required="" value="${tableExporterInstance?.dbFrom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tableExporterInstance, field: 'dbTo', 'error')} required">
	<label for="dbTo">
		<g:message code="tableExporter.dbTo.label" default="Db To" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dbTo" required="" value="${tableExporterInstance?.dbTo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tableExporterInstance, field: 'tableFrom', 'error')} required">
	<label for="tableFrom">
		<g:message code="tableExporter.tableFrom.label" default="Table From" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="tableFrom" required="" value="${tableExporterInstance?.tableFrom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tableExporterInstance, field: 'tableTo', 'error')} required">
	<label for="tableTo">
		<g:message code="tableExporter.tableTo.label" default="Table To" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="tableTo" required="" value="${tableExporterInstance?.tableTo}"/>

</div>

