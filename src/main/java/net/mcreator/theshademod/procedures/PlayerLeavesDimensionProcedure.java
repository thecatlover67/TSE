package net.mcreator.theshademod.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.theshademod.TheShadeModModElements;

import java.util.Map;

@TheShadeModModElements.ModElement.Tag
public class PlayerLeavesDimensionProcedure extends TheShadeModModElements.ModElement {
	public PlayerLeavesDimensionProcedure(TheShadeModModElements instance) {
		super(instance, 44);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PlayerLeavesDimension!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).clearActivePotions();
	}
}
