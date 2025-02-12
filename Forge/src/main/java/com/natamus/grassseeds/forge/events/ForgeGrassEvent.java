package com.natamus.grassseeds.forge.events;

import com.natamus.grassseeds.events.GrassEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeGrassEvent {
	@SubscribeEvent
	public static void onDirtClick(PlayerInteractEvent.RightClickBlock e) {
		GrassEvent.onDirtClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}
