package de.itemis.scala.ejb

import javax.ejb.LocalBean
import annotation.target.field
import javax.persistence.{EntityManager, PersistenceContext}
import de.itemis.scala.examples.domain.Person
import javax.ws.rs.{PathParam, GET, Path}

/**
 *
 * Date: 07.04.12
 * Time: 22:46
 *
 * @author Friderici
 */
@Path("person")
@javax.inject.Singleton
@LocalBean
class PersonResource {

	@(PersistenceContext @field)
	private var em:EntityManager = _

	@GET
	def getAll =
		em.createQuery("select p from Person p").getResultList.asInstanceOf[java.util.List[Person]]

	@GET
	@Path("{login}")
	def get(@PathParam("login") login: String) =
		em.createQuery("select p from Person p where p.login = ?").getResultList.asInstanceOf[java.util.List[Person]]
}
