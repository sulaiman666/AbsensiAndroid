package com.solo.ujianAndroidAbsensi.services;

import java.util.List;

import com.solo.ujianAndroidAbsensi.entity.Absen;

public interface AbsenServiceInterface {
	public List<Absen> getAbsenByUserName(String username);
	public String absen(Absen absen);
}
