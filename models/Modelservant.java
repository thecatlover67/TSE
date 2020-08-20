// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelservant extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer legr;
	private final ModelRenderer legl;

	public Modelservant() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 10.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-4.0F, -16.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		legr = new ModelRenderer(this);
		legr.setRotationPoint(-4.0F, -2.0F, 0.0F);
		legr.setTextureOffset(14, 16).addBox(-2.0F, -2.5F, -3.0F, 2.0F, 12.0F, 5.0F, 0.0F, false);
		legr.setTextureOffset(32, 0).addBox(-2.0F, 0.0F, -4.5F, 2.0F, 17.0F, 3.0F, 0.0F, false);
		legr.setTextureOffset(24, 0).addBox(-2.0F, 9.0F, -3.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		legr.setTextureOffset(10, 33).addBox(-2.0F, 13.5F, -3.0F, 2.0F, 11.0F, 3.0F, 0.0F, false);
		legr.setTextureOffset(23, 16).addBox(-2.0F, 24.0F, -1.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		legl = new ModelRenderer(this);
		legl.setRotationPoint(4.0F, -2.0F, 0.0F);
		legl.setTextureOffset(0, 16).addBox(0.0F, -2.5F, -3.0F, 2.0F, 12.0F, 5.0F, 0.0F, false);
		legl.setTextureOffset(28, 28).addBox(0.0F, 0.0F, -4.5F, 2.0F, 17.0F, 3.0F, 0.0F, false);
		legl.setTextureOffset(0, 0).addBox(0.0F, 9.0F, -3.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		legl.setTextureOffset(0, 33).addBox(0.0F, 13.5F, -3.0F, 2.0F, 11.0F, 3.0F, 0.0F, false);
		legl.setTextureOffset(9, 16).addBox(0.0F, 24.0F, -1.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		legr.render(matrixStack, buffer, packedLight, packedOverlay);
		legl.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.legr.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.legl.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}