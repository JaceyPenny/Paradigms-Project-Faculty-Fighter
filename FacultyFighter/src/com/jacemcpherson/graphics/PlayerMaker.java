package com.jacemcpherson.graphics;

import com.jacemcpherson.resources.R;
import com.jacemcpherson.util.ImageUtil;
import com.jacemcpherson.util.MathUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class PlayerMaker {

    public static HashMap<Integer, BufferedImage> makePlayerWithHead(String headFile, Dimension size) {
        HashMap<Integer, BufferedImage> player = new HashMap<>();

        player.put(Player.ANIMATION_STILL, addHeadToImage(headFile, R.image.player_still, size));
        player.put(Player.ANIMATION_PUNCH, addHeadToImage(headFile, R.image.player_punch, size));
        player.put(Player.ANIMATION_WALK1, addHeadToImage(headFile, R.image.player_walk1, size));
        player.put(Player.ANIMATION_WALK2, addHeadToImage(headFile, R.image.player_walk2, size));
        player.put(Player.ANIMATION_BLOCK, addHeadToImage(headFile, R.image.player_block, size));

        return player;
    }

    public static HashMap<Integer, BufferedImage> makeReversePlayer(HashMap<Integer, BufferedImage> animation) {
        HashMap<Integer, BufferedImage> flipped = new HashMap<>();

        for (Integer key : animation.keySet()) {
            BufferedImage image = animation.get(key);
            BufferedImage newImage = ImageUtil.flipImage(image);
            flipped.put(key, newImage);
        }

        return flipped;
    }

    private static BufferedImage addHeadToImage(String headFile, String playerFile, Dimension size) {
        BufferedImage playerImage;

        if (size != null)
            playerImage = ImageUtil.loadImageSynchronous(playerFile, size.width, size.height);
        else
            playerImage = ImageUtil.loadImageSynchronous(playerFile);

        BufferedImage headImage = ImageUtil.loadImageSynchronous(headFile);

        BufferedImage newImage = new BufferedImage(playerImage.getWidth(), (int) ((float) playerImage.getHeight() * 1.25f), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(playerImage, 0, newImage.getHeight() - playerImage.getHeight(), new Color(255, 255, 255, 0), null);

        int middleX = (int) (0.333 * playerImage.getWidth());

        int headHeight = (int) (0.3333 * playerImage.getHeight());

        Dimension headSize = MathUtil.getScaled(headImage, -1, headHeight);
        int startX = middleX - headSize.width / 2;

        graphics.drawImage(headImage, startX, 0, headSize.width, headSize.height, null);
        return newImage;
    }
}
