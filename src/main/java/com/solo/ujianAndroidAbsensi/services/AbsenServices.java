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
	
	
	@Override
	public List<Absen> getAbsenByUserName(String username) {
		// TODO Auto-generated method stub
		return this.absenRepo.getByUsername(username);
	}


	@Override
	public String absen(Absen absen) {
		// TODO Auto-generated method stub
		if(absen.getTanggalMasuk() != null) {
			if(this.absenRepo.getByUsernameAndDateIn(
					absen.getUsername(), absen.getTanggalMasuk()
					) == null) {
				this.absenRepo.save(absen);
				return "Check In berhasil dilakukan";
			}
			return "Anda Sudah Check In";
		} else if(absen.getTanggalKeluar() != null) {
			if (this.absenRepo.getByUsernameAndDateOut(
					absen.getUsername(), absen.getTanggalKeluar()
					) == null) {
				Absen absenTemp = this.absenRepo
						.getByUsernameAndDateIn(
								absen.getUsername(), 
								absen.getTanggalKeluar());
				if(absenTemp != null) {
					absen.setId(absenTemp.getId());
					absen.setTanggalMasuk(absenTemp.getTanggalMasuk());
					absen.setJamMasuk(absenTemp.getJamMasuk());;
					absen.setFotoMasuk(absenTemp.getFotoMasuk());
					
					this.absenRepo.save(absen);
					return "Check Out berhasil dilakukan";
				} else {
					absen.setTanggalMasuk(absen.getTanggalKeluar());
					absen.setFotoMasuk(absen.getFotoKeluar());
					absen.setJamMasuk(absen.getJamKeluar());
					
					absen.setTanggalKeluar(null);
					absen.setFotoKeluar(null);
					
					this.absenRepo.save(absen);
					return "Anda belum Check In Hari Ini, sehingga anda beru terhitung check in";
				}
				
			}
			return "Anda Sudah Check Out";
		}
		else return "Absen Error";
	}



}
