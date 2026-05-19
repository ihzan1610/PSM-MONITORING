# PSM Sawit Pantau - CLEAN GitHub Debug APK

Paket ini sudah disiapkan untuk di-upload ke GitHub dan dibuat menjadi **debug APK** melalui GitHub Actions.

## Status Final

Sudah dicek:
- JavaScript utama: OK
- XML Android: OK
- Workflow GitHub Actions: OK
- Tombol back bawaan HP: sudah ditangani
- Rotasi layar: tidak dikunci portrait
- Dropdown ASAL dan SPB: bisa diklik
- Kolom Sampah/Tangkai: kosong saat input baru
- Pengaman salah pencet +TAMBAH: aktif
- Thermal per rit: sudah dibuat rapi

## Cara Build APK Debug

1. Ekstrak ZIP.
2. Upload semua isi folder ini ke repository GitHub.
3. Buka tab **Actions**.
4. Pilih workflow **Build Debug APK**.
5. Klik **Run workflow**.
6. Tunggu sampai selesai.
7. Download artifact:
   `PSM-Sawit-Pantau-debug-apk`
8. Ekstrak artifact tersebut.
9. Ambil file:
   `app-debug.apk`

## File Utama

Aplikasi HTML ada di:

```text
app/src/main/assets/index.html
```

## Catatan

- Halaman 1: tarik data cloud.
- Halaman 2: lokal saja.
- PIN default: `1234`.
- Simpan gambar memakai html2canvas CDN, jadi perlu internet saat ekspor gambar.
- Print thermal RawBT perlu dites langsung di HP dengan aplikasi RawBT.
