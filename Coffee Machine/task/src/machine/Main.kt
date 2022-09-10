package machine

const val ESPRESSO_WATER = 250
const val ESPRESSO_COFFEE_BEANS = 16
const val ESPRESSO_COST = 4

const val LATTE_WATER = 350
const val LATTE_MILK = 75
const val LATTE_COFFEE_BEANS = 20
const val LATTE_COST = 7

const val CAPPUCCINO_WATER = 200
const val CAPPUCCINO_MILK = 100
const val CAPPUCCINO_COFFEE_BEANS = 12
const val CAPPUCCINO_COST = 6

const val BUY = "buy"
const val FILL = "fill"
const val TAKE = "take"

var MACHINE_HAS_WATER = 400
var MACHINE_HAS_MILK = 540
var MACHINE_HAS_COFFEE_BEANS = 120
var MACHINE_HAS_CUP = 9
var MACHINE_HAS_MONEY = 550

fun main() {
    chooseAction()
}

fun state() {
    println("""
        The coffee machine has:
        $MACHINE_HAS_WATER ml of water
        $MACHINE_HAS_MILK ml of milk
        $MACHINE_HAS_COFFEE_BEANS g of coffee beans
        $MACHINE_HAS_CUP disposable cups
        $$MACHINE_HAS_MONEY of money
    """.trimIndent())
}

fun chooseAction() {
    state()

    print("Write action ($BUY, $FILL, $TAKE): ")
    when (readln()) {
        BUY -> buyCoffee()
        FILL -> fillCoffeeMachine()
        TAKE -> takeMoney()
        else -> println("Unknown Action")
    }

    state()
}

fun buyCoffee() {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
    when (readln().toInt()) {
        1 -> makeEspresso()
        2 -> makeLatte()
        3 -> makeCappuccino()
        else -> println("Unknown drink")
    }
}

fun makeEspresso() {
    if (ESPRESSO_WATER <= MACHINE_HAS_WATER
        && ESPRESSO_COFFEE_BEANS <= MACHINE_HAS_COFFEE_BEANS
        && 0 < MACHINE_HAS_CUP)
    {
        MACHINE_HAS_WATER -= ESPRESSO_WATER
        MACHINE_HAS_COFFEE_BEANS -= ESPRESSO_COFFEE_BEANS
        MACHINE_HAS_CUP--
        MACHINE_HAS_MONEY += ESPRESSO_COST
    }
}

fun makeLatte() {
    if (LATTE_WATER <= MACHINE_HAS_WATER
        && LATTE_MILK <= MACHINE_HAS_MILK
        && LATTE_COFFEE_BEANS <= MACHINE_HAS_COFFEE_BEANS
        && 0 < MACHINE_HAS_CUP)
    {
        MACHINE_HAS_WATER -= LATTE_WATER
        MACHINE_HAS_MILK -= LATTE_MILK
        MACHINE_HAS_COFFEE_BEANS -= LATTE_COFFEE_BEANS
        MACHINE_HAS_CUP--
        MACHINE_HAS_MONEY += LATTE_COST
    }
}

fun makeCappuccino() {
    if (CAPPUCCINO_WATER <= MACHINE_HAS_WATER
        && CAPPUCCINO_MILK <= MACHINE_HAS_MILK
        && CAPPUCCINO_COFFEE_BEANS <= MACHINE_HAS_COFFEE_BEANS
        && 0 < MACHINE_HAS_CUP)
    {
        MACHINE_HAS_WATER -= CAPPUCCINO_WATER
        MACHINE_HAS_MILK -= CAPPUCCINO_MILK
        MACHINE_HAS_COFFEE_BEANS -= CAPPUCCINO_COFFEE_BEANS
        MACHINE_HAS_CUP--
        MACHINE_HAS_MONEY += CAPPUCCINO_COST
    }
}

fun fillCoffeeMachine() {
    print("Write how many ml of water do you want to add: ")
    MACHINE_HAS_WATER += readln().toInt()
    print("Write how many ml of milk do you want to add: ")
    MACHINE_HAS_MILK += readln().toInt()
    print("Write how many grams of coffee beans do you want to add: ")
    MACHINE_HAS_COFFEE_BEANS += readln().toInt()
    print("Write how many disposable cups of coffee do you want to add: ")
    MACHINE_HAS_CUP += readln().toInt()
}

fun takeMoney() {
    println("I gave you $$MACHINE_HAS_MONEY")
    MACHINE_HAS_MONEY = 0
}

