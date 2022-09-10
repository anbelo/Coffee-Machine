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
const val REMAINING = "remaining"
const val EXIT = "exit"

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
    while (true) {
        print("Write action ($BUY, $FILL, $TAKE. $REMAINING, $EXIT): ")
        when (readln()) {
            BUY -> buyCoffee()
            FILL -> fillCoffeeMachine()
            TAKE -> takeMoney()
            REMAINING -> state()
            EXIT -> break
            else -> println("Unknown Action")
        }
    }
}

fun buyCoffee() {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    when (readln()) {
        "1" -> makeCoffee(ESPRESSO_WATER, 0, ESPRESSO_COFFEE_BEANS, ESPRESSO_COST)
        "2" -> makeCoffee(LATTE_WATER, LATTE_MILK, LATTE_COFFEE_BEANS, LATTE_COST)
        "3" -> makeCoffee(CAPPUCCINO_WATER, CAPPUCCINO_MILK, CAPPUCCINO_COFFEE_BEANS, CAPPUCCINO_COST)
        "back" -> {} // ignore
        else -> println("Unknown drink")
    }
}

fun makeCoffee(water: Int, milk: Int, coffeeBeans: Int, cost: Int) {
    if (MACHINE_HAS_WATER < water) {
        println("Sorry, not enough water!")
    } else if (MACHINE_HAS_MILK < milk) {
        println("Sorry, not enough milk!")
    } else if (MACHINE_HAS_COFFEE_BEANS < coffeeBeans) {
        println("Sorry, not enough coffee beans!")
    } else if (MACHINE_HAS_CUP <= 0) {
        println("Sorry, not enough disposable cups!")
    } else {
        println("I have enough resources, making you a coffee!")
        MACHINE_HAS_WATER -= water
        MACHINE_HAS_MILK -= milk
        MACHINE_HAS_COFFEE_BEANS -= coffeeBeans
        MACHINE_HAS_CUP--
        MACHINE_HAS_MONEY += cost
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

