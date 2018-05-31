# Scalafix rules

This project contains a bunch of basic rules Scala developers should be following.


## Usage

*project/plugins.sbt*:
```
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "<latest version>")
```

*.scalafix.conf*:
```
rules = [
  "https://raw.githubusercontent.com/joan38/scalarules/master/rules/src/main/scala/fix/NoSomeConstructor.scala"
]
```

And run scalafix: 
```
sbt scalafix
```


## Known limitations

The current way of adding rules is not ideal since it envoles giving the full URI to the rule. This something we are
looking to improve in scalafix.
