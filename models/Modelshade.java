// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelshade extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer stinger;

	public Modelshade() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.5F, 17.0F, -0.5F);
		body.setTextureOffset(0, 0).addBox(-6.0F, -12.0F, -1.0F, 10.0F, 6.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(18, 10).addBox(-4.0F, -10.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(0, 16).addBox(0.0F, -10.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		stinger = new ModelRenderer(this);
		stinger.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotationAngle(stinger, -0.3491F, 0.0F, 0.0F);
		stinger.setTextureOffset(12, 12).addBox(-1.0F, -1.0603F, -0.658F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		stinger.setTextureOffset(0, 20).addBox(-1.0F, 5.9459F, -1.3646F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		stinger.setTextureOffset(0, 10).addBox(-2.0F, 5.9397F, -4.3941F, 2.0F, 2.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		stinger.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.stinger.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.body.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}