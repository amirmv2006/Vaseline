package ir.amv.os.vaseline.file.controllers.spring.server.upload;

import ir.amv.os.vaseline.basics.json.api.server.converter.IVaselineJsonConverter;
import ir.amv.os.vaseline.file.service.api.IVaselineFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amir
 */
@Controller
public class VaselineFileUploadController {

    private static final Logger logger = LoggerFactory
            .getLogger(VaselineFileUploadController.class);

    private IVaselineFileService fileService;
    private IVaselineJsonConverter jsonConverter;

    /**
     * Upload single file using Spring Controller
     *
     * @param category
     */
    @RequestMapping(value = "/file/uploadFile.upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String uploadFileHandler(
            @RequestParam(required = false, value = "name", defaultValue = "") String name,
            @RequestParam(required = false, value = "category", defaultValue = "") String category,
            @RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                if (name.equals("")) {
                    name = file.getName();
                }
                Long uploadFile = fileService.uploadFile(
                        name, category, file.getSize(), file.getContentType(), file.getInputStream());

                result.put("success", true);
                result.put("data", uploadFile);
            } catch (Exception e) {
                result.put("success", false);
                result.put("errorMessage", e.getMessage());
            }
        } else {
            result.put("success", false);
            result.put("errorMessage", "You failed to upload " + name + " because the file was empty.");
        }
        return jsonConverter.toJson(result);
    }

    @Autowired
    public void setFileService(IVaselineFileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setJsonConverter(final IVaselineJsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }
}
