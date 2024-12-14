package com.github.ringoame196_s_mcPlugin.events

import com.github.ringoame196_s_mcPlugin.managers.CpsGameManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class EntityDamageByEntityEvent : Listener {
    private val cpsGameManager = CpsGameManager()

    @EventHandler
    fun onEntityDamageByEntity(e: EntityDamageByEntityEvent) {
        val player = e.damager as? Player ?: return
        val entity = e.entity
        if (!cpsGameManager.isCpsGameTarget(entity)) return
        e.isCancelled = true
        cpsGameManager.additionClick(player)
    }
}
