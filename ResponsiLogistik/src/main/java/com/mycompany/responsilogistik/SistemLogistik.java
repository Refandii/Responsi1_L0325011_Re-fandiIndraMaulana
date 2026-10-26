/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.responsilogistik;

/**
 *
 * @author HP-845
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class SistemLogistik {
    public static void main(String[] args) {
        // Implementasi Modul 4: Koleksi dinamis ArrayList
        ArrayList<BarangBantuan> daftarBarang = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("=== Sistem Logistik Pendidikan Daerah 3T ===");

        // Implementasi Modul 2: Perulangan (while) untuk menu interaktif
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Barang Bantuan");
            System.out.println("2. Selesai dan Cetak Manifes");
            System.out.print("Pilih menu (1/2): ");
            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                // Implementasi Modul 3: Exception Handling (try-catch-finally)
                try {
                    System.out.print("Masukkan Kode Barang (diawali EDX-): ");
                    String kode = scanner.nextLine();
                    
                    // Validasi string (IllegalArgumentException)
                    if (!kode.startsWith("EDX-")) {
                        throw new IllegalArgumentException("Kode barang harus diawali EDX-");
                    }

                    System.out.print("Masukkan Nama Barang: ");
                    String nama = scanner.nextLine();

                    System.out.print("Masukkan Kategori (Elektronik/Buku dll): ");
                    String kategori = scanner.nextLine();

                    System.out.print("Masukkan Berat Barang (Kg): ");
                    double berat = Double.parseDouble(scanner.nextLine());
                    
                    // Validasi custom exception (BeratInvalidException)
                    if (berat <= 0) {
                        throw new BeratInvalidException("Berat barang tidak boleh negatif atau nol.");
                    }

                    // Pembuatan objek dan penambahan ke koleksi
                    BarangBantuan barang = new BarangBantuan(kode, nama, kategori, berat);
                    daftarBarang.add(barang);
                    System.out.println("[Sukses] Barang berhasil didata!");

                } catch (BeratInvalidException e) {
                    System.out.println("[Error] " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("[Error] " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("[Error] Input berat harus berupa angka!");
                } finally {
                    System.out.println("--- Akhir proses input barang ---");
                }
                
            } else if (pilihan.equals("2")) {
                isRunning = false;
                cetakManifes(daftarBarang);
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }

    private static void cetakManifes(ArrayList<BarangBantuan> daftar) {
        double totalBerat = 0;
        
        // Menghitung akumulasi berat
        for (BarangBantuan b : daftar) {
            totalBerat += b.getBeratKg();
        }

        // Implementasi Modul 2: Percabangan (if-else) label armada
        String armada = (totalBerat < 50) ? "Mobil Pickup" : "Truk Ekspedisi";

        // Implementasi Modul 5: File I/O
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("manifest_pengiriman.txt"))) {
            writer.write("=== DOKUMEN MANIFES PENGIRIMAN 3T ===\n");
            writer.write("Total Barang         : " + daftar.size() + " item\n");
            writer.write("Total Berat Muatan   : " + totalBerat + " kg\n");
            writer.write("Armada yang Digunakan: " + armada + "\n");
            writer.write("--------------------------------------------------\n");
            writer.write("Detail Muatan:\n");
            
            for (BarangBantuan b : daftar) {
                writer.write(String.format("- [%s] %s (%s) : %.2f kg\n", 
                    b.getKodeBarang(), b.getNamaBarang(), b.getKategori(), b.getBeratKg()));
            }
            
            System.out.println("\n[Informasi] Data manifes berhasil diekspor ke manifest_pengiriman.txt");
        } catch (IOException e) {
            System.out.println("[Error] Gagal menulis ke file manifes: " + e.getMessage());
        }
    }
}
