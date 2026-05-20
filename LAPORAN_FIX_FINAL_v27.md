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
