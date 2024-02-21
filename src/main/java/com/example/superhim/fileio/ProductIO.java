package com.example.superhim.fileio;

import java.io.FileOutputStream;
import java.io.InputStream;


import org.springframework.web.multipart.MultipartFile;

public class ProductIO {
	private String resultString ;
	private FileOutputStream fos;
	private InputStream is;
	
	public String writeProduct (String realPath, String newfile,MultipartFile f) {
		try {
			
			is =  f.getInputStream();
			byte[] b = new byte[is.available()];
			is.read(b);
			
			fos = new FileOutputStream(realPath);
			fos.write(b);
			
			resultString = "saved";
		} catch (Exception e) {
			e.printStackTrace();
			resultString = "failed";
		}
		finally {
			try {
				is.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
}
