package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data

object CpsGameDataManager {

    fun registerPlayerScore(playerUUID: String, score: Int) {
        val command = "INSERT INTO ${Data.TABLE_NAME} (${Data.ID_KEY}, ${Data.SCORE_KEY}) VALUES (?, ?) ON CONFLICT(${Data.ID_KEY}) DO UPDATE SET ${Data.SCORE_KEY} = excluded.${Data.SCORE_KEY};"
        DataBaseManager.runSQLCommand(command, mutableListOf(playerUUID, score))
    }

    fun acquisitionScore(searchId: String): Int {
        val command = "SELECT ${Data.SCORE_KEY} FROM ${Data.TABLE_NAME} WHERE ${Data.ID_KEY} = ?;"
        return DataBaseManager.acquisitionIntValue(command, mutableListOf(searchId), Data.SCORE_KEY) ?: 0
    }

    fun acquisitionBestScore(): Int {
        return acquisitionScore(Data.BEST_SCORE_ID)
    }

    fun updateBestScore(score: Int) {
        val command = "UPDATE ${Data.TABLE_NAME} SET ${Data.SCORE_KEY} = ? WHERE ${Data.ID_KEY} = '${Data.BEST_SCORE_ID}';"
        DataBaseManager.runSQLCommand(command, mutableListOf(score))
    }
}
