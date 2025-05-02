package io.github.mayachen350.trickMcDiscordBot

import com.exaroton.api.ExarotonClient
import com.exaroton.api.server.Server
import dev.kord.common.entity.Permission
import dev.kord.common.entity.Permissions
import dev.kord.core.event.guild.MemberJoinEvent
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import io.github.cdimascio.dotenv.Dotenv
import me.jakejmattson.discordkt.dsl.bot
import me.jakejmattson.discordkt.util.intentsOf

val client: ExarotonClient by lazy { ExarotonClient(Dotenv.load().get("EXAROTRON_KEY")) }

val mcServer: Server by lazy {
    client.getServer(Dotenv.load().get("EXAROTRON_SERVER_ID")).apply {
        fetch().join()
    }
}

@OptIn(PrivilegedIntent::class)
fun main() {
    val token = Dotenv.load().get("BOT_TOKEN")
    bot(token) {
        configure {
            //Remove a command invocation message after the command is executed.
            deleteInvocation = false

            //An emoji added when a command is invoked (use 'null' to disable this).
            commandReaction = null

            dualRegistry = false

            //Configure the Discord Gateway intents for your bot.
            intents.apply {
                plus(Intent.DirectMessages)
                plus(Intent.GuildMembers)
                plus(intentsOf<MemberJoinEvent>())
            }

            defaultPermissions = Permissions {
                Permission.SendMessages
                Permission.ReadMessageHistory
                Permission.AddReactions
            }
        }

        onStart {
            println("Bot started!")
        }
    }
}