package ir.amv.os.vaseline.file.api.server.servlet.upload;

import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;
import ir.amv.os.vaseline.security.shared.api.IAuthenticationApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@Controller
public class FileUploadController implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	private IAuthenticationApi authenticationApi;
	private IFileApi fileApi;
	private Gson gson;

	private ApplicationContext applicationContext;

	/**
	 * Upload single file using Spring Controller
	 * 
	 * @param category
	 */
	@RequestMapping(value = "/uploadFile.upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String uploadFileHandler(
			@RequestParam(required = false, value = "name", defaultValue = "") String name,
			@RequestParam(required = false, value = "category", defaultValue = "") String category,
			@RequestParam("file") MultipartFile file) {
		JsonObject result = new JsonObject();
		if (!file.isEmpty()) {
			try {
				// byte[] bytes = file.getBytes();

				// Creating the directory to store file
				// String rootPath = System.getProperty("catalina.home");
				// File dir = new File(rootPath + File.separator + "tmpFiles");
				// if (!dir.exists())
				// dir.mkdirs();

				// Create the file on server
				// File serverFile = new File(dir.getAbsolutePath()
				// + File.separator + name);
				// BufferedOutputStream stream = new BufferedOutputStream(
				// new FileOutputStream(serverFile));
				// stream.write(bytes);
				// stream.close();

				IFileEntity fileEntity = fileApi.createFile();
				if (category != null && !category.equals("")) {
					fileEntity.setCategory(category);
				}
				fileEntity.setContentType(file.getContentType());
				fileEntity.setFileName(name);
				fileEntity.setFileSize(new Long(file.getSize()));
				fileEntity.setOwner(authenticationApi.getCurrentUsername());
				Long uploadFile = fileApi.uploadFile(fileEntity,
						file.getInputStream());

				// logger.info("Server File Location="
				// + serverFile.getAbsolutePath());

				result.add("success", new JsonPrimitive(true));
				result.add("data", new JsonPrimitive(uploadFile));
			} catch (Exception e) {
				result.add("success", new JsonPrimitive(false));
				result.add("errorMessage", new JsonPrimitive(e.getMessage()));
			}
		} else {
			result.add("success", new JsonPrimitive(false));
			result.add("errorMessage", new JsonPrimitive(
					"You failed to upload " + name
							+ " because the file was empty."));
		}
		return gson().toJson(result);
	}

	private Gson gson() {
		if (gson == null) {
			GsonBuilder gsonBuilder = applicationContext.getBean(GsonBuilder.class);
			gson = gsonBuilder.create();
		}
		return gson;
	}

	@Autowired
	public void setFileApi(IFileApi fileApi) {
		this.fileApi = fileApi;
	}

	@Autowired
	public void setAuthenticationApi(IAuthenticationApi authenticationApi) {
		this.authenticationApi = authenticationApi;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
