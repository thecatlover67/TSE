// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelshadezombie extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer armleft;
	private final ModelRenderer armright;

	public Modelshadezombie() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 12.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 22.0F, -4.0F);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(bone);
		setRotationAngle(bone, -1.5708F, 0.0F, 0.0F);
		bone.setTextureOffset(0, 36).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		armleft = new ModelRenderer(this);
		armleft.setRotationPoint(4.0F, 22.0F, -2.0F);
		armleft.setTextureOffset(20, 20).addBox(0.0F, -2.0F, -10.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

		armright = new ModelRenderer(this);
		armright.setRotationPoint(-4.0F, 22.0F, -2.0F);
		armright.setTextureOffset(0, 16).addBox(-4.0F, -2.0F, -10.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		armleft.render(matrixStack, buffer, packedLight, packedOverlay);
		armright.render(matrixStack, buffer, packedLight, packedOverlay);
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
		this.armright.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.armleft.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}