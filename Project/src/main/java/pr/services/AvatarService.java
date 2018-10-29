package pr.services;

import pr.models.Avatar;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AvatarService {
    void saveAvatar(String fileName, String path, String type, String url, Long userId);
    void deleteAvatar(Long userId);

    void writeAvatarToResponse(String filePath, HttpServletResponse response) throws Exception;

}
