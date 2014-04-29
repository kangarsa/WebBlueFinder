<%@ page import="webbluefinder.Scene" %>


<g:form>
	<fieldset class="buttons">
		<g:hiddenField name="id" value="${sceneInstance?.id}" />
		<g:link class="start" action="next" id="${sceneInstance?.id}"><g:message code="scene.startnext" default="Start/Next" /></g:link>
		<g:actionSubmit class="stop" action="stop" value="${message(code: 'scene.stop', default: 'Stop')}" onclick="return confirm('${message(code: 'scene.stop.confirm.message', default: 'Are you sure?')}');" />
	</fieldset>
</g:form>
<table>
	<thead>
		<tr>
			<th><g:message code="process.name.label" default="Name" /></th>
			<th><g:message code="process.percent.label" default="Percent" /></th>
			<th><g:message code="process.state.label" default="State" /></th>
			<th><g:message code="process.execute.label" default="Execute" /></th>
			<th><g:message code="process.stop.label" default="Stop" /></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${sceneInstance.previousProcess}" status="i" var="process">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			
				<td><g:link controller="${process.getName()}" action="show" id="${process.id}">${process.getName()}</g:link></td>
				<td class="${process.getPercent() == 100 ? 'highPercent' : ''}${process.getPercent() > 0 ? 'mediumPercent' : 'lowPercent'}">${process.getPercent()}%</td>
				<td>${process.getStateName()}</td>
				<td><g:link class="buttonStart" controller="process" action="execute" id="${process.id}"><g:message code="process.execute" default="Execute" /></g:link></td>
				<td><g:actionSubmit disabled class="buttonStop" controller="process" action="stop" value="${message(code: 'process.stop', default: 'Stop')}" onclick="return confirm('${message(code: 'process.stop.confirm.message', default: 'Are you sure?')}');" /></td>
			</tr>
		</g:each>
	</tbody>
</table>