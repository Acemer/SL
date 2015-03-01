package ru.nordwest.nord.common.lib.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import ru.nordwest.nord.Nord;

import java.util.List;

public class OverlayHandler {
        @SubscribeEvent
        public void render(RenderGameOverlayEvent.Post e) {
                if (e.type == RenderGameOverlayEvent.ElementType.ALL) {
                        Entity ent = getMouseOver(e.partialTicks, 500, false);
                        if (ent instanceof EntityItem) {
                                EntityItem item = (EntityItem) ent;
                                Minecraft mc = Minecraft.getMinecraft();
                                if (mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() == Nord.itemScanner)
                                        renderToolTip(item.getEntityItem(), e.resolution.getScaledWidth() / 2, e.resolution.getScaledHeight() / 2);
                        }
                }
        }

        protected void renderToolTip(ItemStack itemStack, int x, int y) {

                Minecraft mc = Minecraft.getMinecraft();
                List list = itemStack.getTooltip(mc.thePlayer, mc.gameSettings.advancedItemTooltips);
                ScaledResolution s = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

                for (int k = 0; k < list.size(); ++k) {
                        if (k == 0) {
                                list.set(k, itemStack.getRarity().rarityColor + (String) list.get(k));
                        } else {
                                list.set(k, EnumChatFormatting.GRAY + (String) list.get(k));
                        }
                }

                FontRenderer font = itemStack.getItem().getFontRenderer(itemStack);
                drawHoveringText(list, x, y, s.getScaledWidth(), s.getScaledHeight(), 0, (font == null ? mc.fontRenderer : font));
        }

