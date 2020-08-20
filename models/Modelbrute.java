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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		allstuff.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.armright.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.legright.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.armleft.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.legleft.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}