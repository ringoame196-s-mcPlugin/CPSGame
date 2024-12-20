package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.datas.Data
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.entity.Shulker

class CpsGameManager {
    fun additionClick(player: Player) {
        val sound = Sound.BLOCK_DISPENSER_FAIL
        player.playSound(player, sound, 1f, 1f)

        if (DataManager.acquisitionGameData(player) == null) startGame(player)
        DataManager.additionClickCount(player)
    }

    private fun startGame(player: Player) {
        DataManager.setGameData(player, Data.limitTime)
    }

    fun endGame(player: Player) {
        DataManager.acquisitionBossBar(player)?.delete()
        val clickCount = DataManager.acquisitionClickCount(player)

        val message = "${ChatColor.GOLD}[結果] ${clickCount}${Data.UNIT_NAME}しました"
        val sound = Sound.ENTITY_GENERIC_EXPLODE
        val particle = Particle.EXPLOSION_LARGE
        player.sendMessage(message)
        player.playSound(player, sound, 1f, 1f)
        player.spawnParticle(particle, player.location, 10, 0.5, 0.5, 0.5, 0.1)

        DataManager.deleteGameData(player)
        saveScore(player, clickCount)
    }

    private fun saveScore(player: Player, score: Int) {
        val playerUUID = player.uniqueId.toString()
        val playerScore = CpsGameDataManager.acquisitionScore(playerUUID)
        val bestScore = CpsGameDataManager.acquisitionBestScore()

        if (Data.isWriteToScoreboard) writeScoreBoard(player, score)

        if (playerScore >= score) return // 自己ベスト更新していなければ保存しない
        sendSelfBest(player)
        CpsGameDataManager.registerPlayerScore(playerUUID, score)

        if (score > bestScore) { // 鯖内ベストを更新したとき
            sendServerBest(player, score)
            CpsGameDataManager.updateBestScore(score)
        }
    }

    private fun sendSelfBest(player: Player) {
        val sound = Sound.ENTITY_FIREWORK_ROCKET_TWINKLE
        val message = "${ChatColor.AQUA}自己ベスト更新！"
        player.playSound(player, sound, 1f, 1f)
        player.sendMessage(message)
    }

    private fun sendServerBest(player: Player, score: Int) {
        val message = "${ChatColor.YELLOW}[CpsGame] ${player.name}さんが 鯖内ベストスコアを${score}${Data.UNIT_NAME}で更新しました"
        Bukkit.broadcastMessage(message)
    }

    private fun writeScoreBoard(player: Player, score: Int) {
        val scoreBoardManager = ScoreBoardManager()
        val playerName = player.name
        scoreBoardManager.setValue(Data.scoreboardName, playerName, score)
    }

    fun summonCpsArmorStand(location: Location) {
        val world = location.world
        val target = world?.spawn(location, Shulker::class.java)
        target?.let {
            it.setAI(false)
            it.customName = Data.TARGET_NAME
            it.isCustomNameVisible = true
            it.scoreboardTags.add(Data.TARGET_TAG)
        }
    }

    fun changeShulkerColor(shulker: Shulker) {
        val currentColor = shulker.color
        val colorList = DyeColor.values()
        val currentIndex = colorList.indexOf(currentColor)
        val nextIndex = (currentIndex + 1) % colorList.size
        shulker.color = colorList[nextIndex]
    }

    fun isCpsGameTarget(entity: Entity): Boolean {
        return entity is Shulker && entity.scoreboardTags.contains(Data.TARGET_TAG)
    }
}
