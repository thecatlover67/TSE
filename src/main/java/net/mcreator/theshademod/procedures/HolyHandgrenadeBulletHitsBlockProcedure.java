package net.mcreator.theshademod.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;

import net.mcreator.theshademod.TheShadeModModElements;

import java.util.Map;

@TheShadeModModElements.ModElement.Tag
public class HolyHandgrenadeBulletHitsBlockProcedure extends TheShadeModModElements.ModElement {
	public HolyHandgrenadeBulletHitsBlockProcedure(TheShadeModModElements instance) {
		super(instance, 126);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure HolyHandgrenadeBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure HolyHandgrenadeBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure HolyHandgrenadeBulletHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure HolyHandgrenadeBulletHitsBlock!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !world.getWorld().isRemote) {
			world.getWorld().createExplosion(null, (int) x, (int) y, (int) z, (float) 2, Explosion.Mode.BREAK);
		}
	}
}
