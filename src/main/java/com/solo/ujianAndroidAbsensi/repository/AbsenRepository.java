package com.solo.ujianAndroidAbsensi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solo.ujianAndroidAbsensi.entity.Absen;

public interface AbsenRepository extends JpaRepository<Absen, Long>{
	@Query(value = "Select * FROM absen WHERE username = ?1", nativeQuery = true)
	public List<Absen> getByUsername(String username);
	
	@Query(value = "Select * FROM absen WHERE username = ?1 AND tanggal_masuk = ?2", nativeQuery = true)
	public Absen getByUsernameAndDateIn(String username, String date);
	
	@Query(value = "Select * FROM absen WHERE username = ?1 AND tanggal_keluar = ?2", nativeQuery = true)
	public Absen getByUsernameAndDateOut(String username, String date);
}
