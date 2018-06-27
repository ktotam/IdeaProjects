package pr.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void saveAvatar(String fileName, String path, Long userId);
    void deleteAvatar(Long userId);

}
