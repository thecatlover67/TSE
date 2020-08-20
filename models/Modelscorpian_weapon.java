// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelscorpian_weapon extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer arm;
	private final ModelRenderer arm2;
	private final ModelRenderer arm3;
	private final ModelRenderer arm4;

	public Modelscorpian_weapon() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(4, 12).addBox(-4.0F, -3.0F, 11.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 12).addBox(-3.0F, -7.0F, 12.0F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(14, 14).addBox(-1.0F, -7.0F, 7.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, -13.0F, 4.0F, 3.0F, 27.0F, 0.0F, false);

		arm = new ModelRenderer(this);
		arm.setRotationPoint(2.5F, 0.5F, -11.5F);
		body.addChild(arm);
		setRotationAngle(arm, 0.0F, -0.3491F, 0.6981F);
		arm.setTextureOffset(0, 9).addBox(-2.5F, -0.5F, -0.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		arm2 = new ModelRenderer(this);
		arm2.setRotationPoint(-2.5F, 0.5F, -11.5F);
		body.addChild(arm2);
		setRotationAngle(arm2, 0.0F, 0.3491F, -0.6981F);
		arm2.setTextureOffset(0, 6).addBox(-6.5F, -0.5F, -0.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		arm3 = new ModelRenderer(this);
		arm3.setRotationPoint(-2.5F, -3.5F, -11.5F);
		body.addChild(arm3);
		setRotationAngle(arm3, 0.0F, 0.3491F, 0.6981F);
		arm3.setTextureOffset(0, 3).addBox(-6.5F, -1.5F, -0.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		arm4 = new ModelRenderer(this);
		arm4.setRotationPoint(2.5F, -3.5F, -11.5F);
		body.addChild(arm4);
		setRotationAngle(arm4, 0.0F, -0.3491F, -0.6981F);
		arm4.setTextureOffset(0, 0).addBox(-2.5F, -1.5F, -0.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}