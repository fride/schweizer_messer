package de.itemis.scala.ejb

import javax.ws.rs.{GET, Path}
import annotation.target.field
import javax.persistence.{PersistenceContext, EntityManager}
import javax.inject.Singleton
import javax.ejb.{LocalBean, EJB, Local, Stateless}


@Path("/hello")
@Singleton
@LocalBean
class HelloService{

	@(PersistenceContext @field)
	private var em:EntityManager = _

	@GET
	@Path("/world")
	def world() = "Hello World"
}
