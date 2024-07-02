package daelim.book.rental.admin.util;

import java.io.File;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

    public String upload(MultipartFile file) {
        boolean result = false;

        // 파일 저장
        String fileOriName = file.getOriginalFilename();
        String fileExtension = fileOriName.substring(file.getOriginalFilename().lastIndexOf("."), fileOriName.length());
        String uploadDir = "C:\\JavaProject\\BookRental\\src\\main\\webapp\\resources\\upload";

        UUID uuid = UUID.randomUUID();
        String uniqueName = uuid.toString().replaceAll("-","");

        File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);

        if(!saveFile.exists()) {
            saveFile.mkdirs();
        }

        try {
            file.transferTo(saveFile);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(result) {
            System.out.println("File upload SUCCESS!!");
            return uniqueName+fileExtension;
        } else {
            System.out.println("File upload FAIL!!");
            return null;
        }
    }

}
