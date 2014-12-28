package mods.timaxa007.pack.furniture.render;

import mods.timaxa007.pack.furniture.tile.TileEntityLights;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class HandlerBlockLampsOn implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		TileEntityRendererDispatcher.instance.renderTileEntity(new TileEntityLights(), 1.0F);

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return 0;
	}

}
