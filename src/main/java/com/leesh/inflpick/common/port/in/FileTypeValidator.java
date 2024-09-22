package com.leesh.inflpick.common.port.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class FileTypeValidator {

    private FileTypeValidator() {
        throw new IllegalStateException("Utility class");
    }

    // Supported image MIME types
    private static final Set<String> IMAGE_MIME_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/bmp",
            "image/webp"
    );

    // Supported image file extensions
    private static final Set<String> IMAGE_EXTENSIONS = Set.of(
            "jpg", "jpeg", "png", "gif", "bmp", "webp"
    );

    public static void validateImageFile(MultipartFile file) {
        if (!isImageFile(file)) {
            throw new NotImageTypeException("The uploaded file is not an image");
        }
    }

    /**
     * Check if the uploaded file is an image based on the file's MIME type and extension.
     *
     * @param file the uploaded MultipartFile
     * @return true if the file is an image, false otherwise
     */
    public static boolean isImageFile(MultipartFile file) {
        // Check the file's content type (MIME type)
        String mimeType = file.getContentType();
        if (mimeType == null || !IMAGE_MIME_TYPES.contains(mimeType)) {
            return false;
        }

        // Check the file's extension
        String extension = getFileExtension(file.getOriginalFilename());
        return extension != null && IMAGE_EXTENSIONS.contains(extension.toLowerCase());
    }

    /**
     * Get the file extension from a file name.
     *
     * @param fileName the original file name
     * @return the file extension (without the dot) or null if not found
     */
    private static String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return null;
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    /**
     * Optional: Verify the file's magic number to prevent MIME type spoofing.
     * This method uses the file's "magic numbers" to confirm it's a valid image.
     *
     * @param filePath the path to the file
     * @return true if the file matches a known image type by its magic number, false otherwise
     * @throws IOException if the file cannot be read
     */
    public static boolean isImageFileByMagicNumber(Path filePath) throws IOException {
        String mimeType = Files.probeContentType(filePath);
        return mimeType != null && IMAGE_MIME_TYPES.contains(mimeType);
    }
}
