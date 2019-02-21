package me.jameshunt.algorithms

class LinkedChildList<T> {

    private var head: Node<T>? = null

    val size: Int
        get() {
            var currentNode = head ?: return 0

            var count = 1

            while (currentNode.next != null) {
                currentNode = currentNode.next!!
                count++
            }

            return count
        }

    fun insert(value: T) = when (head == null) {
        true -> head = Node(
            previous = null,
            next = null,
            child = null,
            value = value
        )
        false -> {
            val last = get(size - 1)
            last.next = Node(
                previous = last,
                next = null,
                child = null,
                value = value
            )
        }
    }

    fun insertChild(index: Int, childList: LinkedChildList<T>) {
        get(index).child = childList.head
    }

    fun get(index: Int): Node<T> {
        var currentNode = head ?: throw IndexOutOfBoundsException()

        if (index == 0) return currentNode

        var count = 0
        while (currentNode.next != null) {

            currentNode = currentNode.next!!

            count++
            if (count == index) {
                return currentNode
            }
        }

        throw IndexOutOfBoundsException()
    }

    fun flatten(): List<T> = head?.flatten() ?: emptyList()

    fun Node<T>.flatten(): List<T> {

        return listOf(this.value) +
                (this.child?.flatten()?: emptyList()) +
                (this.next?.flatten() ?: emptyList())
    }
}