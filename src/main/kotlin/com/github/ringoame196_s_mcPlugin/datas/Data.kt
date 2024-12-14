package com.github.ringoame196_s_mcPlugin.datas

import org.bukkit.ChatColor
import org.bukkit.entity.Player

object Data {
    // DB関係
    var tableFileName: String? = null
    const val TABLE_NAME = "cpsgame"
    const val ID_KEY = "id"
    const val SCORE_KEY = "score"
    const val BEST_SCORE_ID = "bestscore"

    // ターゲット
    val TARGET_NAME = "${ChatColor.GOLD}Cpsゲーム 叩いて${ChatColor.RED}ゲームスタート"
    const val TARGET_TAG = "cpsgame"
    const val UNIT_NAME = "クリック"

    // ゲームデータ
    val gameData: MutableMap<Player, GameData> = mutableMapOf()
    var limitTime: Int = 0
}
