package de.itemis.scala.regex


sealed trait Gender

object Female extends Gender {
	override def toString ="Frau"
}

object Male extends Gender {
	override def toString ="Herr"
}

case class Person(gender: Gender, firstName: String, lastName: String)

object PersonHelper {

	val Name = """(Herr|Frau) ([A-Z][a-z]+) ([A-Z][a-z]+)""".r

	def gender(str: String) =
		if ("Herr" == str)
			Male
		else
			Female

	def findAllPersons(str: String) = {
		for {
			m <- Name.findAllIn(str).matchData
		} yield Person(gender(m.group(1)), m.group(2), m.group(3))
	}

	def test = findAllPersons("""
		Herr Jan Friderici,
		Herr Maxim Zaks,
		Frau Gundula Gaukel,
		Onkel Dagobert,
		Frau Ada Lovelace.
	""")
}

/**
 *
 * Date: 10.04.12
 * Time: 20:46
 *
 * @author Friderici
 */

object RegexEx {
	/**
	 * An email address.
	 */
	val Email = """([A-Za-z0-9_\.]+)@(.+)""".r

	val parseEmail = (x: String) => x match {
		case Email(name, domain) => Right((name, domain))
		case _ => Left("Not an email address: " + x)
	}

	val parseEmail2 = (x: String) => try {
		val Email(name, domain) = x
		(name, domain)
	} catch {
		case e: MatchError => ("ungültige", "email")
	}

	val successHandler = (name: String, domain: String) =>
		println("Found an email: user is: " + name + ", " + "domain is: " + domain)

	/**
	 *
	 * @param args
	 */
	def main(args: Array[String]) {
		("jan.friderici@itemis.de" :: "zaks@itemis.de" :: "Ungültiger String" :: Nil).map {
			str =>
				parseEmail(str)
		} foreach {
			m =>
				m.fold(
					println(_),
					successHandler.tupled(_) // parseEmail returns a tuple but successHandler has two parameters...
				)
		}

		// pattern matching und try catch als statements
		val (m, d) = try {
			val Email(m, d) = "Jan(at)friderici.net"
			(m, d)
		} catch {
			case e: MatchError => ("ungültige", "email")
		}

		println(m + "@" + d)

		val Email(name, domain) = "jan@friderici.net"
		println(name + "@" + domain)

		val Name = "(Herr|Frau) (a-zA-Z)+ (a-zA-Z)+".r

		PersonHelper.test.foreach(println)
	}
}
