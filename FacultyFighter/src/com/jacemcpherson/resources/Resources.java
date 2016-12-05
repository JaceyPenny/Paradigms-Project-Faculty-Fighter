package com.jacemcpherson.resources;

import com.jacemcpherson.graphics.Player;
import com.jacemcpherson.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Resources {

    private static ArrayList<Pair<String, String>> mFaculty;
    private static HashMap<Integer, BufferedImage> mPlayer;

    public static BufferedImage getImage(String fileName) throws IOException {
        return ImageIO.read(new File(fileName));
    }

    public static HashMap<Integer, BufferedImage> getPlayerAnimation(Dimension size) {
        if (mPlayer == null) {
            mPlayer = new HashMap<>();

            if (size == null) {
                mPlayer.put(Player.ANIMATION_STILL, ImageUtil.loadImageSynchronous(R.image.player_still));
                mPlayer.put(Player.ANIMATION_PUNCH, ImageUtil.loadImageSynchronous(R.image.player_punch));
                mPlayer.put(Player.ANIMATION_WALK1, ImageUtil.loadImageSynchronous(R.image.player_walk1));
                mPlayer.put(Player.ANIMATION_WALK2, ImageUtil.loadImageSynchronous(R.image.player_walk2));
                mPlayer.put(Player.ANIMATION_BLOCK, ImageUtil.loadImageSynchronous(R.image.player_block));
            } else {
                mPlayer.put(Player.ANIMATION_STILL, ImageUtil.loadImageSynchronous(R.image.player_still, size.width, size.height));
                mPlayer.put(Player.ANIMATION_PUNCH, ImageUtil.loadImageSynchronous(R.image.player_punch, size.width, size.height));
                mPlayer.put(Player.ANIMATION_WALK1, ImageUtil.loadImageSynchronous(R.image.player_walk1, size.width, size.height));
                mPlayer.put(Player.ANIMATION_WALK2, ImageUtil.loadImageSynchronous(R.image.player_walk2, size.width, size.height));
                mPlayer.put(Player.ANIMATION_BLOCK, ImageUtil.loadImageSynchronous(R.image.player_block, size.width, size.height));
            }
        }

        return mPlayer;
    }

    public static HashMap<Integer, BufferedImage> getPlayerAnimation() {
        return getPlayerAnimation(null);
    }

    public static ArrayList<Pair<String, String>> getFacultyFiles() {
        if (mFaculty == null) {
            mFaculty = new ArrayList<>();
            mFaculty.add(new Pair<>(R.image.faculty_panda, "Dr. Brajendra Panda"));
            mFaculty.add(new Pair<>(R.image.faculty_cbobda, "Dr. Christophe Bobda"));
            mFaculty.add(new Pair<>(R.image.faculty_dandrews, "Dr. David Andrews"));
            mFaculty.add(new Pair<>(R.image.faculty_drt, "Dr. Dale Thompson"));
            mFaculty.add(new Pair<>(R.image.faculty_frankliu, "Dr. Xiaoqing Liu"));
            mFaculty.add(new Pair<>(R.image.faculty_gholmes, "Dr. George Holmes"));
            mFaculty.add(new Pair<>(R.image.faculty_gordonb, "Dr. Merwin Beavers"));
            mFaculty.add(new Pair<>(R.image.faculty_jdi, "Dr. Jia Di"));
            mFaculty.add(new Pair<>(R.image.faculty_jgauch, "Dr. John Gauch"));
            mFaculty.add(new Pair<>(R.image.faculty_jparkers, "Dr. Pat Parkerson"));
            mFaculty.add(new Pair<>(R.image.faculty_mgashler, "Dr. Michael Gashler"));
            mFaculty.add(new Pair<>(R.image.faculty_moustafa, "Dr. Rida Moustafa"));
            mFaculty.add(new Pair<>(R.image.faculty_mqhuang, "Dr. Miaoqing Huang"));
            mFaculty.add(new Pair<>(R.image.faculty_qinghual, "Dr. Qinghua Li"));
            mFaculty.add(new Pair<>(R.image.faculty_rxp, "Dr. Reid Phillips"));
            mFaculty.add(new Pair<>(R.image.faculty_sgauch, "Dr. Susan Gauch"));
            mFaculty.add(new Pair<>(R.image.faculty_wingning, "Dr. Wing Li"));
            mFaculty.add(new Pair<>(R.image.faculty_xintaowu, "Dr. Xintao Wu"));
        }

        return mFaculty;
    }

}
