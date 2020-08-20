// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelghoul extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer arm1;
	private final ModelRenderer arm2;
	private final ModelRenderer head;

	public Modelghoul() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 12.0F, 0.0F);
		body.setTextureOffset(32, 32).addBox(-4.0F, -15.0F, -2.0F, 8.0F, 13.0F, 4.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-2.5F, 10.0F, 0.0F);
		leg1.setTextureOffset(24, 49).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 14.0F, 3.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(2.5F, 10.0F, 0.0F);
		leg2.setTextureOffset(12, 39).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 14.0F, 3.0F, 0.0F, false);

		arm1 = new ModelRenderer(this);
		arm1.setRotationPoint(-4.0F, -2.0F, 0.0F);
		arm1.setTextureOffset(0, 39).addBox(-3.0F, -1.0F, -1.5F, 3.0F, 14.0F, 3.0F, 0.0F, false);

		arm2 = new ModelRenderer(this);
		arm2.setRotationPoint(4.0F, -2.0F, 0.0F);
		arm2.setTextureOffset(36, 49).addBox(0.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(0, 16).addBox(-4.5F, -1.0F, -4.5F, 9.0F, 1.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(30, 14).addBox(-4.5F, -9.0F, 2.5F, 9.0F, 8.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 26).addBox(-4.5F, -9.0F, -4.5F, 9.0F, 6.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(4, 20).addBox(-4.5F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 16).addBox(-3.5F, -3.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 6).addBox(-1.5F, -3.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(3, 5).addBox(0.5F, -3.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 4).addBox(2.5F, -3.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(3, 1).addBox(3.5F, -3.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 2).addBox(3.5F, -2.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-4.5F, -2.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(3, 3).addBox(-4.5F, -3.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 20).addBox(-2.5F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(4, 18).addBox(1.5F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(4, 16).addBox(-0.5F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 18).addBox(3.5F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay);
		arm1.render(matrixStack, buffer, packedLight, packedOverlay);
		arm2.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
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
		this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.arm2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}