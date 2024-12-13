package com.github.ringoame196_s_mcPlugin.commands

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Command : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false

        val subCommand = args[0]

        if (sender !is Player) {
            val message = "${ChatColor.RED}このコマンドはプレイヤーのみ実行可能です"
            sender.sendMessage(message)
            return true
        }

        when (subCommand) {
            CommandConst.SHOW_COMMAND -> showCommand(sender)
            CommandConst.SUMMON_COMMAND -> summonCommand(sender)
            CommandConst.DELETE_COMMAND -> deleteCommand(sender)
        }

        return true
    }

    private fun showCommand(sender: Player) {}
    private fun summonCommand(sender: Player) {}
    private fun deleteCommand(sender: Player) {}
}
