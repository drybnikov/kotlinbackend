package dr.kotliners.kotlinbackend.model

import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList

data class Account(
    val id: Long,
    val userId: Int,
    val currency: Currency,
    var amount: BigDecimal,
    val transactions: ArrayList<Transaction>
)

data class Transaction(
    val id: Long,
    val accountId: Long,
    val value: BigDecimal,
    val type: TransactionType,
    val date: Long
) {
    companion object {
        fun transactionByType(accountId: Long, value: BigDecimal, type: TransactionType): Transaction {
            val transactionId = UUID.randomUUID().leastSignificantBits
            return Transaction(
                id = transactionId,
                accountId = accountId,
                date = System.currentTimeMillis(),
                type = type,
                value = value
            )
        }

        fun transferTransaction(accountId: Long, value: BigDecimal): Transaction {
            val transactionId = UUID.randomUUID().mostSignificantBits
            return Transaction(
                id = transactionId,
                accountId = accountId,
                date = System.currentTimeMillis(),
                type = TransactionType.TRANSFER,
                value = value
            )
        }
    }
}

enum class TransactionType {
    TRANSFER,
    WITHDRAWAL,
    DEPOSIT
}