        protected void drawHoveringText(List list, int x, int y, int width, int height, int zLevel, FontRenderer font) {
                RenderItem itemRender = new RenderItem();
                GuiScreen gui = new GuiScreen();
                if (!list.isEmpty()) {
                        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
                        RenderHelper.disableStandardItemLighting();
                        GL11.glDisable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_DEPTH_TEST);
                        int k = 0;

                        for (Object aP_146283_1_ : list) {
                                String s = (String) aP_146283_1_;
                                int l = font.getStringWidth(s);

                                if (l > k) {
                                        k = l;
                                }
                        }

                        int j2 = x + 12;
                        int k2 = y - 12;
                        int i1 = 8;

                        if (list.size() > 1) {
                                i1 += 2 + (list.size() - 1) * 10;
                        }

                        if (j2 + k > width) {
                                j2 -= 28 + k;
                        }

                        if (k2 + i1 + 6 > height) {
                                k2 = height - i1 - 6;
                        }

                        itemRender.zLevel = 300.0F;
                        int j1 = -267386864;
                        drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1, zLevel);
                        drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1, zLevel);
                        drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1, zLevel);
                        drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1, zLevel);
                        drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1, zLevel);
                        int k1 = 1347420415;
                        int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
                        drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1, zLevel);
                        drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1, zLevel);
                        drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1, zLevel);
                        drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1, zLevel);

                        for (int i2 = 0; i2 < list.size(); ++i2) {
                                String s1 = (String) list.get(i2);
                                font.drawStringWithShadow(s1, j2, k2, -1);

                                if (i2 == 0) {
                                        k2 += 2;
                                }

                                k2 += 10;
                        }

                        itemRender.zLevel = 0.0F;
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glEnable(GL11.GL_DEPTH_TEST);
                        RenderHelper.enableStandardItemLighting();
                        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                }
        }

        protected void drawGradientRect(int x, int y, int x2, int y2, int dx, int dy, int zLevel) {
                float f = (float) (dx >> 24 & 255) / 255.0F;
                float f1 = (float) (dx >> 16 & 255) / 255.0F;
                float f2 = (float) (dx >> 8 & 255) / 255.0F;
                float f3 = (float) (dx & 255) / 255.0F;
                float f4 = (float) (dy >> 24 & 255) / 255.0F;
                float f5 = (float) (dy >> 16 & 255) / 255.0F;
                float f6 = (float) (dy >> 8 & 255) / 255.0F;
                float f7 = (float) (dy & 255) /
                        255.0F;
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                GL11.glShadeModel(GL11.GL_SMOOTH);
                Tessellator tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_F(f1, f2, f3, f);
                tessellator.addVertex((double) x2, (double) y, (double) zLevel);
                tessellator.addVertex((double) x, (double) y, (double) zLevel);
                tessellator.setColorRGBA_F(f5, f6, f7, f4);
                tessellator.addVertex((double) x, (double) y2, (double) zLevel);
                tessellator.addVertex((double) x2, (double) y2, (double) zLevel);
                tessellator.draw();
                GL11.glShadeModel(GL11.GL_FLAT);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        public Entity getMouseOver(float partialTicks, double distance, boolean canBeCollidedWith) {
                Minecraft mc = Minecraft.getMinecraft();
                Entity pointedEntity = null;
                MovingObjectPosition rayTrace = null;

                if (mc.renderViewEntity != null) {
                        if (mc.theWorld != null) {
                                rayTrace = mc.renderViewEntity.rayTrace(distance, partialTicks);
                                Vec3 positionVec = mc.renderViewEntity.getPosition(partialTicks);
                                double distanceToVec3 = distance;

                                if (rayTrace != null) {
                                        distanceToVec3 = rayTrace.hitVec.distanceTo(positionVec);
                                }

                                Vec3 lookVec = mc.renderViewEntity.getLook(partialTicks);
                                Vec3 posDistVec = positionVec.addVector(lookVec.xCoord * distance, lookVec.yCoord * distance, lookVec.zCoord * distance);
                                Vec3 tempVec = null;
                                double boxExpand = 1.0F;
                                List<Entity> entities = mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.renderViewEntity, mc.renderViewEntity.boundingBox.addCoord(lookVec.xCoord * distance, lookVec.yCoord * distance, lookVec.zCoord * distance).expand(boxExpand, boxExpand, boxExpand));
                                double vecInsideDist = distanceToVec3;

                                for (Entity entity : entities) {
                                        if (!canBeCollidedWith || entity.canBeCollidedWith()) {
                                                double borderSize = entity.getCollisionBorderSize();
                                                AxisAlignedBB expEntityBox = entity.boundingBox.expand(borderSize, borderSize, borderSize);
                                                MovingObjectPosition calculateInterceptPos = expEntityBox.calculateIntercept(positionVec, posDistVec);

                                                if (expEntityBox.isVecInside(positionVec)) {
                                                        if (0.0D < vecInsideDist || vecInsideDist == 0.0D) {
                                                                pointedEntity = entity;
                                                                tempVec = calculateInterceptPos == null ? positionVec : calculateInterceptPos.hitVec;
                                                                vecInsideDist = 0.0D;
                                                        }
                                                } else if (calculateInterceptPos != null) {
                                                        double calcInterceptPosDist = positionVec.distanceTo(calculateInterceptPos.hitVec);

                                                        if (calcInterceptPosDist < vecInsideDist || vecInsideDist == 0.0D) {
                                                                if (entity == mc.renderViewEntity.ridingEntity && !entity.canRiderInteract()) {
                                                                        if (vecInsideDist == 0.0D) {
                                                                                pointedEntity = entity;
                                                                                tempVec = calculateInterceptPos.hitVec;
                                                                        }
                                                                } else {
                                                                        pointedEntity = entity;
                                                                        tempVec = calculateInterceptPos.hitVec;
                                                                        vecInsideDist = calcInterceptPosDist;
                                                                }
                                                        }
                                                }
                                        }
                                }

                                if (pointedEntity != null && (vecInsideDist < distanceToVec3 || rayTrace == null)) {
                                        return pointedEntity;
                                }
                        }
                }

                return null;
        }
}
