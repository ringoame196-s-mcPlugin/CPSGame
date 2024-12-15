package com.github.ringoame196_s_mcPlugin.managers

import org.bukkit.Bukkit

class ScoreBoardManager {
    private val manager = Bukkit.getScoreboardManager()
    private val scoreBoard = manager?.mainScoreboard

    fun setValue(scoreBoardName: String, key: String, value: Int) {
        val objective = scoreBoard?.getObjective(scoreBoardName)
        if (objective == null) {
            Bukkit.getLogger().warning("スコアボード '$scoreBoardName' が見つかりません")
            return
        }
        objective.getScore(key).score = value
    }

    fun makeScoreBoard(scoreBoardName: String, type: String, displayName: String? = null) {
        if (scoreBoard?.getObjective(scoreBoardName) != null) return
        try {
            scoreBoard?.registerNewObjective(
                scoreBoardName,
                type,
                displayName ?: scoreBoardName
            )
        } catch (e: Exception) {
            Bukkit.broadcastMessage(e.message.toString())
        }
    }
}
