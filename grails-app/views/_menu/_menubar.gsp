<div class="">
	<ul class="nav nav-tabs" data-role="listview" data-split-icon="gear" data-filter="true">
		<li class="controller${params.controller == 'home' ? " active" : ""}">
			<g:link controller="home" action="index">
				<g:message code="home.label" default="Home"/>
			</g:link>
		</li>
		<li class="controller${params.controller == 'scene' ? " active" : ""}">
			<g:link controller="scene" action="index">
				<g:message code="scene.label" default="Scene"/>
			</g:link>
		</li>
		
		
	<%--
		<g:each status="i" var="c" in="${grailsApplication.controllerClasses.sort { it.logicalPropertyName } }">
			<li class="controller${params.controller == c.logicalPropertyName ? " active" : ""}">
				<g:link controller="${c.logicalPropertyName}" action="index">
					<g:message code="${c.logicalPropertyName}.label" default="${c.logicalPropertyName.capitalize()}"/>
				</g:link>
			</li>
		</g:each>
	 --%>
	</ul>
</div>
