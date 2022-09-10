package machine

fun main() {
    println("""
        Starting to make a coffee
        Grinding coffee beans
        Boiling water
        Mixing boiled water with crushed coffee beans
        Pouring coffee into the cup
        Pouring some milk into the cup
        Coffee is ready!
    """.trimIndent())

    print("Write how many ml of water the coffee machine has: ")
    val waterAmount = readln().toInt()
    print("Write how many ml of milk the coffee machine has: ")
    val milkAmount = readln().toInt()
    print("Write how many grams of coffee beans the coffee machine has: ")
    val coffeeBeansAmount = readln().toInt()
    print("Write how many cups of coffee you will need: ")
    val cupCount = readln().toInt()

    val maxWaterCups = waterAmount / 200
    val maxMilkCups = milkAmount / 50
    val coffeeBeansCups = coffeeBeansAmount / 15
    val maxCups = maxWaterCups.coerceAtMost(maxMilkCups).coerceAtMost(coffeeBeansCups)

    if (cupCount < maxCups) {
        print("Yes, I can make that amount of coffee (and even ${maxCups - 1} more than that)")
    } else if (cupCount == maxCups) {
        print("Yes, I can make that amount of coffee")
    } else {
        print("No, I can make only $maxCups cups of coffee")
    }
}
