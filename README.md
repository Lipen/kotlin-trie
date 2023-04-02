# Trie in Kotlin

[![Build Status](https://github.com/Lipen/kotlin-trie/workflows/Build/badge.svg?branch=master)](https://github.com/Lipen/kotlin-trie/actions)

> Simple implementation of a trie in Kotlin according to Sedgewick's [Algorithms](https://www.amazon.com/Algorithms-4th-Robert-Sedgewick/dp/032157351X) book.

## Example

```kotlin
val trie = Trie<Int>()
trie.put("she", 0)
trie.put("sells", 1)
trie.put("sea", 2)
trie.put("shells", 3)
trie.put("by", 4)
trie.put("the", 5)
trie.put("sea", 6)
trie.put("shore", 7)
trie.put("seashore", 8)

trie.get("she") // 0
trie.get("sells") // 1
trie.get("sea") // 6
trie.get("shells") // 3
trie.get("by") // 4
trie.get("the") // 5
trie.get("shore") // 7
trie.get("seashore") // 8
trie.get("shores") // null

trie.keysWithPrefix("sh") // [she, shells, shore]
trie.keysWithPrefix("sea") // [sea, seashore]
trie.keysWithPrefix("sells") // [sells]
trie.keysWithPrefix("sell") // [sells]
trie.keysWithPrefix("sellz") // []

println(trie.keysThatMatch("s.a").toList()) // [sea]
println(trie.keysThatMatch("s...s").toList()) // [sells]
println(trie.keysThatMatch("..e").toList()) // [she, the]
```
