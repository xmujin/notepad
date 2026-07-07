package com.x2un.notepad.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class NewScreen extends Screen {
    private Screen parent;
    private MultiLineEditBox content;
    private EditBox title;
    private String titleText = "";
    private String contentText = "";


    protected NewScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }


    @Override
    protected void init() {
        MyButton cancelButton = MyButton.builder(Component.literal("取消"), btn -> {
                    this.onClose();
                })
                .pos(40, 40).size(80, 20).build();

        MyButton saveButton = MyButton.builder(Component.literal("保存"), btn -> {
            // 保存到文件
            File saveDir = new File(Minecraft.getInstance().gameDirectory, "notepads");
            if(!saveDir.exists()) {
                saveDir.mkdirs();
            }


            File file = new File(
                    saveDir,
                    titleText + ".txt"
            );

                    try {
                        if(file.createNewFile()){
                            System.out.println("创建成功");
                        }
                        Files.writeString(
                                file.toPath(),
                                contentText,
                                StandardCharsets.UTF_8
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    System.out.println("保存成功");


                })
                .pos(40, 70).size(80, 20).build();



        this.addRenderableWidget(cancelButton);
        this.addRenderableWidget(saveButton);

        int centerX = (this.width - 200) / 2;

        this.title = new EditBox(
                this.font,
                centerX, 40,
                200, 20,
                Component.literal("标题")
        );

        this.content = MultiLineEditBox.builder()
                .setX(centerX)
                .setY(70)
                .build(this.font, 200, 150, Component.literal("内容"));

        // 在resize后将数据赋值给新的编辑框（防止文本丢失）
        this.title.setValue(titleText);
        this.content.setValue(contentText);

        // 设置监听器
        this.title.setResponder(t -> titleText = t);
        this.content.setValueListener(t -> contentText = t);
        this.addRenderableWidget(this.title);
        this.addRenderableWidget(this.content);

    }

    // 关闭时返回上一个screen
    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(this.parent);
    }


}
