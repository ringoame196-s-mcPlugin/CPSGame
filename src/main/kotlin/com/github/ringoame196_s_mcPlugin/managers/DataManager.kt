package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.BossBar
import com.github.ringoame196_s_mcPlugin.datas.Data
import com.github.ringoame196_s_mcPlugin.datas.GameData
import org.bukkit.entity.Player

object DataManager {
    fun registerTableFileName(fileName: String) {
        Data.tableFileName = fileName
    }

    fun setGameData(player: Player, time: Int) {
        val gameData = GameData(player, time, 0, BossBar(player))
        Data.gameData[player] = gameData
    }
    fun deleteGameData(player: Player) {
        Data.gameData.remove(player)
    }
    fun acquisitionGameData(player: Player): GameData? = Data.gameData[player]
    fun acquisitionClickCount(player: Player): Int = Data.gameData[player]?.clickCount ?: 0
    fun additionClickCount(player: Player) {
        var clickCount = acquisitionClickCount(player)
        clickCount ++
        Data.gameData[player]?.clickCount = clickCount
    }
    fun setTime(player: Player, time: Int) {
        Data.gameData[player]?.time = time
    }

    fun acquisitionBossBar(player: Player): BossBar? = Data.gameData[player]?.bossBar
}
