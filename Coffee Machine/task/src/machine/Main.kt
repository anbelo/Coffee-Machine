package machine

class Machine {

    private var water = 400
    private var coffeeBeans = 120
    private var milk = 540
    private var money = 550
    private var cup = 9

    var isPowerOn = true
        private set

    private enum class Coffee(val water: Int, val beans: Int, val milk: Int, val cost: Int) {
        ESPRESSO(250, 16, 0, 4),
        LATTE(350, 20, 75, 7),
        CAPPUCCINO(200, 12, 100, 6)
    }

    private interface State {
        fun prompt()
        fun readInput(s: String)
    }

    private val chooseAction = ChooseAction(this)
    private val chooseCoffeeType = ChooseCoffeeType(this)
    private val fillWater = FillWater(this)
    private val fillMilk = FillMilk(this)
    private val fillCoffeeBeans = FillCoffeeBeans(this)
    private val fillCups = FillCups(this)

    private var currentState: State = chooseAction

    private class ChooseAction(val machine: Machine): State {
        private val buy = "buy"
        private val fill = "fill"
        private val take = "take"
        private val remaining = "remaining"
        private val exit = "exit"

        private fun takeMoney() {
            println()
            println("I gave you $${machine.money}")
            println()
            machine.money = 0
        }

        private fun remaining() {
            println("""
                
                The coffee machine has:
                ${machine.water} ml of water
                ${machine.milk} ml of milk
                ${machine.coffeeBeans} g of coffee beans
                ${machine.cup} disposable cups
                $${machine.money} of money
                
                """.trimIndent()
            )
        }

        override fun prompt() {
            println("Write action ($buy, $fill, $take, $remaining, $exit): ")
        }

        override fun readInput(s: String) {
            when (s) {
                buy -> {
                    machine.currentState = machine.chooseCoffeeType
                }
                fill -> {
                    machine.currentState = machine.fillWater
                }
                take -> {
                    takeMoney()
                    machine.currentState = machine.chooseAction
                }
                remaining -> {
                    remaining()
                    machine.currentState = machine.chooseAction
                }
                exit -> {
                    machine.isPowerOn = false
                }
                else -> println("Unknown Action")
            }
        }
    }

    private class ChooseCoffeeType(val machine: Machine): State {
        override fun prompt() {
            println()
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        }

        private fun makeCoffee(coffee: Coffee) {
            if (machine.water < coffee.water) {
                println("Sorry, not enough water!")
            } else if (machine.milk < coffee.milk) {
                println("Sorry, not enough milk!")
            } else if (machine.coffeeBeans < coffee.beans) {
                println("Sorry, not enough coffee beans!")
            } else if (machine.cup <= 0) {
                println("Sorry, not enough disposable cups!")
            } else {
                println("I have enough resources, making you a coffee!")
                machine.water -= coffee.water
                machine.milk -= coffee.milk
                machine.coffeeBeans -= coffee.beans
                machine.cup--
                machine.money += coffee.cost
            }
        }

        override fun readInput(s: String) {
            when (s) {
                "1" -> makeCoffee(Coffee.ESPRESSO)
                "2" -> makeCoffee(Coffee.LATTE)
                "3" -> makeCoffee(Coffee.CAPPUCCINO)
                "back" -> {} // ignore
                else -> println("Unknown drink")
            }
            println()
            machine.currentState = machine.chooseAction
        }
    }

    private class FillWater(val machine: Machine): State {
        override fun prompt() {
            println()
            println("Write how many ml of water do you want to add: ")
        }

        override fun readInput(s: String) {
            machine.water += s.toInt()
            machine.currentState = machine.fillMilk
        }
    }

    private class FillMilk(val machine: Machine): State {
        override fun prompt() {
            println("Write how many ml of milk do you want to add: ")
        }

        override fun readInput(s: String) {
            machine.milk += s.toInt()
            machine.currentState = machine.fillCoffeeBeans
        }
    }

    private class FillCoffeeBeans(val machine: Machine): State {
        override fun prompt() {
            println("Write how many grams of coffee beans do you want to add: ")
        }

        override fun readInput(s: String) {
            machine.coffeeBeans += s.toInt()
            machine.currentState = machine.fillCups
        }
    }

    private class FillCups(val machine: Machine): State {
        override fun prompt() {
            println("Write how many disposable cups of coffee do you want to add: ")
        }

        override fun readInput(s: String) {
            machine.cup += s.toInt()
            println()
            machine.currentState = machine.chooseAction
        }
    }

    fun prompt() {
        currentState.prompt()
    }

    fun readInput(s: String) {
        currentState.readInput(s)
    }

}


fun main() {
    val machine = Machine()
    do {
        machine.prompt()
        machine.readInput(readln())
    } while (machine.isPowerOn)
}

