package fix

import scalafix.v0._
import scala.meta._

import scalafix.util.SymbolMatcher

final case class NoSomeConstructor(index: SemanticdbIndex) extends SemanticRule(index, "NoSomeConstructor") {

  override def description: String =
    "Rewrite that replaces Some() constructor by Option() constructor"

  override def fix(ctx: RuleCtx): Patch = {
    val someConstructor = SymbolMatcher.normalized(Symbol("_root_.scala.Some."))

    ctx.tree.collect {
      case Term.Apply(someConstructor(fun), _) =>
        ctx.replaceTree(fun, "Option").atomic
    }.asPatch
  }
}
