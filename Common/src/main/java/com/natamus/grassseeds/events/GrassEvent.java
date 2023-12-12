package com.natamus.grassseeds.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;

public class GrassEvent {
	public static boolean onDirtClick(Level world, Player player, InteractionHand hand, BlockPos cpos, BlockHitResult hitVec) {
		if (world.isClientSide) {
			return true;
		}
		
		ItemStack handstack = player.getItemInHand(hand);
		if (!handstack.getItem().equals(Items.WHEAT_SEEDS)) {
			return true;
		}

		Block block = world.getBlockState(cpos).getBlock();
		if (block.equals(Blocks.DIRT)) {
			world.setBlockAndUpdate(cpos, Blocks.GRASS_BLOCK.defaultBlockState());
		}
		else if (block.equals(Blocks.GRASS_BLOCK)) {
			BlockPos up = cpos.above();
			if (world.getBlockState(up).getBlock().equals(Blocks.AIR)) {
				world.setBlockAndUpdate(up, Blocks.GRASS.defaultBlockState());
			}
			else if (world.getBlockState(up).getBlock().equals(Blocks.GRASS)) {
				upgradeGrass(world, up);
			}
			else {
				return true;
			}
		}
		else if (block.equals(Blocks.GRASS)) {
			upgradeGrass(world, cpos);
		}
		else {
			return true;
		}
		
		if (!player.isCreative()) {
			handstack.shrink(1);
		}

		return true;
	}
	
	private static void upgradeGrass(Level world, BlockPos pos) {
		  DoublePlantBlock blockdoubleplant = (DoublePlantBlock)Blocks.TALL_GRASS;
		  BlockState doubleplantstate = blockdoubleplant.defaultBlockState();
		  if (doubleplantstate.canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
			 world.setBlock(pos, blockdoubleplant.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 2);
			 world.setBlock(pos.above(), blockdoubleplant.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 2);
		  }
	}
}
