package com.jacemcpherson.util;

import com.jacemcpherson.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class ImageUtil {

    private static class ImageLoader implements Runnable {

        private String mFileName;
        private ImageLoaderCallback mCallback;

        private int mWidth = -1;
        private int mHeight = -1;

        ImageLoader(String fileName) {
            mFileName = fileName;
        }

        ImageLoader(String fileName, ImageLoaderCallback callback) {
            this(fileName);
            mCallback = callback;
        }

        ImageLoader(String fileName, ImageLoaderCallback callback, int width, int height) {
            this(fileName, callback);
            mWidth = width;
            mHeight = height;
        }

        @Override
        public void run() {
            try {
                BufferedImage img = Resources.getImage(mFileName);
                ImageCache.putImage(mFileName, img); // we only want to cache the image straight from the file

                if (mWidth > 0 && mHeight > 0) {
                    img = ImageUtil.resizeImage(img, mWidth, mHeight);
                }

                if (mCallback != null)
                    mCallback.imageLoaded(img, null);
            } catch (IOException e) {
                if (mCallback != null)
                    mCallback.imageLoaded(null, e);
            }
        }
    }

    public interface ImageLoaderCallback {
        public void imageLoaded(BufferedImage image, Exception e);
    }

    private static class ImageCache {
        private static final HashMap<String, BufferedImage> mCache = new HashMap<>();

        static boolean imageLoaded(String imageName) {
            return mCache.containsKey(imageName);
        }

        static boolean putImage(String name, BufferedImage image) {
            boolean overwriting = mCache.containsKey(name);
            mCache.put(name, image);
            return overwriting;
        }

        static BufferedImage getImage(String imageName) {
            return mCache.get(imageName);
        }
    }

    public static void loadImage(String fileName, ImageLoaderCallback callback) {
        loadImage(fileName, -1, -1, callback);
    }

    public static void loadImage(String fileName, int withWidth, int withHeight, ImageLoaderCallback callback) throws IllegalArgumentException {
        if ((withWidth < 0 && withHeight >= 0) || (withWidth >= 0 && withHeight < 0)) {
            throw new IllegalArgumentException("You must provide either two positive or two negative integers for width and height.");
        }

        if (ImageCache.imageLoaded(fileName)) {
            BufferedImage img = ImageCache.getImage(fileName);

            if (withWidth >= 0) {
                img = resizeImage(img, withWidth, withHeight);
            }

            if (callback != null)
                callback.imageLoaded(img, null);

        } else {
            Thread thread = new Thread(new ImageLoader(fileName, callback, withWidth, withHeight));
            thread.start();
        }
    }

    public static BufferedImage loadImageSynchronous(String fileName) {
        if (ImageCache.imageLoaded(fileName)) {
            return ImageCache.getImage(fileName);
        } else {
            ImageLoader loader = new ImageLoader(fileName, null);
            loader.run();
            return null;
        }
    }

    private static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}
