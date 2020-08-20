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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.legright2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.legleft1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.legright1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.legleft2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}