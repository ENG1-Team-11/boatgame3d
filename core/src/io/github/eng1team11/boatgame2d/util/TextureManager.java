package io.github.eng1team11.boatgame2d.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureManager {

    static HashMap<String, Texture> _textures = new HashMap<>();

    /**
     * Load a texture to memory with a given identifier
     *
     * @param filepath   The filepath of the texture
     * @param identifier The identifier of the texture
     */
    public static void loadTexture(String filepath, String identifier) {
        Texture tx = new Texture(Gdx.files.internal(filepath));
        _textures.put(identifier, tx);
    }

    /**
     * Load a texture to memory, using the filepath as the identifier
     *
     * @param filepath The filepath of the texture
     */
    public static void loadTexture(String filepath) {
        Texture tx = new Texture(Gdx.files.internal(filepath));
        _textures.put(filepath, tx);
    }

    /**
     * Get a texture
     *
     * @param identifier The identifier of the texture
     * @return A `Texture` object representing the texture, or null if it is not loaded
     */
    public static Texture getTexture(String identifier) {
        if (!_textures.containsKey(identifier)) return null;
        return _textures.get(identifier);
    }

    /**
     * Unload a texture from memory and dispose of it properly
     *
     * @param identifier The identifier of the texture
     */
    public static void unloadTexture(String identifier) {
        _textures.get(identifier).dispose();
        _textures.remove(identifier);
    }

}
