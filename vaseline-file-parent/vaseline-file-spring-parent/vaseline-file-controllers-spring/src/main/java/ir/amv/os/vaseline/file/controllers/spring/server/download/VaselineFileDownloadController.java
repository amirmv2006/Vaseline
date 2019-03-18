package ir.amv.os.vaseline.file.controllers.spring.server.download;

import ir.amv.os.vaseline.file.model.api.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.file.service.api.IVaselineFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Controller
public class VaselineFileDownloadController {

	private static final Logger logger = LoggerFactory
			.getLogger(VaselineFileDownloadController.class);

	private static final String CONTENT_DISPOSITION = "Content-Disposition";

	private IVaselineFileService fileService;

	/**
	 * Upload single file using Spring Controller
	 * 
	 */
	@RequestMapping(value = "/file/downloadFile", method = RequestMethod.GET)
	public void uploadFileHandler(
			@RequestParam(value = "fileId") String fileIdStr,
			@RequestParam(required = false, value = "category", defaultValue = "") String category,
			HttpServletResponse response) {
		try {
			final Long fileId = Long.parseLong(fileIdStr);
			IVaselineFileDto byId = fileService.getById(category, fileId);

			String contentType = byId.getContentType();
			response.setContentType(contentType);
			ServletOutputStream outputStream = response.getOutputStream();
			fileService.writeFileContent(category, fileId, outputStream);
			response.setHeader(CONTENT_DISPOSITION, "attachment; filename="
					+ byId.getFileName());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	public void setFileService(IVaselineFileService fileService) {
		this.fileService = fileService;
	}
}
