package testfirst
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FreeSpec
import org.scalatest.prop.PropertyChecks

@RunWith(classOf[JUnitRunner])
class PropertyTest extends FreeSpec with ShouldMatchers with PropertyChecks {
  "Namen sollen aus Buchstaben bestehen" - {
    "alle Namen bestehen aus mehr als einem Buchstaben" in {
      forAll("name") { (name: String) =>
        whenever(name != "") {
          name.length should be > 0;
        }
      }
    }
  }
}