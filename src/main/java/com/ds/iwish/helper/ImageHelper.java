package com.ds.iwish.helper;


import org.springframework.web.multipart.MultipartFile;

public class ImageHelper {

    public static String getImageName(MultipartFile image, long prefix, boolean isLarge) {
        String filename = image.getOriginalFilename();
        String[] partsOfFilename = filename.split("\\.");
        String coverExtension = partsOfFilename[partsOfFilename.length - 1];
        String suffix = isLarge ? "-l" : "-s";
        return prefix + suffix + "." + coverExtension;
    }
}
