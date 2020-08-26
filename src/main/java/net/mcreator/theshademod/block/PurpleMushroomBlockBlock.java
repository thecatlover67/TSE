
package net.mcreator.theshademod.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.theshademod.TheShadeModModElements;

import java.util.List;
import java.util.Collections;

@TheShadeModModElements.ModElement.Tag
public class PurpleMushroomBlockBlock extends TheShadeModModElements.ModElement {
	@ObjectHolder("the_shade_mod:purple_mushroom_block")
	public static final Block block = null;
	public PurpleMushroomBlockBlock(TheShadeModModElements instance) {
		super(instance, 170);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.PLANT).hardnessAndResistance(2f, 10f).lightValue(0));
			setRegistryName("purple_mushroom_block");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
