package com.github.ringoame196_s_mcPlugin.commands

import com.github.ringoame196_s_mcPlugin.Data
import com.github.ringoame196_s_mcPlugin.managers.CpsGameDataManager
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

    private fun showCommand(sender: Player) {
        val playerUUID = sender.uniqueId.toString()
        val playerScore = CpsGameDataManager.acquisitionScore(playerUUID)
        val bestScore = CpsGameDataManager.acquisitionBestScore()

        val message = "${ChatColor.YELLOW}[鯖内ベストスコア] ${bestScore}${Data.UNIT_NAME}\n" +
            "${ChatColor.AQUA}[あなたのベストスコア] ${playerScore}${Data.UNIT_NAME}\n"
        sender.sendMessage(message)
    }
    private fun summonCommand(sender: Player) {}
    private fun deleteCommand(sender: Player) {}
}
