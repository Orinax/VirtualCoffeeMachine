package machine
import java.util.Scanner

fun getUserInput(scanner: Scanner): String {
    val userInput = scanner.nextLine()
    return userInput
}

/** Returns the value of how much of an ingredient should be added. */
fun getIntegerInput(scanner: Scanner): Int {
    val integerInput = scanner.nextInt()
    return integerInput
}

//fun makeCoffee(choice: Int,
//               waterAmount: Int,
//               milkAmount: Int,
//               coffeeBeansAmount: Int,
//               disposableCupsAmount: Int) {

fun checkIngredients(choice: Int,
                     waterAmount: Int,
                     milkAmount: Int,
                     coffeeBeansAmount: Int,
                     disposableCupsAmount: Int): Boolean {

    var enoughIngredients: Boolean

    // choice 1 == espresso, choice 2 == latte, choice 3 == cappuccino
    if ((choice == 1) and
        (waterAmount >= 250) and
        (milkAmount >= 0) and
        (coffeeBeansAmount >= 16) and
        (disposableCupsAmount >= 1)) {
        enoughIngredients = true
    } else if ((choice == 2) and
            (waterAmount >= 350) and
            (milkAmount >= 75) and
            (coffeeBeansAmount >= 20) and
            (disposableCupsAmount >= 1)) {
        enoughIngredients = true
    } else if ((choice == 3) and
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

//fun makeCoffee() {
//    println("Starting to make a coffee")
//    println("Grinding coffee beans")
//    println("Boiling water")
//    println("Mixing boiled water with crushed coffee beans")
//    println("Pouring coffee into the cup")
//    println("Pouring some milk into the cup")
//    println("Coffee is ready!")
//}

fun identifyLackingIngredient(choice: Int,
                     waterAmount: Int,
                     milkAmount: Int,
                     coffeeBeansAmount: Int,
                     disposableCupsAmount: Int): String {

    var lackingIngredient = ""

    // choice 1 == espresso, choice 2 == latte, choice 3 == cappuccino
    if (choice == 1) {
        if (waterAmount < 250) {
            lackingIngredient = "water"
        } else if (coffeeBeansAmount < 16) {
            lackingIngredient = "coffee beans"
        }
    } else if (choice == 2) {
        if (waterAmount < 350) {
            lackingIngredient = "water"
        } else if (milkAmount < 75) {
            lackingIngredient = "milk"
        } else if (coffeeBeansAmount < 20) {
            lackingIngredient = "coffee beans"
        }
    } else if (choice == 3) {
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

fun useIngredient(choice: Int, ingredient: String): Int {
    var amountToUse = 0
    if ((choice == 1) and (ingredient == "water")) {
        amountToUse = 250
    } else if ((choice == 2) and (ingredient == "water")) {
        amountToUse = 350
    } else if ((choice == 3) and (ingredient == "water")) {
        amountToUse = 200
    } else if ((choice == 2) and (ingredient == "milk")) {
        amountToUse = 75
    } else if ((choice == 3) and (ingredient == "milk")) {
        amountToUse = 100
    } else if ((choice == 1) and (ingredient == "coffee beans")) {
        amountToUse = 16
    } else if ((choice == 2) and (ingredient == "coffee beans")) {
        amountToUse = 20
    } else if ((choice == 3) and (ingredient == "coffee beans")) {
        amountToUse = 12
    } else if (ingredient == "disposable cups") {
        amountToUse = 1
    }
    return amountToUse
}
fun buyDrink(scanner: Scanner): Int {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    val userChoice = getUserInput(scanner)
    val intUserChoice: Int

    if ((userChoice == "1") or (userChoice == "2") or (userChoice == "3")) {  // Order a drink.
        intUserChoice = userChoice.toInt()
        return intUserChoice
    } else if (userChoice == "back") {  // Cancel the order.
        intUserChoice = 4
        return intUserChoice
    } else {
        intUserChoice = 5  // This does nothing but create an integer that can be returned for all other inputs.
        return intUserChoice
    }
}

fun fillMachine(ingredient: String, scanner: Scanner): Int {
    if ((ingredient == "water") or (ingredient == "milk")) {
        println("Write how many ml of $ingredient you want to add:")
    } else if (ingredient == "coffee beans") {
        println("Write how many grams of $ingredient you want to add:")
    } else if (ingredient == "disposable cups") {
        println("Write how many $ingredient you want to add:")
    }

    val ingredientAmount = getIntegerInput(scanner)
    return ingredientAmount
}

fun currentState(water: Int, milk: Int, coffeeBeans: Int, cups: Int, money: Int) {
    println("The coffee machine has:")
    println("$water ml of water")
    println("$milk ml of milk")
    println("$coffeeBeans g of coffee beans")
    println("$cups disposable cups")
    println("$$money of money")
}

fun askForAction() {
    println("Write action (buy, fill, take, remaining, exit):") // Removed "exit" option for now.
}

fun main() {
    var scanner = Scanner(System.`in`,)

    var waterAmount = 400
    var milkAmount = 540
    var coffeeBeansAmount = 120
    var disposableCupsAmount = 9
    var moneyAmount = 550
    var enoughIngredients: Boolean
    var coffeeMachinePoweredOn = true

    askForAction()

    while (coffeeMachinePoweredOn) {

        val userInput = getUserInput(scanner)

        // This mess needs to be cleaned up. Problems arise because Kotlin is pass-by-value...
        // It seems a class based approach would work better. Those changes will be made next time.
        if (userInput == "buy") {
            println()
            val customerChoice = buyDrink(scanner)
            if (customerChoice == 1) {  // This is for espresso orders.
                enoughIngredients = checkIngredients(customerChoice,
                    waterAmount, milkAmount, coffeeBeansAmount, disposableCupsAmount)
                if (enoughIngredients) {
                    waterAmount -= useIngredient(customerChoice, "water")
                    coffeeBeansAmount -= useIngredient(customerChoice, "coffee beans")
                    disposableCupsAmount -= useIngredient(customerChoice, "disposable cups")
                    moneyAmount += 4
                    println("I have enough resources, making you a coffee!")
                    println()
                } else {
                    val lackingIngredient = identifyLackingIngredient(customerChoice,
                        waterAmount, milkAmount, coffeeBeansAmount, disposableCupsAmount)
                    println("Sorry, not enough $lackingIngredient!")
                    println()
                }
                askForAction()
            } else if (customerChoice == 2) {  // This is for latte orders.
                enoughIngredients = checkIngredients(customerChoice,
                    waterAmount, milkAmount, coffeeBeansAmount, disposableCupsAmount)
                if (enoughIngredients) {
                    waterAmount -= useIngredient(customerChoice, "water")
                    milkAmount -= useIngredient(customerChoice, "milk")
                    coffeeBeansAmount -= useIngredient(customerChoice, "coffee beans")
                    disposableCupsAmount -= useIngredient(customerChoice, "disposable cups")
                    moneyAmount += 7
                    println("I have enough resources, making you a coffee!")
                    println()
                } else {
                    val lackingIngredient = identifyLackingIngredient(customerChoice,
                        waterAmount, milkAmount, coffeeBeansAmount, disposableCupsAmount)
                    println("Sorry, not enough $lackingIngredient!")
                    println()
                }
                askForAction()
            } else if (customerChoice == 3) {  // This is for cappuccino orders.
                enoughIngredients = checkIngredients(customerChoice,
                    waterAmount, milkAmount, coffeeBeansAmount, disposableCupsAmount)
                if (enoughIngredients) {
                    waterAmount -= useIngredient(customerChoice, "water")
                    milkAmount -= useIngredient(customerChoice, "milk")
                    coffeeBeansAmount -= useIngredient(customerChoice, "coffee beans")
                    disposableCupsAmount -= useIngredient(customerChoice, "disposable cups")
                    moneyAmount += 6
                    println("I have enough resources, making you a coffee!")
                    println()
                } else {
                    val lackingIngredient = identifyLackingIngredient(customerChoice,
                        waterAmount, milkAmount, coffeeBeansAmount, disposableCupsAmount)
                    println("Sorry, not enough $lackingIngredient!")
                    println()
                }
                askForAction()
            } else if (customerChoice == 4) {  // Send users back to the main menu.
                println("Cancelling order...")
                println()
                askForAction()
            } else {
                println("Invalid choice")
                println()
                askForAction()
            }
        } else if (userInput == "fill") {
            waterAmount += fillMachine("water", scanner)
            milkAmount += fillMachine("milk", scanner)
            coffeeBeansAmount += fillMachine("coffee beans", scanner)
            disposableCupsAmount += fillMachine("disposable cups", scanner)
            println()
            askForAction()
        } else if (userInput == "take") {
            println()
            println("I gave you $$moneyAmount")
            moneyAmount = 0
            println()
            askForAction()
        } else if (userInput == "remaining") {
            println()
            currentState(waterAmount, milkAmount, coffeeBeansAmount, disposableCupsAmount, moneyAmount)
            println()
            askForAction()
        } else if (userInput == "exit") {
            coffeeMachinePoweredOn = false
        } else {
            println("Invalid choice")
            println()
            askForAction()
        }
    }
}

//    if (availableCups == coffeeCupsNeeded) {
//        println("Yes, I can make that amount of coffee")
//    } else if (availableCups > coffeeCupsNeeded) {
//        println("Yes, I can make that amount of coffee (and even ${availableCups - coffeeCupsNeeded} more than that)")
//    } else {
//        println("No, I can make only $availableCups cups of coffee")
//    }
