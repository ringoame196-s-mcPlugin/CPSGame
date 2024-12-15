package com.github.ringoame196_s_mcPlugin.events

import com.github.ringoame196_s_mcPlugin.managers.CpsGameManager
import org.bukkit.entity.Shulker
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.EquipmentSlot

class PlayerInteractEntityEvent : Listener {
    private val cpsGameManager = CpsGameManager()

    @EventHandler
    fun onPlayerInteractEntity(e: PlayerInteractEntityEvent) {
        val player = e.player
        val entity = e.rightClicked as? Shulker ?: return
        if (e.hand != EquipmentSlot.HAND) return
        if (!cpsGameManager.isCpsGameTarget(entity)) return
        e.isCancelled = true
        cpsGameManager.additionClick(player)
        cpsGameManager.changeShulkerColor(entity)
    }
}
