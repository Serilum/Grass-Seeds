package com.natamus.grassseeds.forge.events;

import com.natamus.grassseeds.events.GrassEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeGrassEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeGrassEvent.class);

		PlayerInteractEvent.RightClickBlock.BUS.addListener(ForgeGrassEvent::onDirtClick);
	}

	@SubscribeEvent
	public static void onDirtClick(PlayerInteractEvent.RightClickBlock e) {
		GrassEvent.onDirtClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}
