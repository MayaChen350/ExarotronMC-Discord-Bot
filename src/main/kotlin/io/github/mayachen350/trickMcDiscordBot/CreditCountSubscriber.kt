package io.github.mayachen350.trickMcDiscordBot

import dev.kord.core.Kord
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jakejmattson.discordkt.util.sendPrivateMessage
import me.jakejmattson.discordkt.util.toSnowflake
import kotlin.time.Duration.Companion.minutes

object CreditCountSubscriber {
    private const val LOW_CREDIT_COUNT = 10

    fun pollPeriodicallyForCreditCount(ctx: Kord) {
        ctx.launch {
            while (true) {
                val creditCount =
                    client.getCreditPool(Dotenv.load().get("EXAROTRON_CREDIT_POOL_ID")).fetch().join().credits

                if (creditCount < LOW_CREDIT_COUNT) {
                    println("Alert! Credits are low!")
                    creditLowAlert(ctx, creditCount)
                }
                delay(30.minutes.inWholeMilliseconds)
            }
        }
    }

    private suspend fun creditLowAlert(ctx: Kord, creditCount: Double) {
        ctx.getUser(Dotenv.load().get("TRICKS_ID").toSnowflake())?.sendPrivateMessage(
            "## :alarm_clock: REMINDER:\n" +
                    "Your credits have gone under $LOW_CREDIT_COUNT!\n" +
                    "Current credit count: **$creditCount**"
        )
    }
}