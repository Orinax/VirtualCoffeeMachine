package machine
import java.util.Scanner

fun getUserInput(scanner: Scanner): String {
    val userInput = scanner.nextLine()
    // Solution to filling the machine lies here.
    // Call this function each time to update the variables of the machine.
    return userInput
}

//fun buyCoffee() {
//
//    var enoughIngredients = true
//
//    while (enoughIngredients) {
//        if ((water >= 200) and (milk >= 50) and (coffeeBeans >= 15)) {
//            availableCups++
//            water -= 200
//            milk -= 50
//            coffeeBeans -= 15
//        } else {
//            enoughIngredients = false
//        }
//    }
//
//    if (availableCups == coffeeCupsNeeded) {
//        println("Yes, I can make that amount of coffee")
//    } else if (availableCups > coffeeCupsNeeded) {
//        println("Yes, I can make that amount of coffee (and even ${availableCups - coffeeCupsNeeded} more than that)")
//    } else {
//        println("No, I can make only $availableCups cups of coffee")
//    }
//}


fun makeCoffee() {
    println("Starting to make a coffee")
    println("Grinding coffee beans")
    println("Boiling water")
    println("Mixing boiled water with crushed coffee beans")
    println("Pouring coffee into the cup")
    println("Pouring some milk into the cup")
    println("Coffee is ready!")
}

fun fillMachine(scanner: Scanner, water: Int, milk: Int, coffeeBeans: Int, disposableCups: Int) {
    println("Write how many ml of water you want to add:")
    water += scanner.nextInt()
    println("Write how many ml of milk you want to add:")
    milk = scanner.nextInt()
    println("Write how many grams of coffee beans you want to add:")
    coffeeBeans = scanner.nextInt()
    println("Write how many disposable cups you want to add:")
    disposableCups = scanner.nextInt()

    // var availableCups = 0
}
fun currentState(water: Int, milk: Int, coffeeBeans: Int, cups: Int, money: Int) {
    println("The coffee machine has:")
    println("$water ml of water")
    println("$milk ml of milk")
    println("$coffeeBeans g of coffee beans")
    println("$cups disposable cups")
    println("$$money of money")
}

fun main() {
    val scanner = Scanner(System.`in`,)

    var water = 0
    var milk = 0
    var coffeeBeans = 0
    var disposableCups = 0
    var money = 0

    println("Write action (buy, fill, take):")
    var userInput = getUserInput(scanner)

    if (userInput == "fill") {
        fillMachine(scanner, water, milk, coffeeBeans, disposableCups)
        currentState(water, milk, coffeeBeans, disposableCups, money)
    }


//    val water = numOfCoffeeCups * 200
//    val milk = numOfCoffeeCups * 50
//    val coffeeBeans = numOfCoffeeCups * 15

//    println("For $coffeeCupsNeeded cups of coffee you will need:")
//    println("$water ml of water\n$milk ml of milk\n$coffeeBeans g of coffee beans")
}


