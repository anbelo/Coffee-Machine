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

    print("Write how many cups of coffee you will need: ")
    val cupCount = readln().toInt()
    print("""
        For $cupCount cups of coffee you will need:
        ${cupCount * 200} ml of water
        ${cupCount * 50} ml of milk
        ${cupCount * 15} g of coffee beans
    """.trimIndent())
}
