package testfirst
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class BetterThanJunit extends FunSuite {
	
	test("Mein Name besteht aus 5 Buchstaben"){
	  assert("maxim".length == 5)
	}
	
	test("Mein Name besteht aus 6 Buchstaben"){
	  assert("maxim".length == 6)
	}
}