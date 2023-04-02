fun main() {
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

    println("==> trie.get:")
    println(trie.get("she")) // 0
    println(trie.get("sells")) // 1
    println(trie.get("sea")) // 6
    println(trie.get("shells")) // 3
    println(trie.get("by")) // 4
    println(trie.get("the")) // 5
    println(trie.get("shore")) // 7
    println(trie.get("seashore")) // 8
    println(trie.get("shores")) // null

    println("==> trie.keysWithPrefix:")
    println(trie.keysWithPrefix("sh").toList()) // [she, shells, shore]
    println(trie.keysWithPrefix("sea").toList()) // [sea, seashores]
    println(trie.keysWithPrefix("sells").toList()) // [sells]
    println(trie.keysWithPrefix("sell").toList()) // [sells]
    println(trie.keysWithPrefix("sellz").toList()) // []

    println("==> trie.keysThatMatch:")
    println(trie.keysThatMatch("s.a").toList()) // [sea]
    println(trie.keysThatMatch("s...s").toList()) // [sells]
    println(trie.keysThatMatch("..e").toList()) // [she, the]
}
