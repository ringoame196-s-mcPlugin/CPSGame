package com.github.ringoame196_s_mcPlugin

import org.bukkit.entity.Player

data class GameData(
    val player: Player,
    var time: Int,
    var clickCount: Int,
    val bossBar: BossBar
)
