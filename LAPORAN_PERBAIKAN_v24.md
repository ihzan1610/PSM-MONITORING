# LAPORAN PERBAIKAN v24

Masalah yang diperbaiki:
1. Tombol SIMPAN GAMBAR di APK tidak berfungsi
   - Ditambahkan Android JS Bridge: saveBase64File().
   - HTML sekarang memanggil saveDataUrlNativeOrDownload().
   - Hasil gambar disimpan ke folder Download HP.

2. Header thermal terlalu banyak
   - Duplikat logo di preview thermal dihapus.
   - Setiap struk thermal sekarang hanya menampilkan 1 logo header.

3. Thermal rekap tidak punya potongan tambahan
   - Potongan tambahan sekarang tampil di preview thermal.
   - Potongan tambahan juga ikut tercetak di RawBT thermal.
   - Potongan tambahan dicetak sebagai rekap tambahan, bukan dobel di setiap rit.

4. Gambar/rekap pabrik
   - dlImgPabrik juga diarahkan ke native save di APK.

Hasil cek:
- JavaScript syntax: OK
- Native save marker: OK
- Thermal potongan tambahan: OK

Catatan tes langsung:
- Simpan gambar perlu dites di APK HP karena bergantung izin Download Android.
- Logo thermal bitmap perlu dites langsung dengan printer RawBT.
