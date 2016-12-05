package com.jacemcpherson.util;

import com.jacemcpherson.resources.R;
import com.jacemcpherson.resources.Resources;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
            if (ImageCache.isLoaded(mFileName)) {
                BufferedImage img = ImageCache.getImage(mFileName);
                if (mWidth > 0 || mHeight > 0) {
                    img = ImageUtil.resizeImage(img, mWidth, mHeight);
                }
                mCallback.imageLoaded(img, null);
                return;
            }

            try {
                BufferedImage img = Resources.getImage(mFileName);
                ImageCache.putImage(mFileName, img); // we only want to cache the image straight from the file, not resized

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
        void imageLoaded(BufferedImage image, Exception e);
    }

    private static class ImageCache {
        private static final HashMap<String, BufferedImage> mCache = new HashMap<>();

        static boolean isLoaded(String imageName) {
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

        if (ImageCache.isLoaded(fileName)) {
            BufferedImage img = ImageCache.getImage(fileName);

            if (withWidth > 0 || withHeight > 0) {
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
        return loadImageSynchronous(fileName, -1, -1);
    }

    public static BufferedImage loadImageSynchronous(String fileName, int withWidth, int withHeight) {
        if (ImageCache.isLoaded(fileName)) {
            BufferedImage image = ImageCache.getImage(fileName);
            if (withWidth > 0 || withHeight > 0) {
                image = resizeImage(image, withWidth, withHeight);
            }
            return image;
        } else {
            try {
                BufferedImage image = Resources.getImage(fileName);
                ImageCache.putImage(fileName, image);

                if (withWidth > 0 || withHeight > 0) {
                    image = resizeImage(image, withWidth, withHeight);
                }

                return image;
            } catch (IOException e) {
                return null;
            }
        }
    }

    public static BufferedImage flipImage(BufferedImage original) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-original.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(original, null);
    }

    private static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Dimension result = MathUtil.getScaled(image, width, height);

        width = result.width;
        height = result.height;

        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static void clearToTransparent(BufferedImage mBufferedImage) {
        Graphics2D graphics = (Graphics2D) mBufferedImage.getGraphics();
        graphics.setBackground(new Color(255, 255, 255, 0));
        graphics.clearRect(0, 0, mBufferedImage.getWidth(), mBufferedImage.getHeight());
    }

    public static void preloadAllImagesInBackground(ImageLoaderCallback callback) {
        Class<R.image> thing = R.image.class;
        Field[] fields = thing.getFields();

        Thread backgroundThread = new Thread(() -> {
            ArrayList<String> results = new ArrayList<>();

            for (Field field : fields) {
                try {
                    String result = (String) field.get(null);
                    results.add(result);
                } catch (Exception e) {

                }
            }

            for (String image : results) {
                new ImageLoader(image, callback).run();
            }

            callback.imageLoaded(null, null);
        });

        backgroundThread.start();
    }

}
