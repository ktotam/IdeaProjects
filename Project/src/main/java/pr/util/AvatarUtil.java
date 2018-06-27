package pr.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pr.models.Avatar;

import java.util.UUID;

@Component
public class AvatarUtil {

    @Value("${storage.path}")
    private String storagePath;

    public Avatar convertFromMultipart(MultipartFile file) {
        String fileName = file.getName();
        return Avatar.builder()
                .fileName(fileName)
                .build();
    }

    private String createStorageName(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        String newFileName = UUID.randomUUID().toString();
        return newFileName + "." + extension;

    }
}
