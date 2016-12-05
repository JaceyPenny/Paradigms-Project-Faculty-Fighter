package com.jacemcpherson.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Resources {

    public static ArrayList<Pair<String, String>> mFaculty;

    public static BufferedImage getImage(String fileName) throws IOException {
        return ImageIO.read(new File(fileName));
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
            mFaculty.add(new Pair<>(R.image.faculty_patitz, "Dr. Matthew Patitz"));
            mFaculty.add(new Pair<>(R.image.faculty_qinghual, "Dr. Qinghua Li"));
            mFaculty.add(new Pair<>(R.image.faculty_rxp, "Dr. Reid Phillips"));
            mFaculty.add(new Pair<>(R.image.faculty_sgauch, "Dr. Susan Gauch"));
            mFaculty.add(new Pair<>(R.image.faculty_wingning, "Dr. Wing Li"));
            mFaculty.add(new Pair<>(R.image.faculty_xintaowu, "Dr. Xintao Wu"));
        }

        return mFaculty;
    }

}
