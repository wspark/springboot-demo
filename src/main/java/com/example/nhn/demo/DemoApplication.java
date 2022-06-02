package com.example.nhn.demo;


import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/")
	public String home() {		
	  return "Hello Docker World deploy by tekton-v0.8";
	}


	@RequestMapping("/log")
	public String log() {	
	List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5);	
	int lastNumber = numbersList.get(5);

	System.out.println("lastNumber =" + lastNumber);

	    logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");
		
	  return "log test";
	}
	
	@RequestMapping("/download")
	public class FileDownloadController {
	
		private static final String EXTERNAL_FILE_PATH = "C:/fileDownloadExample/";
	
		@RequestMapping("/file/{fileName:.+}")
		public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
				@PathVariable("fileName") String fileName) throws IOException {
	
			File file = new File(EXTERNAL_FILE_PATH + fileName);
			if (file.exists()) {
	
				//get the mimetype
				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					//unknown mimetype so set the mimetype to application/octet-stream
					mimeType = "application/octet-stream";
				}
	
				response.setContentType(mimeType);
	
				/**
				 * In a regular HTTP response, the Content-Disposition response header is a
				 * header indicating if the content is expected to be displayed inline in the
				 * browser, that is, as a Web page or as part of a Web page, or as an
				 * attachment, that is downloaded and saved locally.
				 * 
				 */
	
				/**
				 * Here we have mentioned it to show inline
				 */
				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
	
				 //Here we have mentioned it to show as attachment
				 //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
	
				response.setContentLength((int) file.length());
	
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	
				FileCopyUtils.copy(inputStream, response.getOutputStream());
	
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
