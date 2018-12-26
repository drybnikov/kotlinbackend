package dr.kotliners.kotlinbackend.model

import java.util.*
import kotlin.collections.HashSet

data class Account(
    val id: Long,
    val userId: Int,
    val currency: Currency,
    var amount: Double,
    val transactions: HashSet<Transaction>
)

data class Transaction(
    val id: Long,
    val accountId: Long,
    val value: Double,
    val type: TransactionType,
    val date: Long
) {
    companion object {
        fun depositTransaction(accountId: Long, deposit: Double): Transaction {
            val transactionId = UUID.randomUUID().leastSignificantBits
            return Transaction(
                id = transactionId,
                accountId = accountId,
                date = System.currentTimeMillis(),
                type = TransactionType.DEPOSIT,
                value = deposit
            )
        }

        fun transferTransaction(accountId: Long, value: Double): Transaction {
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