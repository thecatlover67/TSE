package net.mcreator.theshademod.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.theshademod.TheShadeModModElements;

import java.util.Map;

@TheShadeModModElements.ModElement.Tag
public class CorruptedarmorBodyTickEventProcedure extends TheShadeModModElements.ModElement {
	public CorruptedarmorBodyTickEventProcedure(TheShadeModModElements instance) {
		super(instance, 59);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure CorruptedarmorBodyTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 10, (int) 1, (false), (false)));
	}
}
