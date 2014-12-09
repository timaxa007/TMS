package mods.timaxa007.pack.magic.ri;

import org.lwjgl.opengl.GL11;

import mods.timaxa007.pack.furniture.PackInfo;
import mods.timaxa007.pack.stock.model.ModelPiePar;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ItemRenderStuffs implements IItemRenderer{
private IModelCustom model;

public ItemRenderStuffs() {
model=AdvancedModelLoader.loadModel("/assets/timaxa007/obj/stuff.obj");
}

@Override
public boolean handleRenderType(ItemStack is, ItemRenderType type) {return true;}

@Override
public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack is, ItemRendererHelper helper) {return true;}

@Override
public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
NBTTagCompound tag = is.getTagCompound();
//int tex=is.getItemDamage();
int texS=0;
int texC=0;

if(tag!=null) {
if(tag.hasKey("Type")) {texS=tag.getInteger("Type");}
if(tag.hasKey("Cap")) {texC=tag.getInteger("Cap");}
}

GL11.glPushMatrix();
GL11.glTranslatef(0.5F, 0.0F, 0.5F);
GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);

if(type==type.EQUIPPED) {
GL11.glScalef(1.2F, 1.2F, 1.2F);
GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
GL11.glTranslatef(0.075F, -0.5F, -1.75F);
}else if(type==type.EQUIPPED_FIRST_PERSON) {
GL11.glScalef(1.0F, 1.0F, 1.0F);
GL11.glTranslatef(0.0F, 0.0F, -0.5F);
}else if(type==type.INVENTORY) {
GL11.glScalef(0.5F, 0.5F, 0.5F);
GL11.glTranslatef(0.0F, -0.5F, 0.0F);
}else{
GL11.glTranslatef(0.0F, -1.0F, 0.0F);
GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
GL11.glScalef(1.0F, 1.0F, 1.0F);
}

switch(texS) {
case 0:Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/"+"blocks/"+"log_birch.png"));break;
case 1:Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/"+"blocks/"+"log_jungle.png"));break;
case 2:Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/"+"blocks/"+"log_spruce.png"));break;
default:Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/"+"blocks/"+"log_oak.png"));break;//4
}

this.model.renderPart("stick");

switch(texC) {
case 0:Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/"+"blocks/"+"gold_block.png"));break;
case 1:Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/"+"blocks/"+"diamond_block.png"));break;
case 2:Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/"+"blocks/"+"emerald_block.png"));break;
default:Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/"+"blocks/"+"iron_block.png"));break;//4
}

this.model.renderPart("cap_up");
this.model.renderPart("cap_down");

GL11.glPopMatrix();

}

}
