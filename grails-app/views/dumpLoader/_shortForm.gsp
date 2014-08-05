<%@ page import="webbluefinder.DumpLoader" %>

			<div class="${hasErrors(bean: dumpLoaderInstance, field: 'dumpPath', 'error')} ">
				<label for="dumpPath" class="control-label"><g:message code="dumpLoader.dumpPath.label" default="Dump Path" /></label>
				<div>
					<g:textField class="form-control" name="dumpPath" placeholder="<g:message code="dumpLoader.hint.sqlPath" default="/Absolute/Path/To/SQL/Dump.sql" />"  value="${dumpLoaderInstance?.dumpPath}"/>
					<span class="help-inline">${hasErrors(bean: dumpLoaderInstance, field: 'dumpPath', 'error')}</span>
				</div>
			</div>

