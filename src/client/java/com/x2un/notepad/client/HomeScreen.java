package com.x2un.notepad.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;

public class HomeScreen extends Screen {
    public HomeScreen(Component title) {
        super(title);
    }
    @Override
    protected void init() {


        MyButton newButton = MyButton.builder(Component.literal("新建"), (btn) ->{})
                .pos(40, 40).size(80, 20).build();

        MyButton testButton = MyButton.builder(Component.literal("test"), (btn) ->{})
                .pos(40, 40 + 20 + 10).size(80, 20).build();

        this.addRenderableWidget(newButton);
        this.addRenderableWidget(testButton);

    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        Component text = Component.literal("记事本");

        int textWidth = this.font.width(text);
        int x = (this.width - textWidth) / 2;

        graphics.text(this.font, text, x, 40 - this.font.lineHeight - 10, 0xFFFFFFFF, true);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        // J 键
        if (event.key() == InputConstants.KEY_J) {
            this.onClose();   // 或 minecraft.gui.setScreen(null);
            return true;
        }

        return super.keyPressed(event);
    }
}


