<%@ page import="webbluefinder.Scene" %>


<table>
	<thead>
		<tr>
			<th><g:message code="process.name.label" default="Name" /></th>
			<th><g:message code="process.percent.label" default="Percent" /></th>
			<th><g:message code="process.state.label" default="State" /></th>
			<th><g:message code="process.actionStart.label" default="Start" /></th>
			<th><g:message code="process.actionStop.label" default="Stop" /></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${sceneInstance.previousProcess}" status="i" var="process">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			
				<td><g:link action="show" id="${process.id}">${process.getName()}</g:link></td>
				<td>${process.getPercent()}%</td>
				<td>${process.getStateName()}</td>

			</tr>
		</g:each>
	</tbody>
</table>