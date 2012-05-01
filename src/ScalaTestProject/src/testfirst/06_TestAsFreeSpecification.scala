package testfirst
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FreeSpec

@RunWith(classOf[JUnitRunner])
class TestAsFreeSpecification extends FreeSpec with ShouldMatchers {
	"Namen sollen aus Buchstaben bestehen" - {
	  var name : String = null
	  "es produziert fehler wenn der Name noch nicht gesetzt ist" in {
	    intercept[NullPointerException] {
	    	name.length();
	    }
	  }
	  "es besteht aus 5 buchstaben wenn Maxim als Name zugewiesen ist" in {
	    name = "Maxim"
	    name should have length(5)
	  }
	}
}