package net.mcreator.theshademod.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.theshademod.TheShadeModModElements;

import java.util.Map;

@TheShadeModModElements.ModElement.Tag
public class CorruptedarmorHelmetTickEventProcedure extends TheShadeModModElements.ModElement {
	public CorruptedarmorHelmetTickEventProcedure(TheShadeModModElements instance) {
		super(instance, 58);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure CorruptedarmorHelmetTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.HASTE, (int) 10, (int) 0, (false), (false)));
	}
}
