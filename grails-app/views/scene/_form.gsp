<%@ page import="webbluefinder.Scene" %>



			<div class="${hasErrors(bean: sceneInstance, field: 'name', 'error')} required">
				<label for="name" class="control-label"><g:message code="scene.name.label" default="Name" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="name" required="" value="${sceneInstance?.name}"/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'name', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'fromType', 'error')} required">
				<label for="fromType" class="control-label"><g:message code="scene.fromType.label" default="From Type" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="fromType" required="" value="${sceneInstance?.fromType}"/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'fromType', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'property', 'error')} required">
				<label for="property" class="control-label"><g:message code="scene.property.label" default="Property" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="property" required="" value="${sceneInstance?.property}"/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'property', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'toType', 'error')} required">
				<label for="toType" class="control-label"><g:message code="scene.toType.label" default="To Type" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="toType" required="" value="${sceneInstance?.toType}"/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'toType', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'process', 'error')} ">
				<label for="process" class="control-label"><g:message code="scene.process.label" default="Process" /></label>
				<div>
					<g:select class="form-control" id="process" name="process.id" from="${webbluefinder.Process.list()}" optionKey="id" value="${sceneInstance?.process?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'process', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'processState', 'error')} ">
				<label for="processState" class="control-label"><g:message code="scene.processState.label" default="Process State" /></label>
				<div>
					<g:textField class="form-control" name="processState" readonly="readonly" value="${sceneInstance?.processState}"/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'processState', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'piaMinLimit', 'error')} required">
				<label for="piaMinLimit" class="control-label"><g:message code="scene.piaMinLimit.label" default="Pia Min Limit" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="piaMinLimit" type="number" min="1" value="${sceneInstance.piaMinLimit}" required=""/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'piaMinLimit', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'piaMaxLimit', 'error')} required">
				<label for="piaMaxLimit" class="control-label"><g:message code="scene.piaMaxLimit.label" default="Pia Max Limit" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="piaMaxLimit" type="number" min="1" value="${sceneInstance.piaMaxLimit}" required=""/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'piaMaxLimit', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'piaIterationsLimit', 'error')} required">
				<label for="piaIterationsLimit" class="control-label"><g:message code="scene.piaIterationsLimit.label" default="Pia Iterations Limit" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="piaIterationsLimit" type="number" min="1" value="${sceneInstance.piaIterationsLimit}" required=""/>
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'piaIterationsLimit', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'piaClean', 'error')} ">
				<label for="piaClean" class="control-label"><g:message code="scene.piaClean.label" default="Pia Clean" /></label>
				<div>
					<bs:checkBox name="piaClean" value="${sceneInstance?.piaClean}" />
					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'piaClean', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: sceneInstance, field: 'previousProcess', 'error')} ">
				<label for="previousProcess" class="control-label"><g:message code="scene.previousProcess.label" default="Previous Process" /></label>
				<div>
					
<ul class="one-to-many">
<g:each in="${sceneInstance?.previousProcess?}" var="p">
    <li><g:link controller="process" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="process" action="create" params="['scene.id': sceneInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'process.label', default: 'Process')])}</g:link>
</li>
</ul>

					<span class="help-inline">${hasErrors(bean: sceneInstance, field: 'previousProcess', 'error')}</span>
				</div>
			</div>

