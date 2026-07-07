package com.x2un.notepad.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeScreen extends Screen {
    public static final String MOD_ID = "notepad";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);




    public HomeScreen(Component title) { super(title); }


    @Override
    protected void init() {

        int centerX = (this.width - 200) / 2;

        MyButton newButton = MyButton.builder(Component.literal("新建"), btn -> {
                    Minecraft.getInstance().gui.setScreen(new NewScreen(Component.empty(), this));
                })
                .pos(40, 40).size(80, 20).build();

        MyButton testButton = MyButton.builder(Component.literal("编辑"), btn -> {})
                .pos(40, 70).size(80, 20).build();
        testButton.active = false;



        this.addRenderableWidget(newButton);
        this.addRenderableWidget(testButton);

        LOGGER.info("我被初始化了");
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


