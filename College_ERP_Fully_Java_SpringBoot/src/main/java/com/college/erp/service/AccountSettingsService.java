package com.college.erp.service;

import com.college.erp.model.User;
import com.college.erp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Set;

@Service
public class AccountSettingsService {

    public enum PasswordChangeResult {
        SUCCESS,
        USER_NOT_FOUND,
        WRONG_OLD_PASSWORD,
        PASSWORDS_MISMATCH,
        TOO_SHORT
    }

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg", "image/png", "image/webp", "image/gif"
    );

    private final UserRepository userRepo;
    private final Path uploadRoot;

    public AccountSettingsService(UserRepository userRepo,
                                  @Value("${app.upload.dir:uploads/profiles}") String uploadDir) {
        this.userRepo = userRepo;
        this.uploadRoot = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    public PasswordChangeResult changePassword(String username,
                                               String oldPassword,
                                               String newPassword,
                                               String confirmPassword) {
        if (newPassword == null || newPassword.length() < 4) {
            return PasswordChangeResult.TOO_SHORT;
        }
        if (!newPassword.equals(confirmPassword)) {
            return PasswordChangeResult.PASSWORDS_MISMATCH;
        }

        User user = userRepo.findByUsername(username).orElse(null);
        if (user == null) {
            return PasswordChangeResult.USER_NOT_FOUND;
        }
        if (!user.getPassword().equals(oldPassword)) {
            return PasswordChangeResult.WRONG_OLD_PASSWORD;
        }

        user.setPassword(newPassword);
        userRepo.save(user);
        return PasswordChangeResult.SUCCESS;
    }

    public String storeProfilePicture(String username, String rolePrefix, MultipartFile file,
                                      String existingFilename) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("empty");
        }
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase(Locale.ROOT))) {
            throw new IllegalArgumentException("type");
        }
        if (file.getSize() > 2 * 1024 * 1024) {
            throw new IllegalArgumentException("size");
        }

        String ext = switch (contentType.toLowerCase(Locale.ROOT)) {
            case "image/png" -> ".png";
            case "image/webp" -> ".webp";
            case "image/gif" -> ".gif";
            default -> ".jpg";
        };

        Files.createDirectories(uploadRoot);
        deleteProfilePictureFile(existingFilename);

        String safeUser = username.replaceAll("[^a-zA-Z0-9._-]", "_");
        String filename = rolePrefix + "_" + safeUser + "_" + System.currentTimeMillis() + ext;
        Path target = uploadRoot.resolve(filename).normalize();
        if (!target.startsWith(uploadRoot)) {
            throw new IOException("Invalid path");
        }
        file.transferTo(target);
        return filename;
    }

    public void deleteProfilePictureFile(String filename) {
        if (filename == null || filename.isBlank()) {
            return;
        }
        try {
            Path file = uploadRoot.resolve(filename).normalize();
            if (file.startsWith(uploadRoot)) {
                Files.deleteIfExists(file);
            }
        } catch (IOException ignored) {
            // best effort
        }
    }

    public String publicUrl(String filename) {
        if (filename == null || filename.isBlank()) {
            return null;
        }
        return "/uploads/profiles/" + filename;
    }
}
