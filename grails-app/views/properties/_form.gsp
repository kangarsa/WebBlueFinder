<%@ page import="webbluefinder.Properties" %>



			<div class="${hasErrors(bean: propertiesInstance, field: 'title', 'error')} required">
				<label for="title" class="control-label"><g:message code="properties.title.label" default="Title" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="title" required="" value="${propertiesInstance?.title}"/>
					<span class="help-inline">${hasErrors(bean: propertiesInstance, field: 'title', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: propertiesInstance, field: 'hostname', 'error')} required">
				<label for="hostname" class="control-label"><g:message code="properties.hostname.label" default="Hostname" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="hostname" required="" value="${propertiesInstance?.hostname}"/>
					<span class="help-inline">${hasErrors(bean: propertiesInstance, field: 'hostname', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: propertiesInstance, field: 'database', 'error')} required">
				<label for="database" class="control-label"><g:message code="properties.database.label" default="Database" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="database" required="" value="${propertiesInstance?.database}"/>
					<span class="help-inline">${hasErrors(bean: propertiesInstance, field: 'database', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: propertiesInstance, field: 'dbuser', 'error')} required">
				<label for="dbuser" class="control-label"><g:message code="properties.dbuser.label" default="Dbuser" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="dbuser" required="" value="${propertiesInstance?.dbuser}"/>
					<span class="help-inline">${hasErrors(bean: propertiesInstance, field: 'dbuser', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: propertiesInstance, field: 'dbpass', 'error')} required">
				<label for="dbpass" class="control-label"><g:message code="properties.dbpass.label" default="Dbpass" /><span class="required-indicator">*</span></label>
				<div>
					<g:textField class="form-control" name="dbpass" required="" value="${propertiesInstance?.dbpass}"/>
					<span class="help-inline">${hasErrors(bean: propertiesInstance, field: 'dbpass', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: propertiesInstance, field: 'dbImportedPIA', 'error')} ">
				<label for="dbImportedPIA" class="control-label"><g:message code="properties.dbImportedPIA.label" default="Db Imported PIA" /></label>
				<div>
					<g:textField class="form-control" name="dbImportedPIA" value="${propertiesInstance?.dbImportedPIA}"/>
					<span class="help-inline">${hasErrors(bean: propertiesInstance, field: 'dbImportedPIA', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: propertiesInstance, field: 'dbPassImportedPIA', 'error')} ">
				<label for="dbPassImportedPIA" class="control-label"><g:message code="properties.dbPassImportedPIA.label" default="Db Pass Imported PIA" /></label>
				<div>
					<g:textField class="form-control" name="dbPassImportedPIA" value="${propertiesInstance?.dbPassImportedPIA}"/>
					<span class="help-inline">${hasErrors(bean: propertiesInstance, field: 'dbPassImportedPIA', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: propertiesInstance, field: 'dbUserImportedPIA', 'error')} ">
				<label for="dbUserImportedPIA" class="control-label"><g:message code="properties.dbUserImportedPIA.label" default="Db User Imported PIA" /></label>
				<div>
					<g:textField class="form-control" name="dbUserImportedPIA" value="${propertiesInstance?.dbUserImportedPIA}"/>
					<span class="help-inline">${hasErrors(bean: propertiesInstance, field: 'dbUserImportedPIA', 'error')}</span>
				</div>
			</div>

