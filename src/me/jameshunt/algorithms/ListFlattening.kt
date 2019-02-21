package me.jameshunt.algorithms

fun main(vararg cows: String) {

    val list = LinkedChildList<Int>()

    list.insert(5)
    list.insertChild(0, LinkedChildList<Int>().also { a ->
        a.insert(6)

        a.insert(25)
        a.insertChild(1, LinkedChildList<Int>().also { b ->
            b.insert(8)
        })


        a.insert(6)
        a.insertChild(2, LinkedChildList<Int>().also { b ->
            b.insert(9)
            b.insertChild(0, LinkedChildList<Int>().also { c ->
                c.insert(7)
            })
        })
    })


    list.insert(33)
    list.insert(17)

    list.insert(2)
    list.insertChild(3, LinkedChildList<Int>().also { a ->
        a.insert(2)
        a.insert(7)

        a.insertChild(0, LinkedChildList<Int>().also { b ->
            b.insert(12)
            b.insert(5)

            b.insertChild(0, LinkedChildList<Int>().also { c ->
                c.insert(21)
                c.insert(3)
            })
        })
    })

    list.insert(1)


    println(list.flatten())

}


data class Node<T>(
    val previous: Node<T>?,
    var next: Node<T>?,
    var child: Node<T>?,
    val value: T
)