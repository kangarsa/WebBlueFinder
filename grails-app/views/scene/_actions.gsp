<%@ page import="webbluefinder.Scene" %>

<style>
@import url(http://fonts.googleapis.com/css?family=Open+Sans:400,700);

.actionContainer {
  margin: 0 auto;
  background: whitesmoke;
  width: 90%;
}

.buttonContainer {
  margin: 0 auto;
  width: 70%;
  padding: 10px;
}

.actionContainer h3 {
  padding-top: 15px;
  text-align: center;
}

.actionButton {
  border: 0 none;
  border-radius: 2px 2px 2px 2px;
  color: #FFFFFF;
  cursor: pointer;
  display: inline-block;
  font-family: Arial,sans-serif;
  font-size: 12px;
  font-weight: bold;
  line-height: 20px;
  margin-bottom: 0;
  margin-top: 10px;
  padding: 7px 10px;
  text-transform: none;
  transition: all 0.3s ease 0s;
  -moz-transition: all 0.3s ease 0s;
  -webkit-transition: all 0.3s ease 0s;
  width: 170px;
  /* auto */
  text-align: center;
  /* DELETE WHEN WIDTH AUTO */
}
.actionButton.red {
  background: none repeat scroll 0 0 #e0645c;
  color: white;
}
.actionButton.red:hover {
  background: none repeat scroll 0 0 #999999;
  color: white;
}
.actionButton.dark {
  background: none repeat scroll 0 0 #444444;
  color: white;
}
.actionButton.dark:hover {
  background: none repeat scroll 0 0 #2db6cf;
  color: white;
}
.actionButton.light {
  background: none repeat scroll 0 0 #999999;
  color: white;
}
.actionButton.light:hover {
  background: none repeat scroll 0 0 #444444;
  color: white;
}
.actionButton.green {
  background: none repeat scroll 0 0 #46b98a;
  color: white;
}
.actionButton.green:hover {
  background: none repeat scroll 0 0 #444444;
  color: white;
}
.actionButton.blue {
  background: none repeat scroll 0 0 #2db6cf;
  color: white;
}
.actionButton.blue:hover {
  background: none repeat scroll 0 0 #444444;
  color: white;
}
</style>
<div class='row-fluid show-grid container'>
	<div class='actionContainer'>
		<h3>Generate New Process</h3>
		<div class='buttonContainer'>
			<g:link controller="DBRetrieverWrapper" action="createFor" id="${sceneInstance.id}"><div class='actionButton red center'>DBRetriever</div></g:link>
			<div class='actionButton dark center'>PIA</div>
			<div class='actionButton light center'>BlueFinder Evaluation</div>
			<div class='actionButton green center'>BlueFinder PathFinder</div>
			<!-- <div class='actionButton blue center'>Blue Dark</div> -->
		</div>
	</div>
</div>

<%--
<ul class="nav nav-tabs">
  <li><a href="#DBRetriever" data-toggle="tab">DBRetriever</a></li>
  <li><a href="#PIA" data-toggle="tab">PIA</a></li>
  <li><a href="#BlueFinder Evaluation" data-toggle="tab">BlueFinder Evaluation</a></li>
  <li><a href="#BlueFinder PathFinder" data-toggle="tab">BlueFinder PathFinder</a></li>
</ul>
<div class="tab-content">
  <div class="tab-pane active" id="DBRetriever">...</div>
  <div class="tab-pane" id="PIA">...</div>
  <div class="tab-pane" id="BlueFinder Evaluation">...</div>
  <div class="tab-pane" id="BlueFinder PathFinder">...</div>
</div>
 --%>
<g:form>
	<fieldset class="buttons">
		<g:hiddenField name="id" value="${sceneInstance?.id}" />
		<g:link class="start" action="next" id="${sceneInstance?.id}"><g:message code="scene.startnext" default="Start/Next" /></g:link>
		<g:actionSubmit class="stop" action="stop" value="${message(code: 'scene.stop', default: 'Stop')}" onclick="return confirm('${message(code: 'scene.stop.confirm.message', default: 'Are you sure?')}');" />
	</fieldset>
</g:form>

<table class="table table-bordered margin-top-medium">

	<thead>
		<tr>
			<th><g:message code="process.name.label" default="Name" /></th>
			<th><g:message code="process.percent.label" default="Percent" /></th>
			<th><g:message code="process.state.label" default="State" /></th>
			<th><g:message code="process.execute.label" default="Execute" /></th>
			<th><g:message code="process.stop.label" default="Stop" /></th>
		</tr>
	</thead>
	${sceneInstance.previousProcess}
	<tbody>
		<g:each in="${sceneInstance.previousProcess}" status="i" var="process">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td><g:link controller="${process.getName()}" action="show" id="${process.id}">${process.getName()}</g:link></td>
				<td class="${process.getPercent() == 100 ? 'highPercent' : ''}${process.getPercent() > 0 ? 'mediumPercent' : 'lowPercent'}">${process.getPercent()}%</td>
				<td>${process.getStateName()}</td>
				<td><g:link class="buttonStart" controller="process" action="execute" id="${process.id}"><g:message code="process.execute" default="Execute" /></g:link></td>
				<td><g:actionSubmit disabled class="buttonStop" controller="process" action="stop" value="${message(code: 'process.stop', default: 'Stop')}" onclick="return confirm('${message(code: 'process.stop.confirm.message', default: 'Are you sure?')}');" /></td>
			</tr>
		</g:each>
	</tbody>
</table>
