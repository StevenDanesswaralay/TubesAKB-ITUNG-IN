package com.example.utstubes_if2_10118078_stevendanesswaralay;

public class pendapatan {
    private String key;
    private String pemasukkan;
    private String tanggal;

    public pendapatan(){

    }

    public String getPemasukkan() {
        return pemasukkan;
    }

    public void setPemasukkan(String pemasukkan) {
        this.pemasukkan = pemasukkan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public pendapatan(String pemasukkan, String tanggal) {
        this.pemasukkan = pemasukkan;
        this.tanggal = tanggal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
