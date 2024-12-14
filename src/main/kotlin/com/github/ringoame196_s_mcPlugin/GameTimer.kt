package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.managers.CpsGameManager
import com.github.ringoame196_s_mcPlugin.managers.DataManager
import org.bukkit.scheduler.BukkitRunnable

object GameTimer : BukkitRunnable() {
    private val cpsGameManager = CpsGameManager()

    override fun run() {
        val iterator = Data.gameData.entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            val player = entry.key
            val remaining = entry.value.time - 1
            if (remaining <= 0) {
                cpsGameManager.endGame(player)
            } else {
                DataManager.setTime(player, remaining)
                entry.value.bossBar.update(remaining)
            }
        }
    }
}
