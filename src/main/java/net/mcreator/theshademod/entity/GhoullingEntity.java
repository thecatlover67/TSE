
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.theshademod.item.CorruptedfleshItem;
import net.mcreator.theshademod.TheShadeModModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@TheShadeModModElements.ModElement.Tag
public class GhoullingEntity extends TheShadeModModElements.ModElement {
	public static EntityType entity = null;
	public GhoullingEntity(TheShadeModModElements instance) {
		super(instance, 96);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 0.7999999999999999f))
						.build("ghoulling").setRegistryName("ghoulling");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10092544, -1, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("ghoulling"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("the_shade_mod:fleshplains")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 15, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelghoulling(), 0.3f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("the_shade_mod:textures/ghoulling_ex.png");
				}
			};
		});
	}
	public static class CustomEntity extends TameableEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 20;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new FollowOwnerGoal(this, 1, (float) 10, (float) 2, false));
			this.goalSelector.addGoal(2, new OwnerHurtByTargetGoal(this));
			this.goalSelector.addGoal(3, new OwnerHurtTargetGoal(this));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.goalSelector.addGoal(6, new LeapAtTargetGoal(this, (float) 0.5));
			this.goalSelector.addGoal(7, new MeleeAttackGoal(this, 1.2, true));
			this.targetSelector.addGoal(8, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(9, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(11, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEAD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.BONE, (int) (1)));
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
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			Item item = itemstack.getItem();
			if (itemstack.getItem() instanceof SpawnEggItem) {
				retval = super.processInteract(sourceentity, hand);
			} else if (this.world.isRemote) {
				retval = this.isTamed() && this.isOwner(sourceentity) || this.isBreedingItem(itemstack);
			} else {
				if (this.isTamed()) {
					if (this.isOwner(sourceentity)) {
						if (item.isFood() && this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.consumeItemFromStack(sourceentity, itemstack);
							this.heal((float) item.getFood().getHealing());
							retval = true;
						} else if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.consumeItemFromStack(sourceentity, itemstack);
							this.heal(4);
							retval = true;
						} else {
							retval = super.processInteract(sourceentity, hand);
						}
					}
				} else if (this.isBreedingItem(itemstack)) {
					this.consumeItemFromStack(sourceentity, itemstack);
					if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
						this.setTamedBy(sourceentity);
						this.world.setEntityState(this, (byte) 7);
					} else {
						this.world.setEntityState(this, (byte) 6);
					}
					this.enablePersistence();
					retval = true;
				} else {
					retval = super.processInteract(sourceentity, hand);
					if (retval)
						this.enablePersistence();
				}
			}
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			return (CustomEntity) entity.create(this.world);
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(CorruptedfleshItem.block, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelghoulling extends EntityModel<Entity> {
		private final ModelRenderer legleft1;
		private final ModelRenderer legleft2;
		private final ModelRenderer legright2;
		private final ModelRenderer legright1;
		private final ModelRenderer head;
		public Modelghoulling() {
			textureWidth = 64;
			textureHeight = 64;
			legleft1 = new ModelRenderer(this);
			legleft1.setRotationPoint(-4.0F, 20.0F, -2.0F);
			setRotationAngle(legleft1, 0.0F, 0.8727F, 0.0F);
			legleft1.setTextureOffset(36, 36).addBox(-1.234F, -2.0F, -5.6428F, 2.0F, 2.0F, 7.0F, 0.0F, false);
			legleft1.setTextureOffset(0, 39).addBox(-1.2744F, -1.0F, -5.1399F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			legleft2 = new ModelRenderer(this);
			legleft2.setRotationPoint(-4.0F, 20.0F, 3.0F);
			setRotationAngle(legleft2, 0.0F, -0.8727F, 0.0F);
			legleft2.setTextureOffset(25, 33).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 2.0F, 7.0F, 0.0F, false);
			legleft2.setTextureOffset(24, 0).addBox(-2.0404F, -1.0F, 2.4972F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			legright2 = new ModelRenderer(this);
			legright2.setRotationPoint(4.0F, 20.0F, 3.0F);
			setRotationAngle(legright2, 0.0F, 0.8727F, 0.0F);
			legright2.setTextureOffset(32, 0).addBox(0.0F, -2.0F, -2.0F, 2.0F, 2.0F, 7.0F, 0.0F, false);
			legright2.setTextureOffset(0, 16).addBox(0.0404F, -1.0F, 2.4972F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			legright1 = new ModelRenderer(this);
			legright1.setRotationPoint(4.0F, 20.0F, -2.0F);
			setRotationAngle(legright1, 0.0F, -0.8727F, 0.0F);
			legright1.setTextureOffset(29, 24).addBox(-0.766F, -2.0F, -5.6428F, 2.0F, 2.0F, 7.0F, 0.0F, false);
			legright1.setTextureOffset(0, 0).addBox(-0.7256F, -1.0F, -5.1399F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 47.0F, 0.0F);
			head.setTextureOffset(0, 0).addBox(-4.0F, -35.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(0, 16).addBox(-4.5F, -28.0F, -4.5F, 9.0F, 1.0F, 9.0F, 0.0F, false);
			head.setTextureOffset(30, 14).addBox(-4.5F, -36.0F, 2.5F, 9.0F, 8.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 26).addBox(-4.5F, -36.0F, -4.5F, 9.0F, 6.0F, 7.0F, 0.0F, false);
			head.setTextureOffset(28, 29).addBox(-4.5F, -29.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(27, 23).addBox(-3.5F, -30.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(3, 27).addBox(-1.5F, -30.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(25, 26).addBox(0.5F, -30.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(0, 26).addBox(2.5F, -30.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(4, 23).addBox(3.5F, -30.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(0, 23).addBox(-4.5F, -30.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(3, 29).addBox(-2.5F, -29.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(28, 27).addBox(1.5F, -29.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(0, 28).addBox(-0.5F, -29.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(25, 28).addBox(3.5F, -29.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			legleft1.render(matrixStack, buffer, packedLight, packedOverlay);
			legleft2.render(matrixStack, buffer, packedLight, packedOverlay);
			legright2.render(matrixStack, buffer, packedLight, packedOverlay);
			legright1.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.legright2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.legleft1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.legright1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.legleft2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
