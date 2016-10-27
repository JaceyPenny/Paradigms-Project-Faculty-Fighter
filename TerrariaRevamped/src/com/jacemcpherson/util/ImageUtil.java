package com.jacemcpherson.util;

import com.jacemcpherson.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtil  {

    public static class ImageLoader implements Runnable {

        private String mFileName;
        private ImageLoaderCallback mCallback;

        private int mWidth = -1;
        private int mHeight = -1;

        public ImageLoader(String fileName, ImageLoaderCallback callback) {
            mFileName = fileName;
            mCallback = callback;
        }

        public ImageLoader(String fileName, ImageLoaderCallback callback, int width, int height) {
            this(fileName, callback);
            mWidth = width;
            mHeight = height;
        }

        @Override
        public void run() {
            BufferedImage img = null;
            try {
                img = Resources.getImage(mFileName);
                if (mWidth > 0 && mHeight > 0) {
                    img = ImageUtil.resizeImage(img, mWidth, mHeight);
                }
            } catch (IOException e) {
                mCallback.imageLoaded(null, e);
            }
            mCallback.imageLoaded(img, null);
        }
    }

    public interface ImageLoaderCallback {
        public void imageLoaded(BufferedImage image, Exception e);
    }

    public static void loadImage(String fileName, ImageLoaderCallback callback) {
        loadImage(fileName, -1, -1, callback);
    }

    public static void loadImage(String fileName, int withWidth, int withHeight, ImageLoaderCallback callback) {
        Thread thread = new Thread(new ImageLoader(fileName, callback, withWidth, withHeight));
        thread.start();
    }

    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}
