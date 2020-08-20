
package net.mcreator.theshademod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.theshademod.TheShadeModModElements;

@TheShadeModModElements.ModElement.Tag
public class DuskanthonypavlicItem extends TheShadeModModElements.ModElement {
	@ObjectHolder("the_shade_mod:duskanthonypavlic")
	public static final Item block = null;
	public DuskanthonypavlicItem(TheShadeModModElements instance) {
		super(instance, 71);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, TheShadeModModElements.sounds.get(new ResourceLocation("the_shade_mod:dusk")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("duskanthonypavlic");
		}
	}
}
