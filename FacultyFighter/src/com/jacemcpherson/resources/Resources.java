package com.jacemcpherson.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {

    private static String[] mFacultyFiles = null;

    public static BufferedImage getImage(String fileName) throws IOException {
        return ImageIO.read(new File(fileName));
    }

    public static String[] getFacultyFiles() {
        if (mFacultyFiles == null) {
            mFacultyFiles = new String[] {
                    R.image.faculty_panda,
                    R.image.faculty_cbobda,
                    R.image.faculty_dandrews,
                    R.image.faculty_drt,
                    R.image.faculty_frankliu,
                    R.image.faculty_gholmes,
                    R.image.faculty_gordonb,
                    R.image.faculty_jdi,
                    R.image.faculty_jgauch,
                    R.image.faculty_jparkers,
                    R.image.faculty_mgashler,
                    R.image.faculty_moustafa,
                    R.image.faculty_mqhuang,
                    R.image.faculty_patitz,
                    R.image.faculty_qinghual,
                    R.image.faculty_rxp,
                    R.image.faculty_sgauch,
                    R.image.faculty_wingning,
                    R.image.faculty_xintaowu
            };
        }

        return mFacultyFiles;
    }

}
