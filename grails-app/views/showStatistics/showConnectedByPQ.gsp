
<%@ page import="webbluefinder.ShowStatistics" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'showStatistics.label', default: 'ShowStatistics')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-showStatistics" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="showStatistics" action="index">Volver a las estadísticas</g:link></li>
			</ul>
		</div>
		
		<h3> Pairs connected by <span style="color:blue">${path }</span></h3> 
			<ul class="connectedPairsList" >
				<g:each var="item" in="${pqcp}">
      				<li class="connectedPairItem"><g:link >${item.Page } </g:link></li>  
				</g:each>
			</ul>		
		
	</body>
</html>
