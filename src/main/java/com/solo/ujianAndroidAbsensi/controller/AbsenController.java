package com.solo.ujianAndroidAbsensi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.solo.ujianAndroidAbsensi.entity.Absen;
import com.solo.ujianAndroidAbsensi.services.AbsenServices;
import com.solo.ujianAndroidAbsensi.utility.FileUtility;

@RestController
@RequestMapping("/absen")
public class AbsenController {
	@Autowired
	AbsenServices absenServices;
	
	@GetMapping("/{username}")
	public List<Absen> getAbsenByUsername(@PathVariable String username) {
		return this.absenServices.getAbsenByUserName(username);
	}
	
	@PostMapping("/checkin")
	public String checkInAbsen(
			@RequestParam(value = "file") MultipartFile images,
			@ModelAttribute(value = "data") String dataJSON) 
					throws IOException{
		String fileName = StringUtils.cleanPath(images
				.getOriginalFilename());
		
		String uploadDir = "src/main/java/user-photos/";
		FileUtility.saveFile(uploadDir, fileName, images);
		Absen absen = new Gson().fromJson(dataJSON, Absen.class);
		absen.setFotoMasuk(fileName);
		return this.absenServices.absen(absen);
	}
	
	@PostMapping("/checkout")
	public String checkOutAbsen(
			@RequestParam(value = "file") MultipartFile images,
			@ModelAttribute(value = "data") String dataJSON) 
					throws IOException{
		String fileName = StringUtils.cleanPath(images
				.getOriginalFilename());
		
		String uploadDir = "src/main/java/user-photos/";
		FileUtility.saveFile(uploadDir, fileName, images);
		Absen absen = new Gson().fromJson(dataJSON, Absen.class);
		absen.setFotoKeluar(fileName);
		return this.absenServices.absen(absen);
	}
}
