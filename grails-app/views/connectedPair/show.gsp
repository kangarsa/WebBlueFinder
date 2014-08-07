
<%@ page import="webbluefinder.ConnectedPair" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'connectedPair.label', default: 'ConnectedPair')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<style>
			.infobox_v2 {
			border: 1px solid #B4BBC8;
			background-color: #f9f9f9;
			color: #000;
			padding: .4em;
			clear: right;
			font-size: 90%;
			line-height: 1.5em;
			width: 22.5em;
			}
		</style>
	</head>
	<body>
		<a href="#show-connectedPair" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="showStatistics" action="index">Volver a las estadísticas</g:link></li>
			</ul>
		</div>

		<g:set var="size" value="${instanciated.size()-1}" />
		<g:set var="counter" value="${0}" />
		
		<h1 style="text-align:center">${connected }</h1>
		
		<div>
			<div style="float:left; margin-left:1.5cm"> ${test.getAt(0) }</div>
			<div style="margin-left:15cm"> ${test.getAt(1) }</div>
		</div>
		
		<br/>
		<div style="clear:both">
		<h3> Path queries that connect <span style="color:blue">${connected}</span></h3> 
		
		<div style="left:250px;overflow:auto;max-height:500px">	 
					<ul class="pathQueriesList" >
					<g:each var="item" in="${pqc}">
	       				<li class="pathQueryItem"><g:link resource="pathQuery" action="show" params="[id: item.id, path: item.path]"> ${item.path } </g:link></li>
	       				
		      				<g:set var="norm" value="${instanciated.getAt(counter).replaceAll("/", "").replace("*", "")}" />
		      				<g:set var="split" value="${norm.split()}" />
		      				<g:set var="tam" value="${split.size()}" />	
		      				<g:set var="index" value="${0}" />
		      				
		      				<span style="margin-left:0.5cm">
			      				<g:each var="word" in="${split}">
			      					<%-- El idioma de la Wiki está hardcodeado!!
			      						 Cuando se defina bien el escenario del cual se muestran las estadísticas, el idioma
			      						 será configurable	     
			      					 --%>								
									<a style="display:inline" href="http://es.wikipedia.org/wiki/${split.getAt(index)}" target="_blank"> ${split.getAt(index)}</a>
									<g:if test="${index == 0}">
										<g:if test ="${ tam > 2 }">
			     							<p style="display:inline"> / * / </p>
			     						</g:if>
			     						<g:else>
			     							<p style="display:inline"> / </p>
			     						</g:else>			     						
									</g:if>
									<g:if test="${index != 0}">
										<g:if test ="${ index+1 < tam }">
			     							<p style="display:inline"> / </p>
			     						</g:if>
									</g:if>
			
			      					<g:set var="index" value="${index + 1}" /> 
			      				</g:each>
		      				</span>
		      				<g:set var="counter" value="${counter + 1}" />  	       				
	       				
	       				  
					</g:each>
					</ul>
		</div>
		</div>
		<br/>
		
		<%-- <div style="border: 3px solid rgb(0, 190, 0); overflow: hidden; margin: 15px auto; max-width: 280px; margin-left: 45px">
		<iframe scrolling="no" src="http://en.wikipedia.org/wiki/Popular_Action_(Peru)" style="border: 0px none; margin-left: -135px; height: 1120px; margin-top: -605px; width: 426px;">
		</iframe>
		</div>--%>
		<%--<g:each var="item" in="${test}">	
			<span style="float:left"> ${item }   </span>
		</g:each>--%>

	</body>
</html>
