/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.responsilogistik;

/**
 *
 * @author HP-845
 */
public class BarangBantuan {
    // Atribut private untuk enkapsulasi
    private String kodeBarang;
    private String namaBarang;
    private String kategori;
    private double beratKg;

    // Konstruktor untuk inisialisasi seluruh atribut
    public BarangBantuan(String kodeBarang, String namaBarang, String kategori, double beratKg) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.beratKg = beratKg;
    }

    // Getter methods untuk mengambil nilai saat mencetak manifes
    public String getKodeBarang() { return kodeBarang; }
    public String getNamaBarang() { return namaBarang; }
    public String getKategori() { return kategori; }
    public double getBeratKg() { return beratKg; }
}
