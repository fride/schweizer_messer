package de.itemis.scala.ejb

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import javax.ejb.embeddable.EJBContainer
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

/**
 *
 *
 *
 * @author Friderici
 */
@RunWith(classOf[JUnitRunner])
class HelloServiceSpec extends FlatSpec with ShouldMatchers{

	"The Hello Service" should "Say Hello World for Path /hello/world" in {

		val service = TestContextTools.lookup[HelloService]("java:gloval/scala-ejb/HelloService/local")

		service match {
			case Some(s) => s.world() should  equal ("Hello World")
			case None => fail("No Hello Service found.")
		}
	}

}

object TestContextTools {
	import java.{util => ju}
	import java.io.File

	lazy val properties = {
		val props = new ju.HashMap[Object,Object]()
		props.put(EJBContainer.MODULES, new File("target/classes"));
		//props.put("org.glassfish.ejb.embedded.glassfish.installation.root",
		//	"/Applications/NetBeans/sges-v3/glassfish");
		props
	}
	lazy val container: EJBContainer =  EJBContainer.createEJBContainer(properties)

	lazy val context = container.getContext


	def lookup[T](name: String) = {
		val service = TestContextTools.context.lookup(name)
		if (null == service) None
		else Some(service.asInstanceOf[T])
	}
}
