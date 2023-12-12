package com.natamus.grassseeds.neoforge.events;

import com.natamus.grassseeds.events.GrassEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeGrassEvent {
	@SubscribeEvent
	public static void onDirtClick(PlayerInteractEvent.RightClickBlock e) {
		GrassEvent.onDirtClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}
