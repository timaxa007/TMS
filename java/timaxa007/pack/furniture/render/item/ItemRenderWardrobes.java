package timaxa007.pack.furniture.render.item;

import timaxa007.pack.conjoint.lib.AddTextureModel;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

public class ItemRenderWardrobes implements IItemRenderer {
	private static final IModelCustom mdlS = AdvancedModelLoader.loadModel(new ResourceLocation("timaxa007", "obj/square_side.obj"));
	private static final IModelCustom mdlT = AdvancedModelLoader.loadModel(new ResourceLocation("timaxa007", "obj/square_top.obj"));
	private static final IModelCustom mdlB = AdvancedModelLoader.loadModel(new ResourceLocation("timaxa007", "obj/square_bottom.obj"));

	public ItemRenderWardrobes() {

	}

	@Override
	public boolean handleRenderType(ItemStack is, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack is, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
		String tex = "";
		int rot = 2;

		//front
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.0F, 0.5F);
		GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-180F+((float)(4-rot)*90F), 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(AddTextureModel.get(tex).getTexture());
		mdlS.renderAll();
		GL11.glPopMatrix();

		//right
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.0F, 0.5F);
		GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-90F+((float)(4-rot)*90F), 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(AddTextureModel.get(tex).getTexture());
		mdlS.renderAll();
		GL11.glPopMatrix();

		//back
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.0F, 0.5F);
		GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(0F+((float)(4-rot)*90F), 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(AddTextureModel.get(tex).getTexture());
		mdlS.renderAll();
		GL11.glPopMatrix();

		//left
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.0F, 0.5F);
		GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(90F+((float)(4-rot)*90F), 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(AddTextureModel.get(tex).getTexture());
		mdlS.renderAll();
		GL11.glPopMatrix();

		//top
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.0F, 0.5F);
		GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-180F+((float)(4-rot)*90F), 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(AddTextureModel.get(tex).getTexture());
		mdlT.renderAll();
		GL11.glPopMatrix();

		//bottom
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.0F, 0.5F);
		GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-180F+((float)(4-rot)*90F), 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(AddTextureModel.get(tex).getTexture());
		mdlB.renderAll();
		GL11.glPopMatrix();

	}

}
