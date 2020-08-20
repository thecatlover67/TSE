
package net.mcreator.theshademod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;

import net.mcreator.theshademod.block.ShadewoodplanksBlock;
import net.mcreator.theshademod.TheShadeModModElements;

@TheShadeModModElements.ModElement.Tag
public class ShadesickleItem extends TheShadeModModElements.ModElement {
	@ObjectHolder("the_shade_mod:shadesickle")
	public static final Item block = null;
	public ShadesickleItem(TheShadeModModElements instance) {
		super(instance, 33);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 2069;
			}

			public float getEfficiency() {
				return 10f;
			}

			public float getAttackDamage() {
				return 8f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 22;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(ShadewoodplanksBlock.block, (int) (1)));
			}
		}, -1f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("shadesickle"));
	}
}
