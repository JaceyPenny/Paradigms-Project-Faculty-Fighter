package com.jacemcpherson.view;

import com.jacemcpherson.controller.Application;
import com.jacemcpherson.graphics.Draw;
import com.jacemcpherson.graphics.Sprite;
import com.jacemcpherson.graphics.TextDrawingOptions;
import com.jacemcpherson.model.BaseModel;
import com.jacemcpherson.resources.Pair;
import com.jacemcpherson.util.FontUtil;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.util.MathUtil;
import com.jacemcpherson.widget.BaseWidget;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CharacterView extends BaseView {

    String mTeacherName = "";
    BufferedImage mCurrentTeacherImage = null;
    Sprite mTeacherSprite = null;

    String mOutOfText = "";

    public CharacterView(Application application, BaseModel model) {
        super(application, model);

        mTeacherSprite = new Sprite(null);

        addSprite(mTeacherSprite);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Draw.drawText(g, "Choose your enemy:", this,
                new TextDrawingOptions()
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .paddingTop(10)
                        .font(FontUtil.gameFont(32f))
                        .color(Color.gray)
        );

        Draw.drawText(g, mTeacherName, this,
                new TextDrawingOptions()
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .paddingTop(40)
                        .font(FontUtil.gameFont(24f))
                        .color(Color.white)
        );

        Draw.drawText(g, mOutOfText, this,
                new TextDrawingOptions()
                        .horizontalPosition(TextDrawingOptions.HorizontalTextPosition.CENTER)
                        .verticalPosition(TextDrawingOptions.VerticalTextPosition.TOP)
                        .paddingTop(70)
                        .font(FontUtil.gameFont(18f))
                        .color(Color.white)
        );


    }

    public void setCurrentTeacher(ArrayList<Pair<String, String>> teachers, int which) {
        Pair<String, String> pair = teachers.get(which);
        BufferedImage teacherImage = ImageUtil.loadImageSynchronous(pair.getFirst());
        mTeacherSprite.setImage(teacherImage);
        mTeacherSprite.setSize(100, -1);
        mTeacherSprite.setPosition(MathUtil.getPositionInView(
                this,
                mTeacherSprite.getWidth(),
                mTeacherSprite.getHeight(),
                BaseWidget.HorizontalGravity.CENTER,
                BaseWidget.VerticalGravity.CENTER)
        );
        mTeacherName = pair.getSecond();

        mOutOfText = String.format("%d of %d", which + 1, teachers.size());
    }
}
