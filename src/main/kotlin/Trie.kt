/**
 * A trie is a tree-like data structure that stores values associated with string keys.
 */
class Trie<T : Any> {
    /**
     * The root node of the trie.
     */
    val root: Node<T> = Node(null)

    /**
     * Returns the number of nodes in the trie.
     */
    val size: Int get() = root.size

    /**
     * Returns the value associated with the given key.
     */
    fun get(key: String): T? {
        return root.get(key, 0)?.value
    }

    /**
     * Inserts the given key-value pair into the trie.
     */
    fun put(key: String, value: T) {
        root.put(key, value, 0)
    }

    /**
     * Returns all keys in the trie.
     */
    fun keys(): Sequence<String> {
        return root.keysWithPrefix("")
    }

    /**
     * Returns all keys in the trie that start with the given prefix.
     */
    fun keysWithPrefix(prefix: String): Sequence<String> {
        val node = root.get(prefix, 0) ?: return emptySequence()
        return node.keysWithPrefix(prefix)
    }

    /**
     * Returns all keys in the trie that match the given pattern.
     */
    fun keysThatMatch(pattern: String): Sequence<String> {
        return root.keysThatMatch("", pattern)
    }

    /**
     * Returns the longest key in the trie that is a prefix of the given query.
     */
    fun longestPrefixOf(query: String): String {
        val length = root.search(query, 0, 0)
        return query.substring(0, length)
    }

    /**
     * Removes the given key from the trie.
     */
    fun delete(key: String) {
        root.delete(key, 0)
    }

    class Node<T : Any>(
        /**
         * The value associated with this node.
         */
        var value: T?,
    ) {
        /**
         * The children of this node.
         */
        val next: MutableMap<Char, Node<T>> = mutableMapOf()

        /**
         * Returns the number of nodes in the trie.
         */
        val size: Int get() = next.values.sumOf { it.size } + if (value == null) 0 else 1

        /**
         * Returns the node associated with the given key.
         */
        internal fun get(key: String, depth: Int): Node<T>? {
            if (depth == key.length) {
                return this
            }
            val char = key[depth]
            return next[char]?.get(key, depth + 1)
        }

        /**
         * Inserts the given key-value pair into the trie.
         */
        internal fun put(key: String, value: T, depth: Int) {
            if (depth == key.length) {
                this.value = value
                return
            }
            val char = key[depth]
            val next = next.getOrPut(char) {
                Node(null)
            }
            next.put(key, value, depth + 1)
        }

        /**
         * Returns all keys in the trie that start with the given prefix.
         */
        internal fun keysWithPrefix(prefix: String): Sequence<String> = sequence {
            if (value != null) {
                yield(prefix)
            }
            for ((char, node) in next) {
                yieldAll(node.keysWithPrefix(prefix + char))
            }
        }

        /**
         * Returns all keys in the trie that match the given pattern.
         */
        internal fun keysThatMatch(prefix: String, pattern: String): Sequence<String> = sequence {
            val depth = prefix.length
            if (depth == pattern.length) {
                if (value != null) yield(prefix)
                return@sequence
            }
            for ((char, node) in next) {
                if (pattern[depth] == '.' || pattern[depth] == char) {
                    yieldAll(node.keysThatMatch(prefix + char, pattern))
                }
            }
        }

        /**
         * Returns the length of the longest key in the trie that is a prefix of the given query.
         */
        internal fun search(query: String, depth: Int, length: Int): Int {
            @Suppress("NAME_SHADOWING")
            var length = length
            if (value != null) {
                length = depth
            }
            if (depth == query.length) {
                return length
            }
            val char = query[depth]
            val node = next[char] ?: return length
            return node.search(query, depth + 1, length)
        }

        /**
         * Removes the given key from the trie.
         */
        internal fun delete(key: String, depth: Int) {
            if (depth == key.length) {
                value = null
                return
            }
            val char = key[depth]
            val node = next[char] ?: return
            node.delete(key, depth + 1)
            if (node.value == null && node.next.isEmpty()) {
                next.remove(char)
            }
        }
    }
}
