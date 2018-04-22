package demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author: b.erden
 * @date: 22.4.2018
 */
public class FileUploadUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    private static final String ABS_PATH = "XX";
    private static String REAL_PATH = "";

    public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
        REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images");
        logger.info(REAL_PATH);

        // make all directories exists
        if (!new File(ABS_PATH).exists()) {
            new File(ABS_PATH).mkdir();
        }
        if (!new File(REAL_PATH).exists()) {
            new File(REAL_PATH).mkdir();
        }

        try {
            // server upload
            file.transferTo(new File(String.format("%s%s.jpg", REAL_PATH, code)));
            // project upload
            file.transferTo(new File(String.format("%s%s.jpg", ABS_PATH, code)));
        } catch (IOException e) {

        }
    }
}
