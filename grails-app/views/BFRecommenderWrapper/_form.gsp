<%@ page import="webbluefinder.process.BFRecommenderWrapper" %>



			<div class="${hasErrors(bean: BFRecommenderWrapperInstance, field: 'scene', 'error')} ">
				<label for="scene" class="control-label"><g:message code="BFRecommenderWrapper.scene.label" default="Scene" /></label>
				<div>
					<g:select class="form-control" id="scene" name="scene.id" from="${webbluefinder.Scene.list()}" optionKey="id" value="${BFRecommenderWrapperInstance?.scene?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: BFRecommenderWrapperInstance, field: 'scene', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: BFRecommenderWrapperInstance, field: 'processErrors', 'error')} ">
				<label for="processErrors" class="control-label"><g:message code="BFRecommenderWrapper.processErrors.label" default="Process Errors" /></label>
				<div>
					<g:textField class="form-control" name="processErrors" readonly="readonly" value="${BFRecommenderWrapperInstance?.processErrors}"/>
					<span class="help-inline">${hasErrors(bean: BFRecommenderWrapperInstance, field: 'processErrors', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: BFRecommenderWrapperInstance, field: 'bfrFrom', 'error')} ">
				<label for="bfrFrom" class="control-label"><g:message code="BFRecommenderWrapper.bfrFrom.label" default="Bfr From" /></label>
				<div>
					<g:textField class="form-control" name="bfrFrom" value="${BFRecommenderWrapperInstance?.bfrFrom}"/>
					<span class="help-inline">${hasErrors(bean: BFRecommenderWrapperInstance, field: 'bfrFrom', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: BFRecommenderWrapperInstance, field: 'bfrTo', 'error')} ">
				<label for="bfrTo" class="control-label"><g:message code="BFRecommenderWrapper.bfrTo.label" default="Bfr To" /></label>
				<div>
					<g:textField class="form-control" name="bfrTo" value="${BFRecommenderWrapperInstance?.bfrTo}"/>
					<span class="help-inline">${hasErrors(bean: BFRecommenderWrapperInstance, field: 'bfrTo', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: BFRecommenderWrapperInstance, field: 'db', 'error')} ">
				<label for="db" class="control-label"><g:message code="BFRecommenderWrapper.db.label" default="Db" /></label>
				<div>
					<g:textField class="form-control" name="db" value="${BFRecommenderWrapperInstance?.db}"/>
					<span class="help-inline">${hasErrors(bean: BFRecommenderWrapperInstance, field: 'db', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: BFRecommenderWrapperInstance, field: 'maxRecommendations', 'error')} required">
				<label for="maxRecommendations" class="control-label"><g:message code="BFRecommenderWrapper.maxRecommendations.label" default="Max Recommendations" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="maxRecommendations" type="number" value="${BFRecommenderWrapperInstance.maxRecommendations}" required=""/>
					<span class="help-inline">${hasErrors(bean: BFRecommenderWrapperInstance, field: 'maxRecommendations', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: BFRecommenderWrapperInstance, field: 'neighbour', 'error')} required">
				<label for="neighbour" class="control-label"><g:message code="BFRecommenderWrapper.neighbour.label" default="Neighbour" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="neighbour" type="number" value="${BFRecommenderWrapperInstance.neighbour}" required=""/>
					<span class="help-inline">${hasErrors(bean: BFRecommenderWrapperInstance, field: 'neighbour', 'error')}</span>
				</div>
			</div>

