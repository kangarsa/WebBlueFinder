
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
	      google.load('visualization', '1.0', {'packages':['table','corechart']});
	
	      // Set a callback to run when the Google Visualization API is loaded.
	      google.setOnLoadCallback(drawChart);
	
	      // Callback that creates and populates a data table,
	      // instantiates the pie chart, passes in the data and
	      // draws it.
	      function drawChart() {

	        // Create the data table.
	        var dataPQS = new google.visualization.DataTable();
	        var dataCP = new google.visualization.DataTable();
	        var dataNCP = new google.visualization.DataTable();
	        var dataPQR = new google.visualization.DataTable();
	        //var dataConnects = new google.visualization.DataTable();
	        //var dataConnectedBy = new google.visualization.DataTable();

			dataPQS.addColumn('number', 'ID');
	        dataPQS.addColumn('string', 'Path');
	        <g:each var="item" in="${pq}">
	       		 dataPQS.addRow([${item.id}, "${item.path }"]);  
			</g:each>
			
			//dataConnects.addColumn('number', 'ID');
	        //dataConnects.addColumn('string', 'Page');
	        
	        dataCP.addColumn('number', 'ID');
	        dataCP.addColumn('string', 'Page');
	        <g:each var="item" in="${cp}">
	       		 dataCP.addRow([${item.id}, '${item.page}']);  
			</g:each>
			
			//dataConnectedBy.addColumn('number', 'ID');
	        //dataConnectedBy.addColumn('string', 'Path');
			
			dataNCP.addColumn('number', 'ID');
			dataNCP.addColumn('string', 'v_from');
			dataNCP.addColumn('string', 'u_to');      
	        <g:each var="item" in="${ncp}">
	       		 dataNCP.addRow([${item.id}, "${item.v_from }", "${item.u_to }"]);  
			</g:each>			
			
			//dataPQR.addColumn('number', 'Cantidad2');
			
	        dataPQR.addColumn('string', 'Path');
	        dataPQR.addColumn('number', 'Cantidad');
	        <g:each var="item" in="${pqr}">
	       		 dataPQR.addRow(["${item.path }", ${item.cant}]);  
			</g:each>		


	        // Set chart options
	        var optionsPQS = {'title':'Path Queries',
	                       'width':'45%',
	                       'height':300};
	        var optionsCP = {'title':'Connected pairs',
	                       'width':'45%',
	                       'height':300};
	        var optionsNCP = {'title':'Not connected pairs',
	        			   'width':'50%',
	                       'height':300,
	                       'allowHtml':true};
	        var optionsPQR = {'title':'Path queries relevance',
	                       'width':'90%',
	                       'height':1111};   
            //var optionsConnects = {'title':'Connects...',
	          //             'width':'45%',
	            //           'height':300};           
	          
	                     
	        // Instantiate and draw our chart, passing in some options.
	        var tablePQS = new google.visualization.Table(document.getElementById('pqs'));
	        var tableCP = new google.visualization.Table(document.getElementById('cp'));
	        var tableNCP = new google.visualization.Table(document.getElementById('ncp'));     
	        var chartPQR = new google.visualization.BarChart(document.getElementById('pqr'));   
	        //var tableConnects = new google.visualization.BarChart(document.getElementById('pqc'));
	        //var tableConnectedBy = new google.visualization.BarChart(document.getElementById('cpc'));
	        //var formatter = new google.visualization.BarFormat({width: 40});
  			//formatter.format(dataNCP, 0); // Apply formatter to second column
	        //dataNCP.setColumnProperty(0, 'style', 'width:150px');
	        tablePQS.draw(dataPQS, optionsPQS);
	        tableCP.draw(dataCP, optionsCP);
	        tableNCP.draw(dataNCP, optionsNCP);
	        chartPQR.draw(dataPQR, optionsPQR);
	        //dataNCP.setColumnProperty(1, 'style', 'width:20px');
			//google.visualization.events.addListener(tablePQS, 'select', selectHandler);
	        //google.visualization.events.addListener(tableCP, 'select', selectCPHandler);

					 	
    		 
    		 

			 }
	        
		</g:javascript>
				<g:field type="text" name="bbdd" required="true" value=""/>
				<g:link controller="ShowStatistics" action="index" hidden="true"> Compute </g:link>
				<br/>
				<div style="width:450px;float:left"> Path queries</div> <div style="width:450px;margin-left:455px"> Connects...</div>
				<div id="pqs" ></div> <div id="pqc"></div>
				<div style="width:450px;float:left"> Connected pairs</div> <div style="width:450px;margin-left:455px"> Connected by...</div>
				<div id="cp" ></div>
				<div style="width:50%"> Not connected pairs</div>
				<div id="ncp" ></div>
				<div id="pqr" ></div>
				
	</body>
</html>
