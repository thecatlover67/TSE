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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
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