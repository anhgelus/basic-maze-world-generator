package world.anhgelus.msmp.basicmazeworldgenerator

import org.bukkit.Bukkit
import org.bukkit.generator.ChunkGenerator
import world.anhgelus.msmp.basicmazeworldgenerator.api.WinningHandler
import world.anhgelus.msmp.basicmazeworldgenerator.events.MobListener
import world.anhgelus.msmp.basicmazeworldgenerator.events.PlayerListener
import world.anhgelus.msmp.basicmazeworldgenerator.events.SetupListener
import world.anhgelus.msmp.basicmazeworldgenerator.generator.MazeGenerator
import world.anhgelus.msmp.basicmazeworldgenerator.handlers.WinHandler
import world.anhgelus.msmp.basicmazeworldgenerator.utils.ConfigAPI
import world.anhgelus.msmp.msmpcore.PluginBase
import java.util.Collections

class BasicMazeWorldGenerator: PluginBase() {
    override val configHelper = ConfigAPI
    override val pluginName = "BasicMazeWorldGenerator"

    override fun disable() {

    }

    override fun enable() {
        INSTANCE = this
        LOGGER = logger

        events.add(MobListener)
        events.add(PlayerListener)
        events.add(SetupListener)
    }

    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator {
        return MazeGenerator()
    }

    private fun getWinHandler(): WinHandler {
        val id = ConfigAPI.getConfig("config").get().getInt("game.win-condition.id", 0)
        WinHandler.values().forEach {
            if (it.id == id) return it
        }
        throw IllegalArgumentException("The win handler with id $id doesn't exist!")
    }

    companion object: CompanionBase()
}