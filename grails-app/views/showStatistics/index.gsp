
<%@ page import="webbluefinder.ShowStatistics" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="kickstartNoNav">
		<g:set var="entityName" value="${message(code: 'showStatistics.label', default: 'ShowStatistics')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<style>
			.listados {
			left:250px;
			overflow:auto;
			max-height:400px;
			width:80%;
			margin:auto;
			}
		</style>		
	</head>
	<body>
		<header id="Header" class="jumbotron masthead">
			<div class="container">
				<h1 class="title">Statistics of aScenary</h1>
			</div>
		</header>	
		<div class="nav" role="navigation">
			<ul class="nav nav-tabs">
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>	
		<r:external type="js" uri="https://www.google.com/jsapi"/>
		<g:javascript>
		  // Load the Visualization API and the piechart package.
	      google.load('visualization', '1.0', {'packages':['corechart']});
	
	      // Set a callback to run when the Google Visualization API is loaded.
	      google.setOnLoadCallback(drawChart);
	
	      // Callback that creates and populates a data table,
	      // instantiates the pie chart, passes in the data and
	      // draws it.
	      function drawChart() {

	        // Create the data table.

	        var dataPQR = new google.visualization.DataTable();
			
	        dataPQR.addColumn('string', 'Path');
	        dataPQR.addColumn('number', 'Cantidad');
	        dataPQR.addColumn('number', 'id');
	        <g:each var="item" in="${pqr}">
	       		 dataPQR.addRow(["${item.path }", ${item.cant}, ${item.id }]);  
			</g:each>		
			var viewPQR = new google.visualization.DataView(dataPQR);
     		 viewPQR.setColumns([0, 1,
                     			  { calc: "stringify",
                     			    sourceColumn: 1,
                      			    type: "string",
                      			    role: "annotation" }]);
			//viewPQR.hideColumns([2]);
	        // Set chart options

	        var optionsPQR = {'title':'Path query coverage',
	                       'width':'90%',
	                       'height':1500
	                       };   	          
	                     
	        // Instantiate and draw our chart, passing in some options.
	             
	        var chartPQR = new google.visualization.BarChart(document.getElementById('pqr'));   
	        //var viewPQR = new google.visualization.DataView(dataPQR);
	        
	        
	        chartPQR.draw(viewPQR, optionsPQR);
	        
			google.visualization.events.addListener(chartPQR, 'select', selectHandler);

			function selectHandler() {		
				var selection = chartPQR.getSelection();
				var id = dataPQR.getValue(selection[0].row, 2);
				var path = dataPQR.getValue(selection[0].row, 0);
				var uri = path;
				var res = encodeURIComponent(uri);
				window.location = "./pathQuery/"+ id;
				}
			 }
	        
		</g:javascript>
				<!-- El estilo de los divs siguientes tiene que volar cuando se aplique un CSS como la gente -->
				
				<h3> Path queries</h3>
				<div class="listados">			 
					<ul class="pathQueriesList" >
					<g:each var="item" in="${pq}">
	       				<li class="pathQueryItem"><g:link resource="pathQuery" action="show" id="${item.id}">${item.path } </g:link></li>  
					</g:each>
					</ul>
				</div>
				<br/>
				<h3> Connected pairs</h3>
				<div class="listados">	 
					<ul class="connectedPairsList" >
					<g:each var="item" in="${cp}">
	       				<li class="connectedPairItem"><g:link controller="connectedPair" action="show" id="${item.id}">${item.Page.replace(",","-").replaceAll("_", " ") } </g:link></li>  
					</g:each>
					</ul>
				</div>
				<br/>
				<h3> Not connected pairs</h3>
				<div class="listados">	 
					<ul class="notConnectedPairsList" >
					<g:each var="item" in="${ncp}">
	       				<li class="notConnectedPairItem">${item.v_from.replaceAll("_", " ") } - ${item.u_to.replaceAll("_", " ") }</li>  
					</g:each>
					</ul>
				</div>				
				<div id="pqr" ></div> <!-- El div donde se carga el gráfico de barras -->		
	</body>
</html>
