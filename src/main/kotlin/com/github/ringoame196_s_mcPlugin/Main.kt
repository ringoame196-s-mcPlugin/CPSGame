package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.TabCompleter
import com.github.ringoame196_s_mcPlugin.events.Events
import com.github.ringoame196_s_mcPlugin.managers.DataManager
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private var plugin = this

    override fun onEnable() {
        super.onEnable()
        saveDefaultConfig()

        // database関係
        saveResource("cpsgame_data.db", false)
        DataManager.registerTableFileName("${plugin.dataFolder.path}/cpsgame_data.db")

        // マイクライベント コマンド関係
        server.pluginManager.registerEvents(Events(), plugin)
        val command = getCommand("cpsgame")
        command!!.setExecutor(Command())
        command.tabCompleter = TabCompleter()
    }
}
