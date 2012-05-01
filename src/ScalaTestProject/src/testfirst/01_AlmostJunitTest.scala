package testfirst
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.Suite

@RunWith(classOf[JUnitRunner])
class AlmostJunitTest extends Suite {
	def testMe(){
	  assert("maxim".length == 5)
	}
	def testMeAgain(){
	  assert("maxim".length == 6)
	}
}