package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.TabCompleter
import com.github.ringoame196_s_mcPlugin.events.Events
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private var plugin = this

    override fun onEnable() {
        super.onEnable()
        saveDefaultConfig()
        saveResource("cpsgame_data.db", false)
        server.pluginManager.registerEvents(Events(), plugin)
        val command = getCommand("cpsgame")
        command!!.setExecutor(Command())
        command.tabCompleter = TabCompleter()
    }
}
