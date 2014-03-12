<%@ page import="webbluefinder.Properties" %>



<div class="fieldcontain ${hasErrors(bean: propertiesInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="properties.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${propertiesInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertiesInstance, field: 'hostname', 'error')} required">
	<label for="hostname">
		<g:message code="properties.hostname.label" default="Hostname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="hostname" required="" value="${propertiesInstance?.hostname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertiesInstance, field: 'database', 'error')} required">
	<label for="database">
		<g:message code="properties.database.label" default="Database" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="database" required="" value="${propertiesInstance?.database}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertiesInstance, field: 'dbuser', 'error')} required">
	<label for="dbuser">
		<g:message code="properties.dbuser.label" default="Dbuser" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dbuser" required="" value="${propertiesInstance?.dbuser}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertiesInstance, field: 'dbpass', 'error')} required">
	<label for="dbpass">
		<g:message code="properties.dbpass.label" default="Dbpass" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dbpass" required="" value="${propertiesInstance?.dbpass}"/>
</div>

