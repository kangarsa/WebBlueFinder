
<%@ page import="webbluefinder.ShowStatistics" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'showStatistics.label', default: 'ShowStatistics')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-showStatistics" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
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

	        var optionsPQR = {'title':'Path query relevance',
	                       'width':'90%',
	                       'height':2000};   	          
	                     
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
				window.location = "../pathQuery/show/"+ id +"?path="+res;




				}

			 }
	        
		</g:javascript>
				<!-- Este field no, el link siguiente tampoco -->
				<g:field type="text" name="bbdd" required="true" value="" hidden="true"/>
				<g:link controller="ShowStatistics" action="index" hidden="true"> Compute </g:link>
				<br/>
				<!-- El estilo de los divs siguientes tiene que volar cuando se aplique un CSS como la gente -->
				<h5 style="font-size:280%;text-align:center">Scenary</h5>
				<h3> Path queries</h3>
				<div style="left:250px;overflow:auto;max-height:400px">			 
					<ul class="pathQueriesList" >
					<g:each var="item" in="${pq}">
	       				<li class="pathQueryItem"><g:link resource="pathQuery" action="show" params="[id: item.id, path: item.path]">${item.path } </g:link></li>  
					</g:each>
					</ul>
				</div>
				<br/>
				<h3> Connected pairs</h3>
				<div style="left:250px;overflow:auto;max-height:400px">	 
					<ul class="connectedPairsList" >
					<g:each var="item" in="${cp}">
	       				<li class="connectedPairItem"><g:link controller="connectedPair" action="show" params="[id: item.id, connected: item.Page]">${item.Page } </g:link></li>  
					</g:each>
					</ul>
				</div>
				<br/>
				<h3> Not connected pairs</h3>
				<div style="left:250px;overflow:auto;max-height:400px">	 
					<ul class="notConnectedPairsList" >
					<g:each var="item" in="${ncp}">
	       				<li class="notConnectedPairItem">${item.v_from } , ${item.u_to }</li>  
					</g:each>
					</ul>
				</div>				
				<div id="pqr" ></div>
				
	</body>
</html>
