package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.TabCompleter
import com.github.ringoame196_s_mcPlugin.datas.Data
import com.github.ringoame196_s_mcPlugin.events.EntityDamageByEntityEvent
import com.github.ringoame196_s_mcPlugin.events.PlayerInteractEntityEvent
import com.github.ringoame196_s_mcPlugin.managers.DataManager
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private var plugin = this

    override fun onEnable() {
        super.onEnable()

        // database関係
        saveResource("cpsgame_data.db", false)
        DataManager.registerTableFileName("${plugin.dataFolder.path}/cpsgame_data.db")

        // config
        saveDefaultConfig()
        Data.limitTime = config.getInt("limit_time")

        // マイクライベント
        server.pluginManager.registerEvents(PlayerInteractEntityEvent(), plugin)
        server.pluginManager.registerEvents(EntityDamageByEntityEvent(), plugin)
        val command = getCommand("cpsgame")
        command!!.setExecutor(Command())
        command.tabCompleter = TabCompleter()

        // ゲーム関係
        GameTimer.runTaskTimer(plugin, 0L, 20L)
    }
}
