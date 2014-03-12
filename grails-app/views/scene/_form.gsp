<%@ page import="webbluefinder.Scene" %>



<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="scene.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${sceneInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'fromType', 'error')} required">
	<label for="fromType">
		<g:message code="scene.fromType.label" default="From Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fromType" required="" value="${sceneInstance?.fromType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'property', 'error')} required">
	<label for="property">
		<g:message code="scene.property.label" default="Property" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="property" required="" value="${sceneInstance?.property}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'toType', 'error')} required">
	<label for="toType">
		<g:message code="scene.toType.label" default="To Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="toType" required="" value="${sceneInstance?.toType}"/>
</div>
<!-- 
<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'process', 'error')} required">
	<label for="process">
		<g:message code="scene.process.label" default="Process" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="process" name="process.id" from="${webbluefinder.Process.list()}" optionKey="id" required="" value="${sceneInstance?.process?.id}" class="many-to-one"/>
</div>
-->
<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'processState', 'error')} ">
	<label for="processState">
		<g:message code="scene.processState.label" default="Process State" />
		
	</label>
	<g:textField name="processState" readonly="readonly" value="${sceneInstance?.processState}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sceneInstance, field: 'previousProcess', 'error')} ">
	<label for="previousProcess">
		<g:message code="scene.previousProcess.label" default="Previous Process" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${sceneInstance?.previousProcess?}" var="p">
    <li><g:link controller="process" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="process" action="create" params="['scene.id': sceneInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'process.label', default: 'Process')])}</g:link>
</li>
</ul>

</div>

