
<%@ page import="webbluefinder.ConnectedPair" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'connectedPair.label', default: 'ConnectedPair')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
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

		<h3> Path queries that connect <span style="color:blue">${connected}</span></h3> 
		<div style="left:250px;overflow:auto;height:500px">	 
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
		
		
		<div style="border: 3px solid rgb(0, 190, 0); overflow: hidden; margin: 15px auto; max-width: 280px; margin-left: 45px">
		<iframe scrolling="no" src="http://en.wikipedia.org/wiki/Popular_Action_(Peru)" style="border: 0px none; margin-left: -135px; height: 1120px; margin-top: -605px; width: 426px;">
		</iframe>
		</div>
		
				<table class="infobox vcard" cellspacing="3" style="border-spacing:3px;width:22em;">
				<tr>
				<th colspan="2" class="fn org" style="text-align:center;font-size:125%;font-weight:bold;border-top: 2px solid #000000; border-bottom: 2px solid #000000;">Popular Action</th>
				</tr>
				<tr>
				<td colspan="2" style="text-align:center;"><span class="nickname">Acción Popular</span></td>
				</tr>
				<tr>
				<td colspan="2" class="logo" style="text-align:center;"><a href="/wiki/File:Acci%C3%B3n_Popular_emblema.jpg" class="image"><img alt="Acción Popular emblema.jpg" src="//upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Acci%C3%B3n_Popular_emblema.jpg/150px-Acci%C3%B3n_Popular_emblema.jpg" width="150" height="148" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Acci%C3%B3n_Popular_emblema.jpg/225px-Acci%C3%B3n_Popular_emblema.jpg 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Acci%C3%B3n_Popular_emblema.jpg/300px-Acci%C3%B3n_Popular_emblema.jpg 2x" data-file-width="330" data-file-height="325" /></a></td>
				</tr>
				<tr>
				<th scope="row" style="text-align:left;padding-top:0.2em;line-height:1.2em;;">President</th>
				<td class="agent" style="padding-top:0.1em;line-height:1.3em;;"><a href="/wiki/Javier_Alva_Orlandini" title="Javier Alva Orlandini">Javier Alva Orlandini</a></td>
				</tr>
				<tr>
				<th scope="row" style="text-align:left;padding-top:0.2em;line-height:1.2em;;"><span class="nowrap">Secretary-General</span></th>
				<td class="agent" style="padding-top:0.1em;line-height:1.3em;;"><a href="/w/index.php?title=Mes%C3%ADas_Guevara&amp;action=edit&amp;redlink=1" class="new" title="Mesías Guevara (page does not exist)">Mesías Guevara</a></td>
				</tr>
				<tr>
				<th scope="row" style="text-align:left;padding-top:0.2em;line-height:1.2em;;">Founded</th>
				<td style="padding-top:0.1em;line-height:1.3em;;">1956<span style="display:none">&#160;(<span class="bday dtstart published updated">1956</span>)</span></td>
				</tr>
				<tr>
				<th scope="row" style="text-align:left;padding-top:0.2em;line-height:1.2em;;">Headquarters</th>
				<td class="label" style="padding-top:0.1em;line-height:1.3em;;"><a href="/wiki/Lima" title="Lima">Lima</a>, <a href="/wiki/Peru" title="Peru">Peru</a></td>
				</tr>
				<tr>
				<th scope="row" style="text-align:left;padding-top:0.2em;line-height:1.2em;;"><a href="/wiki/List_of_political_ideologies" title="List of political ideologies">Ideology</a></th>
				<td class="category" style="padding-top:0.1em;line-height:1.3em;;"><a href="/wiki/Peru" title="Peru">Peruvian</a> <a href="/wiki/Nationalism" title="Nationalism">nationalism</a>,<br />
				<a href="/wiki/Populism" title="Populism">Populism</a>,<br />
				<a href="/wiki/Centrism" title="Centrism">Centrism</a>,<br />
				<a href="/wiki/Reformism" title="Reformism">Reformism</a><sup id="cite_ref-1" class="reference"><a href="#cite_note-1"><span>[</span>1<span>]</span></a></sup></td>
				</tr>
				<tr>
				<th scope="row" style="text-align:left;padding-top:0.2em;line-height:1.2em;;"><a href="/wiki/Political_spectrum" title="Political spectrum">Political position</a></th>
				<td class="category" style="padding-top:0.1em;line-height:1.3em;;"><a href="/wiki/Centrism" title="Centrism">Centre</a><sup id="cite_ref-2" class="reference"><a href="#cite_note-2"><span>[</span>2<span>]</span></a></sup><sup id="cite_ref-3" class="reference"><a href="#cite_note-3"><span>[</span>3<span>]</span></a></sup><br />
				to <a href="/wiki/Centre-right" title="Centre-right" class="mw-redirect">Centre-right</a><sup id="cite_ref-4" class="reference"><a href="#cite_note-4"><span>[</span>4<span>]</span></a></sup><sup id="cite_ref-5" class="reference"><a href="#cite_note-5"><span>[</span>5<span>]</span></a></sup><sup id="cite_ref-6" class="reference"><a href="#cite_note-6"><span>[</span>6<span>]</span></a></sup></td>
				</tr>
				<tr>
				<th colspan="2" style="text-align:center;">Website</th>
				</tr>
				<tr>
				<td colspan="2" style="text-align:center;padding-top:0.1em;line-height:1.3em;;"><a rel="nofollow" class="external text" href="http://www.accionpopular.pe/">www.accionpopular.pe</a></td>
				</tr>
				<tr>
				<td colspan="2" style="text-align:center;border-top: 2px solid #000000;"><a href="/wiki/Politics_of_Peru" title="Politics of Peru">Politics of Peru</a><br />
				<a href="/wiki/List_of_political_parties_in_Peru" title="List of political parties in Peru">Political parties</a><br />
				<a href="/wiki/Elections_in_Peru" title="Elections in Peru">Elections</a></td>
				</tr>
				</table>		

		
	</body>
</html>
