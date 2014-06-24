
<%@ page import="webbluefinder.PathQuery" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pathQuery.label', default: 'PathQuery')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pathQuery" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="showStatistics" action="index">Volver a las estadísticas</g:link></li>
			</ul>
		</div>
		
		<h3> Pairs connected by <span style="color:blue">${path }</span></h3>
		<div style="left:250px;overflow:auto;height:500px">	  
			<ul class="connectedPairsList" >
				<g:each var="item" in="${pqcp}">
      				<li class="connectedPairItem"><g:link controller="connectedPair" action="show" params="[id: item.id, connected: item.Page]"> ${item.id } - ${item.Page } </g:link></li>  
				</g:each>
			</ul>		
		</div>	
	</body>
</html>
