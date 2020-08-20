
package net.mcreator.theshademod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.theshademod.block.ShadewoodplanksBlock;
import net.mcreator.theshademod.TheShadeModModElements;

@TheShadeModModElements.ModElement.Tag
public class ShadewoodgreatswordItem extends TheShadeModModElements.ModElement {
	@ObjectHolder("the_shade_mod:shadewoodgreatsword")
	public static final Item block = null;
	public ShadewoodgreatswordItem(TheShadeModModElements instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 100;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 4f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(ShadewoodplanksBlock.block, (int) (1)));
			}
		}, 3, -2.1f, new Item.Properties().group(ItemGroup.COMBAT)) {
		}.setRegistryName("shadewoodgreatsword"));
	}
}
