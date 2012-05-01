package testfirst
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class TestAsSpecification extends FunSpec with ShouldMatchers {
	describe("Namen sollen aus Buchstaben bestehen"){
	  var name : String = null
	  it("es produziert fehler wenn der Name noch nicht gesetzt ist") {
	    intercept[NullPointerException] {
	    	name.length();
	    }
	  }
	  it("es besteht aus 5 buchstaben wenn Maxim als Name zugewiesen ist"){
	    name = "Maxim"
	    name should have length(5)
	  }
	}
}