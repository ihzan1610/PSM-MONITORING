# LAPORAN FIX v28

Perbaikan 1: Thermal sekarang mode REKAPITULASI
- Tombol THERMAL tidak lagi mencetak satu slip per rit.
- Thermal sekarang menampilkan/mencetak rekap langsung:
  tanggal, sopir, T1, T2, nett, -3%, harga, total, subtotal,
  total nett, total -3%, total potongan jalan/langsir/panen,
  potongan tambahan, dan total bersih.
- Format dibuat mirip gambar rekap lama, tetapi disesuaikan untuk lebar thermal.

Perbaikan 2: Status bar / bar notifikasi
- APK tidak lagi memaksa konten masuk ke area bar notifikasi.
- WebView diberi padding status bar dan navigation bar.
- Warna status bar dibuat sesuai tema aplikasi.
- Mode ini tetap mendukung landscape.

Hasil cek:
- JavaScript syntax: OK
- index.html paket APK = HTML v28: OK
- MainActivity safe status bar: OK
- ZIP package: OK
