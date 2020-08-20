
package net.mcreator.theshademod.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.block.BlockState;

import net.mcreator.theshademod.TheShadeModModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@TheShadeModModElements.ModElement.Tag
public class ShadespiderEntity extends TheShadeModModElements.ModElement {
	public static EntityType entity = null;
	public ShadespiderEntity(TheShadeModModElements instance) {
		super(instance, 16);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 0.8f)).build("shadespider")
						.setRegistryName("shadespider");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -14013910, -65536, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("shadespider"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("the_shade_mod:shadeforest")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 10, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelcustom_model(), 0.7f) {
				{
					this.addLayer(new GlowingLayer<>(this));
				}
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("the_shade_mod:textures/shade_spider_ex.png");
				}
			};
		});
	}
	public static class CustomEntity extends SpiderEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 30;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, (float) 2));
			this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.8));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.SPIDER_EYE, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_shade_mod:spider_ambient"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound((net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.step")), 0.15f,
					1);
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("the_shade_mod:textures/spiderglow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelcustom_model extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer eye1;
		private final ModelRenderer eye3;
		private final ModelRenderer eye5;
		private final ModelRenderer eye2;
		private final ModelRenderer eye4;
		private final ModelRenderer leg1;
		private final ModelRenderer leg3;
		private final ModelRenderer leg5;
		private final ModelRenderer leg7;
		private final ModelRenderer leg2;
		private final ModelRenderer leg4;
		private final ModelRenderer leg6;
		private final ModelRenderer leg8;
		private final ModelRenderer head;
		private final ModelRenderer eyes;
		private final ModelRenderer pincer1;
		private final ModelRenderer pincer2;
		public Modelcustom_model() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(-3.0F, 17.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -6.0F, 7.0F, 6.0F, 16.0F, 0.0F, false);
			body.setTextureOffset(10, 4).addBox(4.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			eye1 = new ModelRenderer(this);
			eye1.setRotationPoint(2.5F, -0.5F, -1.5F);
			body.addChild(eye1);
			setRotationAngle(eye1, 0.0F, -0.4363F, 0.0F);
			eye1.setTextureOffset(0, 10).addBox(-0.0774F, -0.5F, 0.4063F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			eye3 = new ModelRenderer(this);
			eye3.setRotationPoint(5.5F, -0.5F, 4.5F);
			body.addChild(eye3);
			setRotationAngle(eye3, 0.0F, -0.7854F, 0.0F);
			eye3.setTextureOffset(6, 4).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			eye5 = new ModelRenderer(this);
			eye5.setRotationPoint(1.5F, -0.5F, 6.5F);
			body.addChild(eye5);
			setRotationAngle(eye5, 0.0F, -0.3491F, 0.0F);
			eye5.setTextureOffset(4, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			eye2 = new ModelRenderer(this);
			eye2.setRotationPoint(3.0F, -1.0F, 2.0F);
			body.addChild(eye2);
			setRotationAngle(eye2, 0.0F, -0.2618F, 0.0F);
			eye2.setTextureOffset(36, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			eye4 = new ModelRenderer(this);
			eye4.setRotationPoint(2.0F, -1.0F, -4.0F);
			body.addChild(eye4);
			setRotationAngle(eye4, 0.0F, -0.2618F, 0.0F);
			eye4.setTextureOffset(36, 28).addBox(-0.7412F, 0.0F, -0.0341F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			leg1 = new ModelRenderer(this);
			leg1.setRotationPoint(-3.0F, 21.0F, -4.0F);
			setRotationAngle(leg1, 0.0F, 0.0F, 0.6981F);
			leg1.setTextureOffset(0, 31).addBox(-6.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
			leg1.setTextureOffset(6, 35).addBox(-5.9925F, -0.727F, -1.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
			leg3 = new ModelRenderer(this);
			leg3.setRotationPoint(-3.0F, 21.0F, 0.0F);
			setRotationAngle(leg3, 0.0F, 0.0F, 0.6981F);
			leg3.setTextureOffset(30, 12).addBox(-6.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
			leg3.setTextureOffset(0, 35).addBox(-5.9925F, -0.727F, -1.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
			leg5 = new ModelRenderer(this);
			leg5.setRotationPoint(-3.0F, 21.0F, 4.0F);
			setRotationAngle(leg5, 0.0F, 0.0F, 0.6981F);
			leg5.setTextureOffset(30, 8).addBox(-6.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
			leg5.setTextureOffset(34, 34).addBox(-5.9925F, -0.727F, -1.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
			leg7 = new ModelRenderer(this);
			leg7.setRotationPoint(-3.0F, 21.0F, 8.0F);
			setRotationAngle(leg7, 0.0F, 0.0F, 0.6981F);
			leg7.setTextureOffset(30, 4).addBox(-6.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
			leg7.setTextureOffset(28, 34).addBox(-5.9925F, -0.727F, -1.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
			leg2 = new ModelRenderer(this);
			leg2.setRotationPoint(3.0F, 21.0F, -4.0F);
			setRotationAngle(leg2, 0.0F, 0.0F, -0.6981F);
			leg2.setTextureOffset(30, 0).addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
			leg2.setTextureOffset(22, 34).addBox(4.9925F, -0.727F, -1.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
			leg4 = new ModelRenderer(this);
			leg4.setRotationPoint(3.0F, 21.0F, 0.0F);
			setRotationAngle(leg4, 0.0F, 0.0F, -0.6981F);
			leg4.setTextureOffset(18, 30).addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
			leg4.setTextureOffset(16, 34).addBox(4.9925F, -0.727F, -1.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
			leg6 = new ModelRenderer(this);
			leg6.setRotationPoint(3.0F, 21.0F, 4.0F);
			setRotationAngle(leg6, 0.0F, 0.0F, -0.6981F);
			leg6.setTextureOffset(20, 26).addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
			leg6.setTextureOffset(6, 6).addBox(4.9925F, -0.727F, -1.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
			leg8 = new ModelRenderer(this);
			leg8.setRotationPoint(3.0F, 21.0F, 8.0F);
			setRotationAngle(leg8, 0.0F, 0.0F, -0.6981F);
			leg8.setTextureOffset(16, 22).addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
			leg8.setTextureOffset(0, 0).addBox(4.9925F, -0.727F, -1.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 20.0F, -6.0F);
			head.setTextureOffset(0, 22).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 5.0F, 4.0F, 0.0F, false);
			eyes = new ModelRenderer(this);
			eyes.setRotationPoint(-2.0F, -1.0F, -4.0F);
			head.addChild(eyes);
			setRotationAngle(eyes, 0.0F, 0.0F, 0.7854F);
			eyes.setTextureOffset(12, 12).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			eyes.setTextureOffset(0, 12).addBox(2.3284F, -3.3284F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			eyes.setTextureOffset(11, 0).addBox(0.9142F, -3.3284F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			eyes.setTextureOffset(10, 6).addBox(-0.5F, -1.9142F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			pincer1 = new ModelRenderer(this);
			pincer1.setRotationPoint(-1.5F, 0.5F, -4.5F);
			head.addChild(pincer1);
			setRotationAngle(pincer1, 0.0F, -0.6109F, 0.0F);
			pincer1.setTextureOffset(34, 22).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			pincer2 = new ModelRenderer(this);
			pincer2.setRotationPoint(1.5F, 0.5F, -4.5F);
			head.addChild(pincer2);
			setRotationAngle(pincer2, 0.0F, 0.6109F, 0.0F);
			pincer2.setTextureOffset(6, 0).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			leg1.render(matrixStack, buffer, packedLight, packedOverlay);
			leg3.render(matrixStack, buffer, packedLight, packedOverlay);
			leg5.render(matrixStack, buffer, packedLight, packedOverlay);
			leg7.render(matrixStack, buffer, packedLight, packedOverlay);
			leg2.render(matrixStack, buffer, packedLight, packedOverlay);
			leg4.render(matrixStack, buffer, packedLight, packedOverlay);
			leg6.render(matrixStack, buffer, packedLight, packedOverlay);
			leg8.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg5.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg8.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg6.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg7.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
