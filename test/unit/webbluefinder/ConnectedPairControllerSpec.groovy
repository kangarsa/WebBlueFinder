package webbluefinder

import spock.lang.Specification
import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

@TestFor(ConnectedPairController)
class ConnectedPairControllerSpec extends Specification {
	
	def testShowModelAndView() {
		given:
			def cp1 = ["id": 4, "path":"#from / #to"]
			def cp2 = ["id":5, "path":"#from / * / Cat:#from_(Venezuela)_politicians / #to"]
			def path1 = ["Acción_Democrática / Gonzalo_Barrios"]
			def path2 = ["Acción_Democrática / * / Categoría:Acción_Democrática_(Venezuela)_politicians / Gonzalo_Barrios"]
		when:	
			controller.show(4)
		then:
			view == '/connectedPair/show.gsp'
			model.pqc.contains(cp1)
			model.pqc.contains(cp2)
			model.connected == "Acción_Democrática , Gonzalo_Barrios"
			model.instanciated.contains(path1[0])
			model.instanciated.contains(path2[0])
			model.infoboxes.getAt(0).contains "Contenido del infobox" //Hay que ver cómo podría testear esto
	}

}
