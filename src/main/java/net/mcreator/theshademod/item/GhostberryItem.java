
package net.mcreator.theshademod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.theshademod.TheShadeModModElements;

@TheShadeModModElements.ModElement.Tag
public class GhostberryItem extends TheShadeModModElements.ModElement {
	@ObjectHolder("the_shade_mod:ghostberry")
	public static final Item block = null;
	public GhostberryItem(TheShadeModModElements instance) {
		super(instance, 105);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64).food((new Food.Builder()).hunger(5).saturation(5f).build()));
			setRegistryName("ghostberry");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 20;
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
