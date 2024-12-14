package com.github.ringoame196_s_mcPlugin.commands

import com.github.ringoame196_s_mcPlugin.Data
import com.github.ringoame196_s_mcPlugin.managers.CpsGameDataManager
import com.github.ringoame196_s_mcPlugin.managers.CpsGameManager
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class Command : CommandExecutor {
    private val cpsGameManager = CpsGameManager()

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
    private fun summonCommand(sender: Player) {
        val targetBlock = sender.getTargetBlockExact(8)

        if (targetBlock != null) {
            val targetBlockLocation = targetBlock.location
            val summonLocation = targetBlockLocation.clone().add(0.5, 1.0, 0.5)
            cpsGameManager.summonCpsArmorStand(summonLocation)
        } else {
            val message = "${ChatColor.RED}目先のブロックを取得できませんでした"
            sender.sendMessage(message)
        }
    }
    private fun deleteCommand(sender: Player) {
        val entity = acquisitionEntityInPlayerSight(sender)
        if (entity != null && cpsGameManager.isCpsGameTarget(entity)) {
            val message = "${ChatColor.RED}削除しました"
            entity.remove()
            sender.sendMessage(message)
        } else {
            val message = "${ChatColor.RED}エンティティの取得に失敗しました"
            sender.sendMessage(message)
        }
    }

    private fun acquisitionEntityInPlayerSight(player: Player): Entity? {
        val maxDistance = 8.0
        val rayTraceResult = player.world.rayTraceEntities(
            player.eyeLocation,
            player.location.direction,
            maxDistance,
            { entity -> entity != player }
        )

        return rayTraceResult?.hitEntity
    }
}
