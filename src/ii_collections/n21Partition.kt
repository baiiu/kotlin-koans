package ii_collections

import java.util.function.Predicate

fun example8() {
    val numbers = listOf(1, 3, -4, 2, -11)

    // The details (how multi-assignment works) will be explained later in the 'Conventions' task
    val (positive, negative) = numbers.partition { it > 0 }

    positive == listOf(1, 3, 2)
    negative == listOf(-4, -11)
}

fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> {
    // Return customers who have more undelivered orders than delivered
//    todoCollectionTask()


//    val (list, list1) = this.customers.flatMap { it.orders }.partition { it.isDelivered }


    return this.customers.filter {

        val (delivered, undelivered) = it.orders.partition { it.isDelivered }

        return@filter undelivered.size > delivered.size

    }.toSet()


}
