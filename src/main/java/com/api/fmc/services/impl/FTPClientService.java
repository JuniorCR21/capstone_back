package com.api.fmc.services.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FTPClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FTPClientService.class);
	private FTPClient client;
	
	@PostConstruct
	public void postContruct() {
		client = new FTPClient();
		try {
			client.connect("ftp.kcconsultingbusiness.com");
			boolean login = client.login("dev.prueba@familymedicalperu.com","123456");
			client.enterLocalPassiveMode();
			client.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
			client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			LOGGER.info("Login FTPCliente: {}", login);
		} catch (IOException e) {
			LOGGER.error("Error:" + e.getMessage());
		}
	}
	
	public String uploadDocument(MultipartFile multipart, String filename) throws IOException{
		File file = convert(multipart);
		InputStream fis = new FileInputStream(file);
		boolean done = client.storeFile(filename , fis);
		if(!done) {
			throw new IOException();
		}
		fis.close();
		return filename;
	}
	
	public File downloadFile(String remoteFilePath, String localFilePath) throws FileNotFoundException {
		File downloadFile = new File("src/"+localFilePath);
		try {
			OutputStream outputStream;
			outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
			boolean success = client.retrieveFile(remoteFilePath, outputStream);
	        outputStream.close();
	        if (success) {
	        	LOGGER.info("Archivo descargado {}", success);
	        }
	        return downloadFile;
		} catch (IOException e) {
			LOGGER.error("Error:" + e.getMessage());
		}
		return null;
    }
	
	private File convert(MultipartFile multipartFile) throws IOException{
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}
	
	@PreDestroy
	public void preDestroy() {
		try {
			client.logout();
			client.disconnect();
			LOGGER.info("Sesion finalizada con éxito");
		} catch (IOException e) {
			LOGGER.error("Error:" + e.getMessage());
		}
        
    }
}
