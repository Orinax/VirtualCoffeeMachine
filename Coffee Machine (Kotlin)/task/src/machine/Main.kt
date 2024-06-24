package machine

const val ESPRESSO = 1
const val LATTE = 2
const val CAPPUCCINO = 3

class CoffeeMachine(
    private var waterAmount: Int,
    private var milkAmount: Int,
    private var coffeeBeansAmount: Int,
    private var disposableCupsAmount: Int,
    var moneyAmount: Int) {
    init {
        if ((waterAmount + milkAmount + coffeeBeansAmount + disposableCupsAmount) == 0) {
            waterAmount = 400
            milkAmount = 540
            coffeeBeansAmount = 120
            disposableCupsAmount = 9
            moneyAmount = 550
        }
    }

    fun checkIngredients(choice: Int): Boolean {
        val enoughIngredients: Boolean
        if ((choice == ESPRESSO) and
            (waterAmount >= 250) and
            (milkAmount >= 0) and
            (coffeeBeansAmount >= 16) and
            (disposableCupsAmount >= 1)) {
            enoughIngredients = true
        } else if ((choice == LATTE) and
            (waterAmount >= 350) and
            (milkAmount >= 75) and
            (coffeeBeansAmount >= 20) and
            (disposableCupsAmount >= 1)) {
            enoughIngredients = true
        } else if ((choice == CAPPUCCINO) and
            (waterAmount >= 200) and
            (milkAmount >= 100) and
            (coffeeBeansAmount >= 12) and
            (disposableCupsAmount >= 1)) {
            enoughIngredients = true
        } else {
            enoughIngredients = false
        }
        return enoughIngredients
    }

    fun identifyLackingIngredient(choice: Int): String {
        var lackingIngredient = ""
        if (choice == ESPRESSO) {
            if (waterAmount < 250) {
                lackingIngredient = "water"
            } else if (coffeeBeansAmount < 16) {
                lackingIngredient = "coffee beans"
            }
        } else if (choice == LATTE) {
            if (waterAmount < 350) {
                lackingIngredient = "water"
            } else if (milkAmount < 75) {
                lackingIngredient = "milk"
            } else if (coffeeBeansAmount < 20) {
                lackingIngredient = "coffee beans"
            }
        } else if (choice == CAPPUCCINO) {
            if (waterAmount < 200) {
                lackingIngredient = "water"
            } else if (milkAmount < 100) {
                lackingIngredient = "milk"
            } else if (coffeeBeansAmount < 12) {
                lackingIngredient = "coffee beans"
            }
        } else if (disposableCupsAmount == 0) {
            lackingIngredient = "disposable cups"
        }
        return lackingIngredient
    }

    fun useIngredient(choice: Int) {
        if (choice == 1) {
            waterAmount -= 250
            coffeeBeansAmount -= 16
        } else if (choice == 2) {
            waterAmount -= 350
            milkAmount -= 75
            coffeeBeansAmount -= 20
        } else if (choice == 3) {
            waterAmount -= 200
            milkAmount -= 100
            coffeeBeansAmount -= 12
        }
        disposableCupsAmount -= 1
    }

    fun fillMachine() {
        println("Write how many ml of water you want to add:")
        waterAmount += readln().toInt()
        println("Write how many ml of milk you want to add:")
        milkAmount += readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        coffeeBeansAmount += readln().toInt()
        println("Write how many disposable cups you want to add:")
        disposableCupsAmount += readln().toInt()
    }

    fun currentState() {
        println("The coffee machine has:")
        println("$waterAmount ml of water")
        println("$milkAmount ml of milk")
        println("$coffeeBeansAmount g of coffee beans")
        println("$disposableCupsAmount disposable cups")
        println("$$moneyAmount of money")
    }

    fun askForAction() {
        println("Write action (buy, fill, take, remaining, exit):")
    }

    fun getUserInput(): String {
        val userInput = readln()
        return userInput
    }

    fun presentMenu() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    }
}


fun main() {

    val validMenuChoices = listOf(ESPRESSO, LATTE, CAPPUCCINO)
    val coffeeMachine = CoffeeMachine(0, 0, 0, 0, 0)
    var coffeeMachinePoweredOn = true
    var enoughIngredients: Boolean

    coffeeMachine.askForAction()

    while (coffeeMachinePoweredOn) {

        val userInput = coffeeMachine.getUserInput()

        if (userInput == "buy") { // Send user to the menu options.
            println()
            coffeeMachine.presentMenu()
            val menuChoice = coffeeMachine.getUserInput()
            if (menuChoice == "back") {
                println("Cancelling order...")
                println()
                coffeeMachine.askForAction()
            } else if (menuChoice.toInt() in validMenuChoices) {
                enoughIngredients = coffeeMachine.checkIngredients(menuChoice.toInt())
                if (enoughIngredients) {
                    coffeeMachine.useIngredient(menuChoice.toInt())
                    if (menuChoice.toInt() == ESPRESSO) {
                        coffeeMachine.moneyAmount += 4
                    } else if (menuChoice.toInt() == LATTE) {
                        coffeeMachine.moneyAmount += 7
                    } else if (menuChoice.toInt() == CAPPUCCINO) {
                        coffeeMachine.moneyAmount += 6
                    }
                    println("I have enough resources, making you a coffee!")
                    println()
                } else {
                    val lackingIngredient = coffeeMachine.identifyLackingIngredient(menuChoice.toInt())
                    println("Sorry, not enough $lackingIngredient!")
                    println()
                }
                coffeeMachine.askForAction()
            } else {
                println("Invalid choice")
                println()
                coffeeMachine.askForAction()
            }
        } else if (userInput == "fill") {
            coffeeMachine.fillMachine()
            println()
            coffeeMachine.askForAction()
        } else if (userInput == "take") {
            println()
            println("I gave you $${coffeeMachine.moneyAmount}")
            coffeeMachine.moneyAmount = 0
            println()
            coffeeMachine.askForAction()
        } else if (userInput == "remaining") {
            println()
            coffeeMachine.currentState()
            println()
            coffeeMachine.askForAction()
        } else if (userInput == "exit") {
            coffeeMachinePoweredOn = false
        } else {
            println("Invalid choice")
            println()
            coffeeMachine.askForAction()
        }
    }
}