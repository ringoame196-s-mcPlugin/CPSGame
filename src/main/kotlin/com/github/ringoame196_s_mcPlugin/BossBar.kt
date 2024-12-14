package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.datas.Data
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player

class BossBar(private val player: Player) {
    private val bossBar: BossBar

    init {
        val title = "${ChatColor.AQUA}制限時間${Data.limitTime}秒"
        val color = BarColor.RED
        val style = BarStyle.SEGMENTED_10
        bossBar = Bukkit.createBossBar(title, color, style)
        bossBar.addPlayer(player)
    }

    fun update(time: Int) {
        val title = "${ChatColor.AQUA}制限時間:${time}秒"
        bossBar.setTitle(title)
    }

    fun delete() {
        bossBar.removeAll()
    }
}
