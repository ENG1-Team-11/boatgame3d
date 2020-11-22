package io.github.eng1team11.boatgame2d.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {

    FreeTypeFontGenerator _fontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter _fontParameter;

    public static FontManager fm = new FontManager();

    private FontManager() {
        _fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        _fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    }

    public static FontManager get() { return fm; }

    public BitmapFont getFont(int size) {
        _fontParameter.size = size;
        return _fontGenerator.generateFont(_fontParameter);
    }

}
