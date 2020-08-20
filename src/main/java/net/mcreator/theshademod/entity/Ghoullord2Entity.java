
package net.mcreator.theshademod.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.theshademod.item.ShadestaffItem;
import net.mcreator.theshademod.TheShadeModModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@TheShadeModModElements.ModElement.Tag
public class Ghoullord2Entity extends TheShadeModModElements.ModElement {
	public static EntityType entity = null;
	public Ghoullord2Entity(TheShadeModModElements instance) {
		super(instance, 118);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 4.8f)).build("ghoullord_2")
						.setRegistryName("ghoullord_2");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelbrute(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("the_shade_mod:textures/gghoulloorrdd2.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
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
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.17);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			ShadestaffItem.shoot(this, target);
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelbrute extends EntityModel<Entity> {
		private final ModelRenderer allstuff;
		private final ModelRenderer bone;
		private final ModelRenderer head;
		private final ModelRenderer armleft;
		private final ModelRenderer armright;
		private final ModelRenderer legleft;
		private final ModelRenderer legright;
		public Modelbrute() {
			textureWidth = 256;
			textureHeight = 256;
			allstuff = new ModelRenderer(this);
			allstuff.setRotationPoint(0.0F, 0.0F, 0.0F);
			setRotationAngle(allstuff, 0.0F, 3.1416F, 0.0F);
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 0.0F, 0.0F);
			allstuff.addChild(bone);
			bone.setTextureOffset(0, 53).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -24.0F, 0.0F);
			bone.addChild(head);
			head.setTextureOffset(0, 21).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
			head.setTextureOffset(0, 0).addBox(-9.5F, -2.0F, -9.5F, 19.0F, 2.0F, 19.0F, 0.0F, false);
			head.setTextureOffset(64, 21).addBox(-9.5F, -18.0F, -9.5F, 19.0F, 16.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(51, 51).addBox(-9.5F, -18.0F, -5.5F, 19.0F, 12.0F, 13.0F, 0.0F, false);
			head.setTextureOffset(57, 15).addBox(-9.5F, -8.0F, 7.5F, 19.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(8, 8).addBox(-9.5F, -13.0F, 7.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 7).addBox(7.5F, -13.0F, 7.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(57, 0).addBox(-9.5F, -18.0F, 7.5F, 19.0F, 5.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 0).addBox(-2.5F, -13.0F, 7.5F, 5.0F, 5.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 33).addBox(-9.5F, -4.0F, 7.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(8, 29).addBox(-5.5F, -4.0F, 7.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 14).addBox(-1.5F, -4.0F, 7.5F, 3.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 29).addBox(3.5F, -4.0F, 7.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(8, 25).addBox(7.5F, -4.0F, 7.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 25).addBox(5.5F, -6.0F, 7.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(8, 21).addBox(1.5F, -6.0F, 7.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 21).addBox(-3.5F, -6.0F, 7.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(10, 15).addBox(-7.5F, -6.0F, 7.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(57, 11).addBox(-9.5F, -6.0F, 5.5F, 19.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(57, 7).addBox(-9.5F, -4.0F, 3.5F, 19.0F, 2.0F, 2.0F, 0.0F, false);
			armleft = new ModelRenderer(this);
			armleft.setRotationPoint(-8.0F, -20.0F, 0.0F);
			bone.addChild(armleft);
			armleft.setTextureOffset(48, 76).addBox(-8.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);
			armright = new ModelRenderer(this);
			armright.setRotationPoint(8.0F, -20.0F, 0.0F);
			bone.addChild(armright);
			armright.setTextureOffset(80, 80).addBox(0.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);
			legleft = new ModelRenderer(this);
			legleft.setRotationPoint(-4.0F, 0.0F, 0.0F);
			bone.addChild(legleft);
			legleft.setTextureOffset(0, 85).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);
			legright = new ModelRenderer(this);
			legright.setRotationPoint(4.0F, 0.0F, 0.0F);
			bone.addChild(legright);
			legright.setTextureOffset(107, 107).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 24.0F, 5.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			allstuff.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.armright.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.legright.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.armleft.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.legleft.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
