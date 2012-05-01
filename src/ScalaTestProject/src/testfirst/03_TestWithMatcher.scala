package testfirst
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class TestWithMatcher extends FunSuite with ShouldMatchers {
  test("Mein Name besteht aus 5 Buchstaben") {
    "maxim" should have length(5)
    
  }
  test("Mein Name besteht aus 6 Buchstaben") {
    "maxim" should have length(6)
  }
}