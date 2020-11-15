package br.com.erudio.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.net.HttpHeaders;

import br.com.erudio.data.vo.v1.UploadFileResponseVO;
import br.com.erudio.services.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("File WebService")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

	public static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileStorageService service;

	@ApiOperation(value = "Upload a file")
	@PostMapping(value = "/uploadFile", produces = { "application/json", "application/xml", "application/x-yaml" })
	public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = service.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/v1/downloadFile/")
				.path(fileName).toUriString();

		return new UploadFileResponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@ApiOperation(value = "Upload a multiple files")
	@PostMapping(value = "/uploadMultipleFiles", produces = { "application/json", "application/xml",
			"application/x-yaml" })
	public List<UploadFileResponseVO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(f -> uploadFile(f)).collect(Collectors.toList());
	}

	
	@ApiOperation(value = "Download the file informed on path variable")
	@GetMapping(value = "/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request){
		Resource resource = service.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("Could not determinate file type.");
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}
}
