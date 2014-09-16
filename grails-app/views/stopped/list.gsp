
<%@ page import="webbluefinder.Stopped" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'stopped.label', default: 'Stopped')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-stopped" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${stoppedInstanceList}" status="i" var="stoppedInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${stoppedInstanceCount}" />
	</div>
</section>

</body>

</html>