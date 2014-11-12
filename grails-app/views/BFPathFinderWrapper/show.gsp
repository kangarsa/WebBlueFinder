
<%@ page import="webbluefinder.process.BFPathFinderWrapper" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'BFPathFinderWrapper.label', default: 'BFPathFinderWrapper')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-BFPathFinderWrapper" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="BFPathFinderWrapper.scene.label" default="Scene" /></td>
				
				<td valign="top" class="value"><g:link controller="scene" action="show" id="${BFPathFinderWrapperInstance?.scene?.id}">${BFPathFinderWrapperInstance?.scene?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
