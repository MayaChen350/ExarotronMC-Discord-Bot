package io.github.mayachen350.exarotonMcDiscordBot

import me.jakejmattson.discordkt.commands.commands

fun mcServerCommands() = commands("Minecraft Server") {
    slash("server_address", "Get the current server address of the minecraft server!") {
        execute {
            respondPublic("```${mcServer.address}```")
        }
    }

    slash("server_version", "Get the current server version of the minecraft server!") {
        execute {
            respondPublic("The server is on **Minecraft Java Edition ${mcServer.software.version.substringBefore(" ")}**")
        }
    }

    slash("online_status", "Check if the server is currently online!") {
        execute {
            respondPublic("The minecraft server is currently: `${mcServer.status}`")
        }
    }

    slash("player_count", "Get the current number of players playing in the server right now!") {
        execute {
            val playerCount = mcServer.playerInfo.count
            respondPublic("There are currently `$playerCount` ${if (playerCount <= 1) "player" else "players"} online!")
        }
    }
}

fun basicCommands() = commands("Miscellaneous") {
    slash("ping", "Check the ping response of the bot to discord or something.") {
        execute {
            respondPublic("Ping! ${interaction?.kord?.gateway?.averagePing}ms")
        }
    }
}