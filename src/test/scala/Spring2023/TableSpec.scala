package Spring2023

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.util.Random

class TableSpec extends AnyFlatSpec with Matchers {

  behavior of "Table"

  private val table = Table(LazyList.iterate(1)(a => 2*a+1).take(10))

  it should "apply Ints" in {
    val t = Table(7, 63, 255)
    t shouldBe Table(List(7, 63, 255))
  }

  it should "do a sample" in {
    implicit val random: Random = new Random(0)
    println(table)
    val y = table.sample(2)
    y.size shouldBe 3
    y shouldBe Table(7, 63, 255)
  }

  it should "do lensFilter" in {
    val table: Table[String] = Table("ABC", "BCD", "ABA")
    val result: Table[String] = table.lensFilter[Char](x => x == 'A')(w => w.head)
    result.size shouldBe 2
    result shouldBe Table("ABC", "ABA")
  }

  it should "do filter" in {
    val table: Table[String] = Table("ABC", "BCD", "ABA")
    val result = table.filter(x => x.startsWith("A"))
    result.size shouldBe 2
    result shouldBe Table("ABC", "ABA")
  }
}
