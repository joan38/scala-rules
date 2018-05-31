/*
rule = "scala:fix.NoSomeConstructor"
 */
package fix.tests

import scala.{Some => MySome}

object NoSomeConstructor {
  MySome(42)
  Right(42)
}
