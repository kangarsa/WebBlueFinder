
<%@ page import="webbluefinder.Scene" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'scene.label', default: 'Scene')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-scene" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.name.label" default="Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sceneInstance, field: "name")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.fromType.label" default="From Type" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sceneInstance, field: "fromType")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.property.label" default="Property" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sceneInstance, field: "property")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.toType.label" default="To Type" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sceneInstance, field: "toType")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.process.label" default="Process" /></td>
				
				<td valign="top" class="value"><g:link controller="process" action="show" id="${sceneInstance?.process?.id}">${sceneInstance?.process?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.processState.label" default="Process State" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sceneInstance, field: "processState")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.piaMinLimit.label" default="Pia Min Limit" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sceneInstance, field: "piaMinLimit")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.piaMaxLimit.label" default="Pia Max Limit" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sceneInstance, field: "piaMaxLimit")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.piaIterationsLimit.label" default="Pia Iterations Limit" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sceneInstance, field: "piaIterationsLimit")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.piaClean.label" default="Pia Clean" /></td>
				
				<td valign="top" class="value"><g:formatBoolean boolean="${sceneInstance?.piaClean}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="scene.previousProcess.label" default="Previous Process" /></td>
				
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${sceneInstance.previousProcess}" var="p">
						<li><g:link controller="process" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
				</td>
				
			</tr>
		
		</tbody>
	</table>
	
</section>

<section id="show-actions">
	<fieldset class="actions">
		<g:render template="actions"/>
	</fieldset>
</section>

</body>

</html>
