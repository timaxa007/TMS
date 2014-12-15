package mods.timaxa007.lib;

import net.minecraft.entity.player.EntityPlayer;

public class ActionModel {

public ActionModel() {
//empty
}

public static int rotation_model_90degrees(EntityPlayer player) {
return rotation_model_90degrees(player.rotationYaw);
}

public static int rotation_model_90degrees_invert(EntityPlayer player) {
return invert_90degrees(rotation_model_90degrees(player.rotationYaw));
}

public static int rotation_model_90degrees_invert(float f) {
return invert_90degrees(rotation_model_90degrees(f));
}

public static int rotation_model_90degrees(float f) {

if ((f > 315.0F && f <= 360.0F) || (f > 0 && f <= 45.0F)) return 0;
if (f > 45.0F && f <= 135.0F) return 1;
if (f > 135.0F && f <= 225.0F) return 2;
if (f > 225.0F && f <= 315.0F) return 3;

if ((f > -45.0F && f <= 0.0F) || (f > -360 && f <= -315.0F)) return 0;
if (f > -315.0F && f <= -255.0F) return 1;
if (f > -255.0F && f <= -135.0F) return 2;
if (f > -135.0F && f <= -45.0F) return 3;

System.out.println("Error in rotation_model_90degrees.");
return -1;
}

public static int rotation_model_45degrees(EntityPlayer player) {
return rotation_model_45degrees(player.rotationYaw);
}

public static int rotation_model_45degrees_invert(EntityPlayer player) {
return invert_45degrees(rotation_model_45degrees(player.rotationYaw));
}

public static int rotation_model_45degrees_invert(float f) {
return invert_45degrees(rotation_model_45degrees(f));
}

public static int rotation_model_45degrees(float f) {

if ((f > 337.0F && f <= 360.0F) || (f > 0 && f <= 23.0F)) return 0;
if (f > 23.0F && f <= 67.0F) return 1;
if (f > 67.0F && f <= 113.0F) return 2;
if (f > 113.0F && f <= 157.0F) return 3;
if (f > 157.0F && f <= 203.0F) return 4;
if (f > 203.0F && f <= 247.0F) return 5;
if (f > 247.0F && f <= 293.0F) return 6;
if (f > 293.0F && f <= 337.0F) return 7;

if ((f > -23.0F && f <= 0.0F) || (f > -360 && f <= -337.0F)) return 0;
if (f > -337.0F && f <= -293.0F) return 1;
if (f > -293.0F && f <= -247.0F) return 2;
if (f > -247.0F && f <= -203.0F) return 3;
if (f > -203.0F && f <= -157.0F) return 4;
if (f > -157.0F && f <= -113.0F) return 5;
if (f > -113.0F && f <= -67.0F) return 6;
if (f > -67.0F && f <= -23.0F) return 7;

System.out.println("Error in rotation_model_45degrees.");
return -1;
}
//-------------------------------------------------------------------------
public static int invert_90degrees(int i) {
switch(i) {
case 0: return 2;
case 1: return 3;
case 2: return 0;
case 3: return 1;
default: return -1;
}
}

public static int invert_45degrees(int i) {
switch(i) {
case 0: return 4;
case 1: return 5;
case 2: return 6;
case 3: return 7;
case 4: return 0;
case 5: return 1;
case 6: return 2;
case 7: return 3;
default: return -1;
}
}

}
