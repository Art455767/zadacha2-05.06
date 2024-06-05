fun main() {
    val cardType = "Мир"
    val previousTransfers = 0.0
    val transferAmount = 100_000.0

    try {
        val commission = calculateCommission(cardType, previousTransfers, transferAmount)
        println("Комиссия за перевод: $commission")
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}

fun calculateCommission(
    cardType: String = "Мир",
    previousTransfers: Double = 0.0,
    transferAmount: Double
): Double {
    val dailyLimit = 150_000.0
    val monthlyLimit = 600_000.0


    if (transferAmount > dailyLimit || previousTransfers + transferAmount > monthlyLimit) {
        throw IllegalArgumentException("Превышен лимит на сумму перевода")
    }

    return when (cardType) {
        "Mastercard" -> {
            if (previousTransfers + transferAmount <= 75_000) {
                0.0
            } else {
                0.006 * transferAmount + 20
            }
        }
        "Visa" -> {
            val commission = 0.0075 * transferAmount
            maxOf(commission, 35.0)
        }
        "Мир" -> 0.0
        else -> throw IllegalArgumentException("Неизвестный тип карты")
    }
}