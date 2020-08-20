
package net.mcreator.theshademod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.util.ITooltipFlag;

import net.mcreator.theshademod.procedures.CorruptedfleshFoodEatenProcedure;
import net.mcreator.theshademod.TheShadeModModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@TheShadeModModElements.ModElement.Tag
public class CorruptedfleshItem extends TheShadeModModElements.ModElement {
	@ObjectHolder("the_shade_mod:corruptedflesh")
	public static final Item block = null;
	public CorruptedfleshItem(TheShadeModModElements instance) {
		super(instance, 54);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).food((new Food.Builder()).hunger(-10).saturation(5f).build()));
			setRegistryName("corruptedflesh");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("you shouldn't eat this...."));
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				CorruptedfleshFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
