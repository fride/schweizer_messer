package testfirst
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FreeSpec
import org.scalatest.prop.PropertyChecks

@RunWith(classOf[JUnitRunner])
class DataDrivenTest extends FreeSpec with ShouldMatchers with PropertyChecks{
	"Namen sollen aus Buchstaben bestehen" - {
	  val data = Table( ("Name", "Anzahl der Buchstaben"), 
			  			("Maxim", 5), 
			  			("Jan", 3));
	  "alle Namen aus der Tabelle sollen richtige Azahl der Buchstaben haben" in {
	    forAll (data) { (name, expectedLength) =>
	    	name should have length(expectedLength)
	    }	    
	  }
	}
}