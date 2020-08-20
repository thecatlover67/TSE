
package net.mcreator.theshademod.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;

import net.mcreator.theshademod.block.BloodwaterBlock;
import net.mcreator.theshademod.TheShadeModModElements;

@TheShadeModModElements.ModElement.Tag
public class BloodoceanBiome extends TheShadeModModElements.ModElement {
	@ObjectHolder("the_shade_mod:bloodocean")
	public static final CustomBiome biome = null;
	public BloodoceanBiome(TheShadeModModElements instance) {
		super(instance, 114);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0.5f).depth(0.1f).scale(0.2f).temperature(0.5f).precipitation(Biome.RainType.RAIN)
					.category(Biome.Category.OCEAN).waterColor(-14351870).waterFogColor(-14351870).parent("the_shade_mod:fleshplains")
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BloodwaterBlock.block.getDefaultState(),
							BloodwaterBlock.block.getDefaultState(), BloodwaterBlock.block.getDefaultState())));
			setRegistryName("bloodocean");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addOres(this);
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getGrassColor(double posX, double posZ) {
			return -15663087;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getFoliageColor() {
			return -15663087;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColor() {
			return -13434880;
		}
	}
}
