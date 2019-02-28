package net.zhuoweizhang;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.Network;
import cn.nukkit.network.protocol.SetTimePacket;
import cn.nukkit.network.protocol.TextPacket;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class BLScriptEnable extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("blscriptenable")) {
            TextPacket pk = new TextPacket();
            pk.type = TextPacket.TYPE_CHAT;
            pk.source = "";
            pk.message = TextFormat.BLACK + "BlockLauncher, enable scripts";
            pk.setChannel(Network.CHANNEL_TEXT);
            player.dataPacket(pk);
        }
    }

    @EventHandler
    public void onDataPacketReceive(DataPacketReceiveEvent event) {
        Player player = event.getPlayer();
        if (event.getPacket() instanceof SetTimePacket && player != null) {
            player.sendChat("BlockLauncher, enable scripts, please and thank you");
        }
    }
}
