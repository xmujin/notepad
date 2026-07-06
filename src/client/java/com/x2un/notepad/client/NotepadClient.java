package com.x2un.notepad.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;


public class NotepadClient implements ClientModInitializer {
	public static final String MOD_ID = "notepad";
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		KeyMapping.Category CATEGORY = KeyMapping.Category.register(
				Identifier.fromNamespaceAndPath(MOD_ID, "custom_ssssdfdaaa")
		);

		// 开启按键映射
		KeyMapping sendToChatKey = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key.example-mod.send_to_chat", // The translation key for the key mapping.
						InputConstants.Type.KEYSYM, // The type of the keybinding; KEYSYM for keyboard, MOUSE for mouse.
						GLFW.GLFW_KEY_J, // The GLFW keycode of the key.
						CATEGORY // The category of the mapping.
				));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (sendToChatKey.consumeClick()) {
				if (client.player != null) {
					client.gui.setScreen(new HomeScreen(Component.empty()));

				}
			}
		});

	}


}