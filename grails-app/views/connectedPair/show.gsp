
<%@ page import="webbluefinder.ConnectedPair" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'connectedPair.label', default: 'ConnectedPair')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-connectedPair" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="showStatistics" action="index">Volver a las estadísticas</g:link></li>
			</ul>
		</div>

		<h3> Path queries that connect <span style="color:blue">${connected}</span></h3> 
		<div style="left:250px;overflow:auto;height:500px">	 
					<ul class="pathQueriesList" >
					<g:each var="item" in="${pqc}">
	       				<li class="pathQueryItem"><g:link resource="pathQuery" action="show" params="[id: item.id, path: item.path]">${item.id } - ${item.path } </g:link></li>  
					</g:each>
					</ul>
		</div>

	</body>
</html>
