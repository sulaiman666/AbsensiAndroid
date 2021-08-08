package com.solo.ujianAndroidAbsensi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table 
public class Absen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 150)
	private String username;
	@Column(length = 300)
	private String fotoMasuk;
	@Column(length = 300)
	private String fotoKeluar;
	@Column(length = 100)
	private String tanggalMasuk;
	@Column(length = 100)
	private String tanggalKeluar;
	private int jamMasuk;
	private int jamKeluar;
	@Column(length = 200)
	private String GPS;
}
