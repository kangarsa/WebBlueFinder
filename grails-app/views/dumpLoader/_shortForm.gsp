<%@ page import="webbluefinder.DumpLoader" %>

			<div class="${hasErrors(bean: dumpLoaderInstance, field: 'dumpPath', 'error')} ">
				<label for="dumpPath" class="control-label"><g:message code="dumpLoader.dumpPath.label" default="Dump Path" /></label>
				<div>
					<g:textField class="form-control" name="dumpPath" value="${dumpLoaderInstance?.dumpPath}"/>
					<span class="help-inline">${hasErrors(bean: dumpLoaderInstance, field: 'dumpPath', 'error')}</span>
				</div>
			</div>

