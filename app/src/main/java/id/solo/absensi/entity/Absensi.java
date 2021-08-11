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
    @SerializedName("GPSMasuk")
    private String GPSMasuk;
    @SerializedName("GPSKeluar")
    private String GPSKeluar;

    public Absensi(long id, String username, String fotoMasuk, String fotoKeluar, String tanggalMasuk, String tanggalKeluar, String GPSMasuk, String GPSKeluar) {
        this.id = id;
        this.username = username;
        this.fotoMasuk = fotoMasuk;
        this.fotoKeluar = fotoKeluar;
        this.tanggalMasuk = tanggalMasuk;
        this.tanggalKeluar = tanggalKeluar;
        this.GPSMasuk = GPSMasuk;
        this.GPSKeluar = GPSKeluar;
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

    public String getGPSMasuk() {
        return GPSMasuk;
    }

    public void setGPSMasuk(String GPSMasuk) {
        this.GPSMasuk = GPSMasuk;
    }

    public String getGPSKeluar() {
        return GPSKeluar;
    }

    public void setGPSKeluar(String GPSKeluar) {
        this.GPSKeluar = GPSKeluar;
    }
}
