package pr.services;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pr.models.Avatar;
import pr.repositories.AvatarRepository;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public void saveAvatar(String fileName, String path, String type, String url, Long userId) {
        avatarRepository.saveAvatar(fileName, path, type, url, userId);
    }

    @Override
    public void deleteAvatar(Long userId) {
        avatarRepository.deleteAvatarByUserId(userId);
    }

    @Override
    public Avatar getAvatarByUserId(Long userId) {
        Avatar avatar = avatarRepository.findOneByUserId(userId);
        if (avatar == null) {
            return avatarRepository.findOneByUserId(0L);
        }
        return avatar;
    }

    @Override
    public void writeAvatarToResponse(String filePath, HttpServletResponse response) throws Exception {
        Optional<Avatar> avatar = avatarRepository.findAvatarByFileName(filePath);
        if (avatar.isPresent()) {
            Avatar avatar1 = avatar.get();
            response.setContentType(avatar1.getType());
            File file = new File(avatar1.getPath());
            InputStream inputStream = new FileInputStream(file);
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();

        }
    }

    @Override
    public List<Avatar> getAllAvatars() {
        return avatarRepository.findAllAvatars();
    }


}
