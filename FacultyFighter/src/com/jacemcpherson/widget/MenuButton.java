package com.jacemcpherson.widget;

import com.jacemcpherson.graphics.Background;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.TextDrawingOptions;
import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.FontUtil;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.view.BaseView;

import java.awt.*;

public class MenuButton extends BaseWidget {

    private final Background OPEN_BG = new Background(ImageUtil.loadImageSynchronous(R.image.menu_button_open), Background.BackgroundType.IMAGE_CENTER_FIT);
    private final Background HOVERED_BG = new Background(ImageUtil.loadImageSynchronous(R.image.menu_button_hovered), Background.BackgroundType.IMAGE_CENTER_FIT);
    private final Background PRESSED_BG = new Background(ImageUtil.loadImageSynchronous(R.image.menu_button_pressed), Background.BackgroundType.IMAGE_CENTER_FIT);
    private final Background OPEN_CHECKED_BG = new Background(ImageUtil.loadImageSynchronous(R.image.menu_button_open_checked), Background.BackgroundType.IMAGE_CENTER_FIT);
    private final Background HOVERED_CHECKED_BG = new Background(ImageUtil.loadImageSynchronous(R.image.menu_button_hovered_checked), Background.BackgroundType.IMAGE_CENTER_FIT);
    private final Background PRESSED_CHECKED_BG = new Background(ImageUtil.loadImageSynchronous(R.image.menu_button_pressed_checked), Background.BackgroundType.IMAGE_CENTER_FIT);

    String mText = "";

    boolean mChecked = false;

    public MenuButton(BaseView parent) {
        this(parent, 240, 64);
    }

    public MenuButton(BaseView parent, int width, int height) {
        this(parent, width, height, 0, 0, 0);
    }

    public MenuButton(BaseView parent, int width, int height, int xPosition, int yPosition) {
        this(parent, width, height, xPosition, yPosition, 0);
    }

    public MenuButton(BaseView parent, int width, int height, int zPosition) {
        this(parent, width, height, 0, 0, zPosition);
    }

    public MenuButton(BaseView parent, int width, int height, int xPosition, int yPosition, int zPosition) {
        super(parent, width, height, xPosition, yPosition, zPosition);
        updateBackground();
    }

    public void setText(String text) {
        mText = text;
        drawWidget();
    }

    public String getText() {
        return mText;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void drawWidget() {
        super.drawWidget();
        TextDrawingOptions options = new TextDrawingOptions();
        options.horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                .verticalPosition(TextDrawingOptions.VerticalTextPosition.CENTER)
                .font(FontUtil.gameFont((getMouseState() == MouseState.PRESSED) ? 21f : 24f))
                .shiftLeft(5)
                .color(Color.black);
        Draw.drawText(getBufferedImage().getGraphics(), mText, this, options);

    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        updateBackground();
    }

    public void updateBackground() {
        switch (getMouseState()) {
            case OPEN:
                setBackground((mChecked) ? OPEN_CHECKED_BG : OPEN_BG);
                break;
            case HOVERED:
                setBackground((mChecked) ? HOVERED_CHECKED_BG : HOVERED_BG);
                break;
            case PRESSED:
                setBackground((mChecked) ? PRESSED_CHECKED_BG : PRESSED_BG);
                break;
        }
    }
}
