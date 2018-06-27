package pr.services;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pr.models.Avatar;
import pr.repositories.AvatarRepository;
import pr.util.AvatarUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


@Component
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public void saveAvatar(String fileName, String path, Long userId) {
        avatarRepository.saveAvatar(fileName, path, userId);
    }

    @Override
    public void deleteAvatar(Long userId) {
        avatarRepository.deleteAvatarByUserId(userId);
    }
}
