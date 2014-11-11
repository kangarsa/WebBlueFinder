
<%@ page import="webbluefinder.PathQuery" %>
<!DOCTYPE html>
<html>
	<head>
		<g:javascript library="jquery" />
		<meta name="layout" content="kickstartNoNav">
		<g:set var="entityName" value="${message(code: 'pathQuery.label', default: 'PathQuery')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
    	<g:javascript src="d3.js" />
    	<g:javascript src="d3.layout.js" />
    	
		<style>		
			circle {
			  cursor: pointer;
			  fill: #fff;
			  stroke: steelblue;
			  stroke-width: 1.5px;
			}
			
			text {
				font-size:10px;
			}
			
			path.link {
			  fill: none;
			  stroke: #ccc;
			  stroke-width: 1.5px;
			}
		</style>		
	</head>
	<body>
		<header id="Header" class="jumbotron masthead">
			<div class="container">
				<h1 class="title">Path query</h1>
			</div>
		</header>
		<div class="nav" role="navigation">
			<ul class="nav nav-tabs">
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="showStatistics" action="index">Volver a las estadísticas</g:link></li>
			</ul>
		</div>
		
		<!-- 
		Librerías de Google Charts para mostrar el gráfico de la relevancia de cada
		path query
		 -->
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
	        
	        <g:set var="never" value="${true}" />
			<g:each var="item" in="${pqr}">  		 
	       		 <g:if test="${PQrel[0].id == item.id}">
    				 dataPQR.addRow(["${item.path }", 0, ${item.id }, ${item.cant}]);
    				 <g:set var="never" value="${false}" />
				 </g:if>
				 <g:else>
    				 dataPQR.addRow(["${item.path }", ${item.cant}, ${item.id }, 0]);  
			     </g:else>
			</g:each>
			
			<g:if test ="${never}">
				dataPQR.addRow(["${PQrel[0].path }", 0, ${PQrel[0].id }, ${PQrel[0].cant}]);
			</g:if>
						
		    var viewPQR = new google.visualization.DataView(dataPQR);

	        var optionsPQR = {'title':'Path query coverage',
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
				window.location = "./"+ id;
				}
			 }

		</g:javascript>
		
		<g:set var="size" value="${instantiated.size()-1}" />
		<g:set var="counter" value="${0}" />
		
		<h3> Pairs connected by <span style="color:blue">${path }</span></h3>
		
		<div style="left:250px;overflow:auto;max-height:400px;width:90%;border: 3px solid #90bade;border-radius: 5px">	  
			<ul class="connectedPairsList"  >
				<g:each var="item" in="${pqcp}">
      				<li class="connectedPairItem"><g:link controller="connectedPair" action="show" id="${item.id}"> ${item.Page.replaceAll(",","-").replaceAll("_"," ") } </g:link> 
      				-->
      				<g:set var="norm" value="${instantiated.getAt(counter).replaceAll(" /", "").replace("*", "")}" />
      				<g:set var="split" value="${norm.split()}" />
      				<g:set var="tam" value="${split.size()}" />
      				<g:set var="index" value="${0}" />
      				<!--  <span style="margin-left:0.5cm;">-->
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
	      				</li>
      				<!--</span>-->
      				<g:set var="counter" value="${counter + 1}" />  
				</g:each>
			</ul>	
		</div>	
		<p> Total: ${pqcp.size() }</p>
		  <div id="chart"></div>
		<div id="pqr" ></div>
		<script type="text/javascript">
				
			var w = 1200,
			    h = ${treeSize},
			    i = 0,
			    duration = 500,
			    root = {
					    "name": "#paths",
					    "children": [${pathsMap}]
					   };
		
			var tree = d3.layout.tree()
			    .size([h, w - 160]);
		
			var diagonal = d3.svg.diagonal()
			    .projection(function(d) { return [d.y, d.x]; });
		
			var vis = d3.select("#chart").append("svg:svg")
			    .attr("width", w)
			    .attr("height", h)
			  .append("svg:g")
			    .attr("transform", "translate(40,0)");
			   
			//root.children.forEach(click);       		    	   
  			update(root);
			
			
			function update(source) {
		
			  // Compute the new tree layout.
			  var nodes = tree.nodes(root).reverse();
			 console.log(nodes)
			  // Update the nodes…
			  	var node = vis.selectAll("g.node")
			      .data(nodes, function(d) { return d.id || (d.id = ++i); });
		
				var nodeEnter = node.enter().append("svg:g")
			    	.attr("class", "node")
			    	.attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; });
			    	//.style("opacity", 1e-6);
			 
			  // Enter any new nodes at the parent's previous position.
			 
			  	nodeEnter.append("svg:circle")
			      //.attr("class", "node")
			      //.attr("cx", function(d) { return source.x0; })
			      //.attr("cy", function(d) { return source.y0; })
			      .attr("r", 4.5)
			      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; })
			      .on("click", click);
			
				nodeEnter.append("svg:text")
			      	.attr("x", function(d) { return d._children ? -8 : 8; })
					.attr("y", 3)
			      	//.attr("fill","#ccc")
			      	//.attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })
			      	.text(function(d) { return d.name; });
		
			  // Transition nodes to their new position.
				nodeEnter.transition()
					.duration(duration)
					.attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })
			      	.style("opacity", 1)
			      .select("circle")
			    	//.attr("cx", function(d) { return d.x; })
					//.attr("cy", function(d) { return d.y; })
				.style("fill", "lightsteelblue");
			      
			    node.transition()
			      .duration(duration)
			      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })
			      .style("opacity", 1);
			    
		
				node.exit().transition()
			      .duration(duration)
			      .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
			      .style("opacity", 1e-6)
			      .remove();
			/*
				var nodeTransition = node.transition()
					.duration(duration);
			  
			  nodeTransition.select("circle")
			      .attr("cx", function(d) { return d.y; })
			      .attr("cy", function(d) { return d.x; })
			      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });
			  
			  nodeTransition.select("text")
			      .attr("dx", function(d) { return d._children ? -8 : 8; })
				  .attr("dy", 3)
			      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#5babfc"; });
		
			  // Transition exiting nodes to the parent's new position.
			  var nodeExit = node.exit();
			  
			  nodeExit.select("circle").transition()
			      .duration(duration)
			      .attr("cx", function(d) { return source.y; })
			      .attr("cy", function(d) { return source.x; })
			      .remove();
			  
			  nodeExit.select("text").transition()
			      .duration(duration)
			      .remove();
			*/
			  // Update the links…
			  var link = vis.selectAll("path.link")
			      .data(tree.links(nodes), function(d) { return d.target.id; });
		
			  // Enter any new links at the parent's previous position.
			  link.enter().insert("svg:path", "g")
			      .attr("class", "link")
			      .attr("d", function(d) {
				var o = {x: source.x0, y: source.y0};
				return diagonal({source: o, target: o});
			      })
			    .transition()
			      .duration(duration)
			      .attr("d", diagonal);
		
			  // Transition links to their new position.
			  link.transition()
			      .duration(duration)
			      .attr("d", diagonal);
		
			  // Transition exiting nodes to the parent's new position.
			  link.exit().transition()
			      .duration(duration)
			      .attr("d", function(d) {
				var o = {x: source.x, y: source.y};
				return diagonal({source: o, target: o});
			      })
			      .remove();
		
			  // Stash the old positions for transition.
			  nodes.forEach(function(d) {
			    d.x0 = d.x;
			    d.y0 = d.y;
			  });
			}
		
			// Toggle children on click.
			function click(d) {
			  if (d.children) {
			    d._children = d.children;
			    d.children = null;
			  } else {
			    d.children = d._children;
			    d._children = null;
			  }
			  update(d);
			}
		
			d3.select(self.frameElement).style("height", "2000px");

    </script>

	</body>
</html>
