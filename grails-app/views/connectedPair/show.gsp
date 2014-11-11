
<%@ page import="webbluefinder.ConnectedPair"%>
<!DOCTYPE html>
<html>

<head>
<meta name="layout" content="kickstartNoNav" />
<g:set var="entityName"
	value="${message(code: 'connectedPair.label', default: 'ConnectedPair')}" />
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
	<header id="Header" class="jumbotron masthead">
		<div class="container">
			<h2 class="title">Connected pair</h2>
		</div>
	</header>
	<div class="nav" role="navigation">
		<ul class="nav nav-tabs">
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link controller="showStatistics" action="index">Volver a las estadísticas</g:link></li>
		</ul>
	</div>
	<g:set var="counter" value="${0}" />

	<h2 style="text-align: center">
		${connected }
	</h2>

	<!-- Esto muestra los dos infobox de las páginas de la Wiki (si es que tienen) -->
	<div>
		<div class="infobox1" style="float: left; margin-left: 6.5cm">
			${infoboxes.getAt(0)}
		</div>
	</div>

	<div class="infobox2" style="margin-left: 20cm">
		${infoboxes.getAt(1)}
	</div>

	<br />
	<div class="listContainer" style="clear: both">

		<h3>
			Path queries that connect <span style="color: blue">
				${connected}
			</span>
		</h3>
		<div class="listDiv"
			style="left: 250px; overflow: auto; max-height: 500px">
			<ul class="pathQueriesList">
				<g:each var="item" in="${pqc}">
					<li class="pathQueryItem"><g:link resource="pathQuery"
							action="show" id="${item.id}">
							${item.path }
						</g:link></li>

					<g:set var="norm"
						value="${concretePaths.getAt(counter).replaceAll(" /", "").replace("*", "")}" />
					<g:set var="split" value="${norm.split()}" />
					<g:set var="tam" value="${split.size()}" />
					<g:set var="index" value="${0}" />

					<span style="margin-left: 0.5cm"> 
						<g:each var="word" in="${split}">
							<%-- El idioma de la Wiki está hardcodeado!!
			      						 Cuando se defina bien el escenario del cual se muestran las estadísticas, el idioma
			      						 será configurable	     
			      					 --%>
							<a style="display: inline"
								href="http://es.wikipedia.org/wiki/${split.getAt(index)}"
								target="_blank"> ${split.getAt(index)}</a>
							<g:if test="${index == 0}">
								<g:if test="${ tam > 2 }">
									<p style="display: inline">/ * /</p>
								</g:if>
								<g:else>
									<p style="display: inline">/</p>
								</g:else>
							</g:if>
							<g:if test="${index != 0}">
								<g:if test="${ index+1 < tam }">
									<p style="display: inline">/</p>
								</g:if>
							</g:if>

							<g:set var="index" value="${index + 1}" />
						</g:each>
					</span>
					<g:set var="counter" value="${counter + 1}" />
				</g:each>
			</ul>
		</div>
		<!-- listDiv -->
	</div>
	<!-- listContainer -->
	<br />

</body>

</html>
