package machine
import java.util.Scanner


fun main() {
    val scanner = Scanner(System.`in`,)

    println("Write how many ml of water the coffee machine has:")
    var water = scanner.nextInt()
    println("Write how many ml of milk the coffee machine has:")
    var milk = scanner.nextInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    var coffeeBeans = scanner.nextInt()
    println("Write how many cups of coffee you will need:")
    val coffeeCupsNeeded = scanner.nextInt()

    var availableCups = 0

    var enoughIngredients = true

    while (enoughIngredients) {
        if ((water >= 200) and (milk >= 50) and (coffeeBeans >= 15)) {
            availableCups++
            water -= 200
            milk -= 50
            coffeeBeans -= 15
        } else {
            enoughIngredients = false
        }
    }

    if (availableCups == coffeeCupsNeeded) {
        println("Yes, I can make that amount of coffee")
    } else if (availableCups > coffeeCupsNeeded) {
        println("Yes, I can make that amount of coffee (and even ${availableCups - coffeeCupsNeeded} more than that)")
    } else {
        println("No, I can make only $availableCups cups of coffee")
    }
//    val water = numOfCoffeeCups * 200
//    val milk = numOfCoffeeCups * 50
//    val coffeeBeans = numOfCoffeeCups * 15

//    println("For $coffeeCupsNeeded cups of coffee you will need:")
//    println("$water ml of water\n$milk ml of milk\n$coffeeBeans g of coffee beans")
}

fun makeCoffee() {
    println("Starting to make a coffee")
    println("Grinding coffee beans")
    println("Boiling water")
    println("Mixing boiled water with crushed coffee beans")
    println("Pouring coffee into the cup")
    println("Pouring some milk into the cup")
    println("Coffee is ready!")
}
