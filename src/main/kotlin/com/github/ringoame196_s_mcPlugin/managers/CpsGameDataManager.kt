package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data

object CpsGameDataManager {

    fun registerPlayerScore(playerUUID: String, registerScore: Int) {
        val score = acquisitionScore(playerUUID)
        if (score >= registerScore) return // scoreが更新したときのみ値を保存する
        val command = "INSERT INTO ${Data.TABLE_NAME} (${Data.ID_KEY}, ${Data.SCORE_KEY}) VALUES (?, ?) ON CONFLICT(${Data.ID_KEY}) DO UPDATE SET ${Data.SCORE_KEY} = excluded.${Data.SCORE_KEY};"
        DataBaseManager.runSQLCommand(Data.tableFileName ?: return, command, mutableListOf(playerUUID, score))
    }

    fun acquisitionScore(searchId: String): Int {
        val command = "SELECT ${Data.SCORE_KEY} FROM ${Data.TABLE_NAME} WHERE ${Data.ID_KEY} = ?;"
        return DataBaseManager.acquisitionStringInt(Data.tableFileName ?: return 0, command, mutableListOf(searchId), Data.ID_KEY) ?: 0
    }

    fun acquisitionBestScore(): Int {
        return acquisitionScore(Data.BEST_SCORE_ID)
    }
}
