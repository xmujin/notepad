package com.x2un.notepad.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;

public class MyButton extends AbstractButton {
    protected final OnPress onPress;

    public MyButton(final int x, final int y, final int width, final int height, final Component message, final OnPress onPress) {
        this.onPress = onPress;
        super(x, y, width, height, message);
    }

    public static MyButton.Builder builder(final Component message, final OnPress onPress) {
        return new MyButton.Builder(message, onPress);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {

    }

    @Override
    public void onPress(InputWithModifiers input) {
        this.onPress.onPress(this);
    }

    // 重写该方法用于自定义绘制按钮
    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        int color = isActive() ? 0xFF606060 : 0x80404040; // 灰色
        if (isHovered() && isActive()) {
            color = 0xFF404040;
        }
        graphics.fill(getX(), getY(), getX() + this.width, getY() + this.height, color);

        this.extractDefaultLabel(
                graphics.textRendererForWidget(
                        this,
                        GuiGraphicsExtractor.HoveredTextEffects.NONE
                )
        );
    }

    public static class Builder {
        private final Component message;
        private final OnPress onPress;
        private int x;
        private int y;
        private int width = 150;
        private int height = 20;

        public Builder(final Component message, final OnPress onPress) {
            this.message = message;
            this.onPress = onPress;
        }

        public Builder size(final int width, final int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder pos(final int x, final int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public MyButton build() {
            return new MyButton(this.x, this.y, this.width, this.height, this.message, this.onPress);
        }



    }

    // 按钮按下的回调接口
    public interface OnPress {
        void onPress(final MyButton button);
    }
}


