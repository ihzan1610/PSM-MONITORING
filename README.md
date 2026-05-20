# PSM Sawit Pantau - APK GitHub v27 FINAL

# LAPORAN FIX FINAL v27

Perbaikan yang benar-benar dimasukkan ke HTML dan APK package:

1. Tombol SIMPAN GAMBAR di APK
   - HTML memakai saveDataUrlNativeOrDownload().
   - APK sekarang punya Android Bridge chunked:
     beginBase64File()
     appendBase64Chunk()
     finishBase64File()
   - Ini lebih aman untuk gambar besar karena base64 tidak dikirim sekaligus.
   - File disimpan ke folder Download HP.

2. Header thermal terlalu banyak
   - Logo di dalam setiap thermal-slip dihapus.
   - Preview thermal sekarang hanya punya 1 logo di bagian atas.
   - Print RawBT juga hanya mengirim 1 logo bitmap di bagian atas job cetak.

3. Potongan tambahan di thermal
   - buildThermalExtraHTML() tampil di preview thermal.
   - printT() mencetak POTONGAN TAMBAHAN REKAPAN.
   - Edit/hapus potongan tambahan sekarang refresh sesuai mode aktif: gambar atau thermal.

4. Lembar kedua
   - Auto-fill harga, ongkos truk, dan gaji supir berdasarkan ASAL + SPB tetap ada.
   - Posisi tombol < + SIMPAN/UPDATE HAPUS > dibuat tetap.
   - Baris sampah/tangkai disembunyikan tapi ruangnya tetap disediakan saat SPB bukan CASH JLY.

Hasil cek otomatis:
- JavaScript syntax: OK
- index.html dalam paket APK = HTML final v27: OK
- MainActivity bridge simpan gambar chunked: OK
- Workflow GitHub APK: OK


# PSM Sawit Pantau - APK GitHub v26

# LAPORAN PERBAIKAN v26

Perbaikan:
- Di lembar kedua, posisi tombol navigasi dan aksi: < + SIMPAN/UPDATE HAPUS > dibuat tetap.
- Baris SAMPah/TANGKAI untuk CASH JLY tidak lagi membuat tombol naik turun.
- Saat SPB bukan CASH JLY, baris sampah/tangkai disembunyikan tetapi ruangnya tetap disediakan.
- Helper rupiah di harga/ongkos/gaji diberi tinggi minimal agar tombol tidak loncat saat teks Rp muncul/hilang.

Hasil cek:
- JavaScript syntax: OK
- Paket ZIP: OK


# PSM Sawit Pantau - APK GitHub v25

# LAPORAN PERBAIKAN v25

Fitur baru lembar kedua:
- Harga jual pabrik, ongkos truk/kg, dan gaji supir truk/kg sekarang mengingat input terakhir.
- Ingatan berdasarkan kombinasi ASAL + SPB.
- Saat tambah data baru, setelah memilih/mengetik ASAL dan SPB, angka otomatis terisi.
- Angka tetap bisa diedit manual sebelum disimpan.
- Setelah data disimpan, nilai terbaru menjadi referensi berikutnya.

Urutan pengisian:
1. Pilih/ketik ASAL.
2. Pilih/ketik SPB.
3. Harga, ongkos, dan gaji akan otomatis terisi jika sebelumnya pernah ada kombinasi ASAL + SPB tersebut.
4. Kalau belum ada kombinasi sama persis, aplikasi fallback memakai riwayat SPB terakhir.

Hasil cek:
- JavaScript syntax: OK
- Marker fitur auto-fill tarif: OK


# PSM Sawit Pantau - APK GitHub v24

Perbaikan utama:
- Tombol SIMPAN GAMBAR di APK diperbaiki dengan Android JavaScript Bridge.
- File gambar/CSV disimpan ke folder Download HP.
- Header thermal yang dobel sudah diperbaiki.
- Potongan tambahan rekap tampil di preview thermal dan ikut tercetak RawBT.
- Logo thermal bitmap tetap aktif.

Cara build:
1. Upload semua isi ZIP ke repository GitHub.
2. Buka tab Actions.
3. Jalankan workflow Build Debug APK.
4. Download artifact PSM-Sawit-Pantau-debug-apk-v24.
5. Install app-debug.apk di HP.
