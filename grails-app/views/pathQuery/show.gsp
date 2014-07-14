
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
		<r:external type="js" uri="https://www.google.com/jsapi"/>
		<g:javascript>
	      google.load('visualization', '1.0', {'packages':['corechart']});

	      google.setOnLoadCallback(drawChart);

	      function drawChart() {

	        var dataPQR = new google.visualization.DataTable();
			
	        dataPQR.addColumn('string', 'Path');
	        dataPQR.addColumn('number', 'Cantidad');
	        dataPQR.addColumn('number', 'id');
	        dataPQR.addColumn('number', 'Cant');
	        
	        <g:each var="item" in="${pqr}">
	       		 dataPQR.addRow(["${item.path }", ${item.cant}, ${item.id }, 0]);  
			</g:each>		
			
	        <g:each var="item" in="${PQrel}">
	       		 dataPQR.addRow(["${item.path }", 0, ${item.id }, ${item.cant}]);  
			</g:each>		
			
		    var viewPQR = new google.visualization.DataView(dataPQR);

	        var optionsPQR = {'title':'Path query relevance',
	                       'width':'90%',
	                       'height':500,
	                       series: [{color: '#0000ff', visibleInLegend: true}, {color: '#ff0000', visibleInLegend: false}]
	                       };   	                          
	          viewPQR.hideColumns([2]);   
	        var chartPQR = new google.visualization.BarChart(document.getElementById('pqr'));   

	        
	        chartPQR.draw(viewPQR, optionsPQR);
	        
			google.visualization.events.addListener(chartPQR, 'select', selectHandler);

			function selectHandler() {	
			
				var selection = chartPQR.getSelection();
				var id = dataPQR.getValue(selection[0].row, 2);
				var path = dataPQR.getValue(selection[0].row, 0);
				var uri = path;
				var res = encodeURIComponent(uri);
				window.location = "http://localhost:8080/WebBlueFinder/pathQuery/show/"+ id +"?path="+res;



				}

			 }
	        
		</g:javascript>
		
		<g:set var="size" value="${instanciated.size()-1}" />
		<g:set var="counter" value="${0}" />
		
		<h3> Pairs connected by <span style="color:blue">${path }</span></h3>
		<div style="left:250px;overflow:auto;height:400px">	  
			<ul class="connectedPairsList" >
				<g:each var="item" in="${pqcp}">
      				<li class="connectedPairItem"><g:link controller="connectedPair" action="show" params="[id: item.id, connected: item.Page]"> ${item.Page } </g:link> </li>
      				<%--<p style="margin-left:0.5cm"> ${instanciated.getAt(counter)} </p>--%>
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
		

		
		<div id="pqr" ></div>
	</body>
</html>
