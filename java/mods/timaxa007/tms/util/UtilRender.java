package mods.timaxa007.tms.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import mods.timaxa007.tms.lib.ImageUtils;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class UtilRender {
	//------------------------------------------------------------------------------------------------------
	//	(int 255) = (hex 0xFF)
	//------------------------------------------------------------------------------------------------------
	/**@return float[] {red, green, blue} - 0.0F-1.0F.
	 * @param color_hex Hex color (0xFFFFFF).**/
	public static float[] getRGB_f(int color_hex) {
		float r = (color_hex >> 16 & 0xFF) / (float)0xFF;
		float g = (color_hex >> 8 & 0xFF) / (float)0xFF;
		float b = (color_hex & 0xFF) / (float)0xFF;
		return new float[] {r, g, b};
	}

	/**@return int[] {red, green, blue} - 0-255.
	 * @param color_hex Hex color (0xFFFFFF).**/
	public static int[] getRGB_i(int color_hex) {
		int r = (color_hex >> 16 & 0xFF);
		int g = (color_hex >> 8 & 0xFF);
		int b = (color_hex & 0xFF);
		return new int[] {r, g, b};
	}

	/**@return int Hex color.
	 * @param red Color red float - 0.0F-1.0F.
	 * @param green Color green float - 0.0F-1.0F.
	 * @param blue Color blue float - 0.0F-1.0F.**/
	public static int getHEX(float red, float green, float blue) {
		return (int)red * 0xFF << 16 | (int)green * 0xFF << 8 | (int)blue * 0xFF;
	}

	/**@return int Hex color.
	 * @param red Color red int - 0-255.
	 * @param green Color green int - 0-255.
	 * @param blue Color blue int - 0-255.**/
	public static int getHEX(int red, int green, int blue) {
		return (int)red << 16 | (int)green << 8 | (int)blue;
	}

	/**@return int Hex color.
	 * @param <b>colors</b> float[]<br>
	 * float[0] color red - 0.0F-1.0F.<br>
	 * float[1] color green - 0.0F-1.0F.<br>
	 * float[2] color blue - 0.0F-1.0F.**/
	public static int getHEX(float[] colors) {
		return (int)colors[0] * 0xFF << 16 | (int)colors[1] * 0xFF << 8 | (int)colors[2] * 0xFF;
	}

	/**@return int Hex color.
	 * @param <b>colors</b> int[]<br>
	 * int[0] color red - 0-255.<br>
	 * int[1] color green - 0-255.<br>
	 * int[2] color blue - 0-255.**/
	public static int getHEX(int[] colors) {
		return (int)colors[0] << 16 | (int)colors[1] << 8 | (int)colors[2];
	}

	public static void setColor3(int color_hex) {
		float r = (color_hex >> 16 & 0xFF) / (float)0xFF;
		float g = (color_hex >> 8 & 0xFF) / (float)0xFF;
		float b = (color_hex & 0xFF) / (float)0xFF;
		GL11.glColor3f(r, g, b);
	}

	public static void setColor4(int color_hex) {
		float r = (color_hex >> 16 & 0xFF) / (float)0xFF;
		float g = (color_hex >> 8 & 0xFF) / (float)0xFF;
		float b = (color_hex & 0xFF) / (float)0xFF;
		GL11.glColor4f(r, g, b, 1.0F);
	}
	//------------------------------------------------------------------------------------------------------
	public static int colorMix(int color_hex1, int color_hex2) {
		return colorMixAverage(color_hex1, color_hex2);
	}

	public static int colorMixAverage(int color_hex1, int color_hex2) {

		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int r3 = (r1 + r2) / 2;
		int g3 = (g1 + g2) / 2;
		int b3 = (b1 + b2) / 2;

		return r3 << 16 | g3 << 8 | b3;
	}

	public static int colorMixHigher(int color_hex1, int color_hex2) {
		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int r3, g3, b3 = 0;

		if (r1 > r2) r3 = r1; else r3 = r2;
		if (g1 > g2) g3 = g1; else g3 = g2;
		if (b1 > b2) b3 = b1; else b3 = b2;

		return r3 << 16 | g3 << 8 | b3;
	}

	public static int colorMixLower(int color_hex1, int color_hex2) {
		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int r3, g3, b3 = 0;

		if (r1 < r2) r3 = r1; else r3 = r2;
		if (g1 < g2) g3 = g1; else g3 = g2;
		if (b1 < b2) b3 = b1; else b3 = b2;

		return r3 << 16 | g3 << 8 | b3;
	}
	//------------------------------------------------------------------------------------------------------
	public static int colorMixAlpha(int color_hex1, int color_hex2) {
		return colorMixAverageAlpha(color_hex1, color_hex2);
	}

	public static int colorMixAverageAlpha(int color_hex1, int color_hex2) {

		int a1 = (color_hex1 >> 24 & 0xFF);
		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int a2 = (color_hex2 >> 24 & 0xFF);
		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int a3 = (a1 + a2) / 2;
		int r3 = (r1 + r2) / 2;
		int g3 = (g1 + g2) / 2;
		int b3 = (b1 + b2) / 2;

		return a3 << 24 | r3 << 16 | g3 << 8 | b3;
	}

	public static int colorMixHigherAlpha(int color_hex1, int color_hex2) {
		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int r3, g3, b3 = 0;

		if (r1 > r2) r3 = r1; else r3 = r2;
		if (g1 > g2) g3 = g1; else g3 = g2;
		if (b1 > b2) b3 = b1; else b3 = b2;

		return r3 << 16 | g3 << 8 | b3;
	}

	public static int colorMixLowerAlpha(int color_hex1, int color_hex2) {
		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int r3, g3, b3 = 0;

		if (r1 < r2) r3 = r1; else r3 = r2;
		if (g1 < g2) g3 = g1; else g3 = g2;
		if (b1 < b2) b3 = b1; else b3 = b2;

		return r3 << 16 | g3 << 8 | b3;
	}
	//------------------------------------------------------------------------------------------------------
	public static int colorMixAlpha1(int color_hex1, int color_hex2) {
		return colorMixAverageAlpha1(color_hex1, color_hex2);
	}

	public static int colorMixAverageAlpha1(int color_hex1, int color_hex2) {

		int a1 = (color_hex1 >> 24 & 0xFF);
		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int r3 = (r1 + r2) / 2;
		int g3 = (g1 + g2) / 2;
		int b3 = (b1 + b2) / 2;

		return a1 << 24 | r3 << 16 | g3 << 8 | b3;
	}

	public static int colorMixHigherAlpha1(int color_hex1, int color_hex2) {
		int a1 = (color_hex1 >> 24 & 0xFF);
		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int r3, g3, b3 = 0;

		if (r1 > r2) r3 = r1; else r3 = r2;
		if (g1 > g2) g3 = g1; else g3 = g2;
		if (b1 > b2) b3 = b1; else b3 = b2;

		return a1 << 24 | r3 << 16 | g3 << 8 | b3;
	}

	public static int colorMixLowerAlpha1(int color_hex1, int color_hex2) {
		int a1 = (color_hex1 >> 24 & 0xFF);
		int r1 = (color_hex1 >> 16 & 0xFF);
		int g1 = (color_hex1 >> 8 & 0xFF);
		int b1 = (color_hex1 & 0xFF);

		int r2 = (color_hex2 >> 16 & 0xFF);
		int g2 = (color_hex2 >> 8 & 0xFF);
		int b2 = (color_hex2 & 0xFF);

		int r3, g3, b3 = 0;

		if (r1 < r2) r3 = r1; else r3 = r2;
		if (g1 < g2) g3 = g1; else g3 = g2;
		if (b1 < b2) b3 = b1; else b3 = b2;

		return a1 << 24 | r3 << 16 | g3 << 8 | b3;
	}
	//------------------------------------------------------------------------------------------------------
	@Deprecated
	public static int getHEXa(int hex) {
		int a = 255;int r = (hex >> 16 & 0xFF);int g = (hex >> 8 & 0xFF);int b = (hex & 0xFF);
		return a << 24 | r << 16 | g << 8 | b;
	}
	//------------------------------------------------------------------------------------------------------
	public static final void mergerImage(BufferedImage img1, BufferedImage img2) {
		int w = Math.max(img1.getWidth(), img2.getWidth());
		int h = Math.max(img1.getHeight(), img2.getHeight());

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.drawImage(img1, 0, 0, null);
		g.drawImage(img2, 0, 0, null);

		ImageUtils.bindTextureFromImage(image);
	}

	public static final void mergerImage(ResourceLocation tex1, ResourceLocation tex2) {
		BufferedImage img1 = ImageUtils.getImageFrom(tex1);
		BufferedImage img2 = ImageUtils.getImageFrom(tex2);

		int w = Math.max(img1.getWidth(), img2.getWidth());
		int h = Math.max(img1.getHeight(), img2.getHeight());

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.drawImage(img1, 0, 0, null);
		g.drawImage(img2, 0, 0, null);

		//img1.getGraphics().drawImage(img2, 0, 0, (ImageObserver)null);
		//TextureUtil.uploadTextureImage(this.getGlTextureId(), img1);
		ImageUtils.bindTextureFromImage(image);
	}

	public static final void mergerImage(ResourceLocation tex1, ResourceLocation tex2, int hex) {
		BufferedImage img1 = ImageUtils.getImageFrom(tex1);
		BufferedImage img2 = ImageUtils.getImageFrom(tex2);

		for (int x = 0; x < img2.getWidth(); x++){
			for (int y = 0; y < img2.getHeight(); y++){
				img2.setRGB(x, y, UtilRender.colorMixLowerAlpha1(img2.getRGB(x, y), UtilRender.getHEXa(hex)));
			}
		}

		int w = Math.max(img1.getWidth(), img2.getWidth());
		int h = Math.max(img1.getHeight(), img2.getHeight());

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.drawImage(img1, 0, 0, null);
		g.drawImage(img2, 0, 0, null);

		//img1.getGraphics().drawImage(img2, 0, 0, (ImageObserver)null);
		//TextureUtil.uploadTextureImage(this.getGlTextureId(), img1);
		ImageUtils.bindTextureFromImage(image);
	}
	//------------------------------------------------------------------------------------------------------
}
