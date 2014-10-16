package webbluefinder

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

@TestFor(PathQueryController)
class PathQueryControllerSpec extends Specification {

	def testShowModelAndView() {
		given:
			def cp1 = ["id": 1426, "Page":"Peelite , George_Hamilton-Gordon"]
			def cp2 = ["id": 1427, "Page":"Peelite , William_Gladstone"] 
			def cp3 = ["id": 1586, "Page":"Tory , Duque_de_Wellington"]
			def cp4 = ["id": 1587, "Page":"Tory , George_Canning"]
			def cp5 = ["id": 1588, "Page":"Tory , John_Stuart_(conde_de_Bute)"]
			def cp6 = ["id": 1591, "Page":"Tory , Spencer_Perceval"]
			def cp7 = ["id": 1592, "Page":"Tory , William_Wyndham_Grenville"]
			
			def path1 = [["id": 4, "cant": 1650, "path":"#from / #to"]]
			def path2 = [["id": 8, "cant": 594, "path":"#from / * / Cat:#from_politicians / #to"]]
			def path3 = [["id": 35, "cant": 124, "path":"#from / * / Cat:Republicans_(United_States) / #to"]]
			def path4 = [["id": 125, "cant": 122, "path":"#from / * / Cat:Democrats_(United_States) / #to"]]
			def path5 = [["id": 93, "cant": 59, "path":"#from / * / Cat:Políticos_del_Partido_Justicialista / #to"]]
			
			def ins1 = ["Peelite / * / Categoría:Prime_Ministers_of_the_United_Kingdom / George_Hamilton-Gordon"]
			def ins2 = ["Peelite / * / Categoría:Prime_Ministers_of_the_United_Kingdom / William_Gladstone"]
			def ins3 = ["Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / Duque_de_Wellington"]
			def ins4 = ["Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / George_Canning"]
			def ins5 = ["Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / John_Stuart_(conde_de_Bute)"]
			def ins6 = ["Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / Spencer_Perceval"]
			def ins7 = ["Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / William_Wyndham_Grenville"]
		
			def rel = ["id": 204, "cant": 7, "path":"#from / * / Cat:Prime_Ministers_of_the_United_Kingdom / #to"]
		/**
			*[Peelite / * / Categoría:Prime_Ministers_of_the_United_Kingdom / George_Hamilton-Gordon, 
			*Peelite / * / Categoría:Prime_Ministers_of_the_United_Kingdom / William_Gladstone, 
			*Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / Duque_de_Wellington, 
			*Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / George_Canning, 
			*Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / John_Stuart_(conde_de_Bute), 
			*Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / Spencer_Perceval, 
			*Tory / * / Categoría:Prime_Ministers_of_the_United_Kingdom / William_Wyndham_Grenville]
		 */
		
		when:
			controller.show(204)
		then:
			view == '/pathQuery/show.gsp'
			model.pqcp.contains(cp1)
			model.pqcp.contains(cp2)
			model.pqcp.contains(cp3)
			model.pqcp.contains(cp4)
			model.pqcp.contains(cp5)
			model.pqcp.contains(cp6)
			model.pqcp.contains(cp7)
			model.path == "#from / * / Cat:Prime_Ministers_of_the_United_Kingdom / #to"
			model.pqr[0] == path1[0]
			model.pqr[1] == path2[0]
			model.pqr[2] == path3[0]
			model.pqr[3] == path4[0]
			model.pqr[4] == path5[0]
			model.PQrel[0] == rel
			model.instantiated[0].contains(ins1)
			model.instantiated[1].contains(ins2)
			model.instantiated[2].contains(ins3)
			model.instantiated[3].contains(ins4)
			model.instantiated[4].contains(ins5)
			model.instantiated[5].contains(ins6)
			model.instantiated[6].contains(ins7)
			model.pathsMap.replaceAll("\\s+","") == '''
			{
				"name": "Peelite", 
				"children": [
					{
						"name": "Categoría:Prime_Ministers_of_the_United_Kingdom", 
						"children": [
							{
								"name": "George_Hamilton-Gordon"
							},
							{
								"name": "William_Gladstone"
							}
						]
					}
				]
			},
			{
				"name": "Tory", 
				"children": [
					{
						"name": "Categoría:Prime_Ministers_of_the_United_Kingdom", 
						"children": [
							{
								"name": "Duque_de_Wellington"
							},
							{
								"name": "George_Canning"
							},
							{
								"name": "John_Stuart_(conde_de_Bute)"
							},
							{
								"name": "Spencer_Perceval"
							},
							{
								"name": "William_Wyndham_Grenville"
							}
						]
					}
				]
			}	
			'''.replaceAll("\\s+","")
			model.treeSize == 13 * 7
		
		
    }
}
