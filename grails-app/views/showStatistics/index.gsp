
<%@ page import="webbluefinder.ShowStatistics" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'showStatistics.label', default: 'ShowStatistics')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
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
	        <g:each var="item" in="${pqr}">
	       		 dataPQR.addRow(["${item.path }", ${item.cant}]);  
			</g:each>		


	        // Set chart options

	        var optionsPQR = {'title':'Path queries relevance',
	                       'width':'90%',
	                       'height':1111};   	          
	                     
	        // Instantiate and draw our chart, passing in some options.
	             
	        var chartPQR = new google.visualization.BarChart(document.getElementById('pqr'));   
	        
	        chartPQR.draw(dataPQR, optionsPQR);

			 }
	        
		</g:javascript>
				<g:field type="text" name="bbdd" required="true" value="" hidden="true"/>
				<g:link controller="ShowStatistics" action="index" hidden="true"> Compute </g:link>
				<br/>
				<div style="left:250px">
				<h3> Path queries</h3> 
					<ul class="pathQueriesList" >
					<g:each var="item" in="${pq}">
	       				<li class="pathQueryItem"><g:link resource="pathQuery" action="show" params="[id: item.id, path: item.path]">${item.path } </g:link></li>  
					</g:each>
					</ul>
				</div>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<div style="left:250px">
				<h3> Connected pairs</h3> 
					<ul class="connectedPairsList" >
					<g:each var="item" in="${cp}">
	       				<li class="connectedPairItem"><g:link controller="showStatistics" action="showPQWhoConnects" params="[id: item.id, path: item.page]">${item.Page } </g:link></li>  
					</g:each>
					</ul>
				</div>
				<div style="width:50%"> Not connected pairs</div>
				<div id="pqr" ></div>
				
	</body>
</html>
