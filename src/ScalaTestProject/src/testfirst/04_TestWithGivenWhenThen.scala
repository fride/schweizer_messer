package testfirst
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.GivenWhenThen

@RunWith(classOf[JUnitRunner])
class TestWithGivenWhenThen extends FunSuite with ShouldMatchers with GivenWhenThen{

  test("Mein Name besteht aus 5 Buchstaben") {
    given("Ein Name")
    var name : String = null
    when("der Name auf Maxim gesetzt ist")
    name = "Maxim"
    then("Name besteht aus 5 Buchstaben")  
    name should have length(5)    
  }

}