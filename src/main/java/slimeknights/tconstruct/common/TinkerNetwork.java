package slimeknights.tconstruct.common;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import slimeknights.mantle.network.AbstractPacket;
import slimeknights.mantle.network.NetworkWrapper;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.smeltery.network.SmelteryFluidUpdatePacket;
import slimeknights.tconstruct.smeltery.network.SmelteryFuelUpdatePacket;
import slimeknights.tconstruct.tools.network.EntityMovementChangePacket;
import slimeknights.tconstruct.tools.network.InventoryCraftingSyncPacket;
import slimeknights.tconstruct.tools.network.InventorySlotSyncPacket;
import slimeknights.tconstruct.tools.network.PartCrafterSelectionPacket;
import slimeknights.tconstruct.tools.network.StencilTableSelectionPacket;
import slimeknights.tconstruct.tools.network.TinkerStationTabPacket;
import slimeknights.tconstruct.tools.network.ToolStationSelectionPacket;
import slimeknights.tconstruct.tools.network.ToolStationTextPacket;

public class TinkerNetwork extends NetworkWrapper {
  public static TinkerNetwork instance = new TinkerNetwork();

  public TinkerNetwork() {
    super(TConstruct.modID);
  }

  public void setup() {
    // register all the packets

    // TOOLS
    registerPacket(StencilTableSelectionPacket.class);
    registerPacket(PartCrafterSelectionPacket.class);
    registerPacket(ToolStationSelectionPacket.class);
    registerPacket(ToolStationTextPacket.class);
    registerPacketServer(TinkerStationTabPacket.class);
    registerPacketServer(InventoryCraftingSyncPacket.class);
    registerPacketClient(InventorySlotSyncPacket.class);
    registerPacketClient(EntityMovementChangePacket.class);

    // SMELTERY
    registerPacketClient(SmelteryFluidUpdatePacket.class);
    registerPacketClient(SmelteryFuelUpdatePacket.class);
  }

  public static void sendToAll(AbstractPacket packet)
  {
    instance.network.sendToAll(packet);
  }

  public static void sendTo(AbstractPacket packet, EntityPlayerMP player)
  {
    instance.network.sendTo(packet, player);
  }


  public static void sendToAllAround(AbstractPacket packet, NetworkRegistry.TargetPoint point)
  {
    instance.network.sendToAllAround(packet, point);
  }

  public static void sendToDimension(AbstractPacket packet, int dimensionId)
  {
    instance.network.sendToDimension(packet, dimensionId);
  }

  public static void sendToServer(AbstractPacket packet)
  {
    instance.network.sendToServer(packet);
  }
}
