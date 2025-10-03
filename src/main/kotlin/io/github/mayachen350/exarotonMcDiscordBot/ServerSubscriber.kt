package io.github.mayachen350.exarotonMcDiscordBot

import com.exaroton.api.server.Server
import com.exaroton.api.ws.subscriber.ServerStatusSubscriber

object ServerSubscriber : ServerStatusSubscriber {
    override fun handleStatusUpdate(oldServer: Server?, newServer: Server?) {
        editPresence {
            defaultPresence()
        }
    }
}