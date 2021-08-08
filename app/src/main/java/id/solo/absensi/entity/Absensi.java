package id.solo.absensi.entity;

import com.google.gson.annotations.SerializedName;

public class Absensi {
    @SerializedName("id")
    private long id;
    @SerializedName("username")
    private String username;
    @SerializedName("fotoMasuk")
    private String fotoMasuk;
    @SerializedName("fotoKeluar")
    private String fotoKeluar;
    @SerializedName("tanggalMasuk")
    private String tanggalMasuk;
    @SerializedName("tanggalKeluar")
    private String tanggalKeluar;
    @SerializedName("jamMasuk")
    private int jamMasuk;
    @SerializedName("jamKeluar")
    private int jamKeluar;
    @SerializedName("GPS")
    private String GPS;

    public Absensi(long id, String username, String fotoMasuk, String fotoKeluar, String tanggalMasuk, String tanggalKeluar, int jamMasuk, int jamKeluar, String GPS) {
        this.id = id;
        this.username = username;
        this.fotoMasuk = fotoMasuk;
        this.fotoKeluar = fotoKeluar;
        this.tanggalMasuk = tanggalMasuk;
        this.tanggalKeluar = tanggalKeluar;
        this.jamMasuk = jamMasuk;
        this.jamKeluar = jamKeluar;
        this.GPS = GPS;
    }

    public Absensi() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFotoMasuk() {
        return fotoMasuk;
    }

    public void setFotoMasuk(String fotoMasuk) {
        this.fotoMasuk = fotoMasuk;
    }

    public String getFotoKeluar() {
        return fotoKeluar;
    }

    public void setFotoKeluar(String fotoKeluar) {
        this.fotoKeluar = fotoKeluar;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public int getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(int jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public int getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(int jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }
}
