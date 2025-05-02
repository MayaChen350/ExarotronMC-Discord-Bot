package io.github.mayachen350.trickMcDiscordBot

import me.jakejmattson.discordkt.commands.commands

fun basicCommands() = commands("Basics") {
    slash("Ping", "Check the ping response of the bot to discord or something.") {
        execute {
            respondPublic("Ping! ${interaction?.kord?.gateway?.averagePing}ms")
        }
    }
}