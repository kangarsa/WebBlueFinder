<%@ page import="webbluefinder.DumpLoader" %>



<div class="fieldcontain ${hasErrors(bean: dumpLoaderInstance, field: 'scene', 'error')} ">
	<label for="scene">
		<g:message code="dumpLoader.scene.label" default="Scene" />
		
	</label>
	<g:select id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${dumpLoaderInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: dumpLoaderInstance, field: 'processErrors', 'error')} ">
	<label for="processErrors">
		<g:message code="dumpLoader.processErrors.label" default="Process Errors" />
		
	</label>
	<g:textField name="processErrors" readonly="readonly" value="${dumpLoaderInstance?.processErrors}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: dumpLoaderInstance, field: 'dumpPath', 'error')} required">
	<label for="dumpPath">
		<g:message code="dumpLoader.dumpPath.label" default="Dump Path" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dumpPath" required="" value="${dumpLoaderInstance?.dumpPath}"/>

</div>

