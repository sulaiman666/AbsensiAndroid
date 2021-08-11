package com.solo.ujianAndroidAbsensi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solo.ujianAndroidAbsensi.entity.Absen;
import com.solo.ujianAndroidAbsensi.repository.AbsenRepository;

@Service
public class AbsenServices implements AbsenServiceInterface{
	@Autowired
	AbsenRepository absenRepo;
	String[] tanggalMasuk;
	String[] tanggalKeluar;
	
	
	@Override
	public List<Absen> getAbsenByUserName(String username) {
		// TODO Auto-generated method stub
		return this.absenRepo.getByUsername(username);
	}


	@Override
	public String absen(Absen absen) {
		// TODO Auto-generated method stub
		if(absen.getTanggalMasuk() != null) {
			tanggalMasuk = absen.getTanggalMasuk().split(" ");
			if(this.absenRepo.getByUsernameAndDateIn(
					absen.getUsername(), tanggalMasuk[0]
					) == null) {
				this.absenRepo.save(absen);
				return "Check In berhasil dilakukan";
			}
			return "Anda Sudah Check In";
		} else if(absen.getTanggalKeluar() != null) {
			tanggalKeluar = absen.getTanggalKeluar().split(" ");
			if (this.absenRepo.getByUsernameAndDateOut(
					absen.getUsername(), tanggalKeluar[0]
					) == null) {
				Absen absenTemp = this.absenRepo
						.getByUsernameAndDateIn(
								absen.getUsername(), 
								tanggalKeluar[0]);
				if(absenTemp != null) {
					absen.setId(absenTemp.getId());
					absen.setTanggalMasuk(absenTemp.getTanggalMasuk());
					absen.setFotoMasuk(absenTemp.getFotoMasuk());
					absen.setGPSMasuk(absenTemp.getGPSMasuk());
					
					this.absenRepo.save(absen);
					return "Check Out berhasil dilakukan";
				} else {
					absen.setTanggalMasuk(absen.getTanggalKeluar());
					absen.setFotoMasuk(absen.getFotoKeluar());
					absen.setGPSMasuk(absen.getGPSKeluar());
					
					absen.setTanggalKeluar(null);
					absen.setFotoKeluar(null);
					absen.setGPSKeluar(null);
					
					this.absenRepo.save(absen);
					return "Anda belum Check In Hari Ini, sehingga anda baru terhitung check in";
				}
				
			}
			return "Anda Sudah Check Out";
		}
		else return "Absen Error";
	}



}
