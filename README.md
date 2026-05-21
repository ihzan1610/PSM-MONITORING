# PSM Sawit Pantau - APK GitHub v33

# LAPORAN v33 - URUT TANGGAL BARU DI ATAS

Perbaikan:
- Data lembar kedua / data pabrik sekarang otomatis diurutkan berdasarkan tanggal pabrik terbaru di atas.
- Urutan memakai TANGGAL + JAM pabrik.
- Tanggal baru tampil di atas, tanggal lama di bawah.
- Kalau tanggal dan jam sama, data yang dibuat/diinput lebih baru tampil lebih atas.
- Urutan diterapkan saat:
  1. aplikasi dibuka,
  2. data baru disimpan,
  3. data diedit,
  4. restore JSON/CSV/Excel,
  5. tabel dan rekap ditampilkan.

Contoh:
- 22/05/26
- 21/05/26
- 20/05/26

Hasil cek:
- JavaScript syntax: OK
- Paket ZIP: OK


# PSM Sawit Pantau - APK GitHub v32

# LAPORAN v32 - URUT OTOMATIS TANGGAL PABRIK

Perbaikan:
- Data lembar kedua / data pabrik sekarang otomatis diurutkan berdasarkan tanggal pabrik.
- Urutan memakai TANGGAL + JAM pabrik.
- Tanggal lama tampil di atas, tanggal baru di bawah.
- Kalau tanggal dan jam sama, urutan memakai createdAt sebagai pembeda.
- Urutan diterapkan saat:
  1. aplikasi dibuka,
  2. data baru disimpan,
  3. data diedit,
  4. restore JSON/CSV/Excel,
  5. tabel dan rekap ditampilkan.

Catatan:
- Tombol sort manual di tabel pembayaran tetap bisa dipakai.
- Data yang sudah lama di localStorage akan otomatis dirapikan saat aplikasi dibuka.

Hasil cek:
- JavaScript syntax: OK
- Paket ZIP: OK


# PSM Sawit Pantau - APK GitHub v31

# LAPORAN v31 - POTONGAN KOPERASI JALWOY/JLY

Perbaikan hitungan khusus SPB KOPERASI JLY / KOPERASI JALWOY:
- Bruto = Harga x Nett Akhir
- PPH = 0,25% x Bruto
- Potongan koperasi = Rp 30 x Nett Akhir
- Total diterima = Bruto - PPH - Potongan koperasi

Hitungan ini diterapkan ke:
- Tabel pembayaran koperasi/cash
- Rekap pembayaran
- Data timbangan & laba
- Rekap timbangan
- Summary penjualan
- Dashboard laba
- Grafik tren penjualan
- Export CSV semua buah keluar

Catatan:
- Potongan ini hanya aktif jika SPB mengandung KOPERASI dan JLY/JALWOY/JALWAY/JALUR.
- SPB lain tetap memakai total biasa: harga x nett akhir.

Hasil cek:
- JavaScript syntax: OK
- Paket ZIP: OK


# PSM Sawit Pantau - APK GitHub v30

# LAPORAN v30 - RESTORE EXCEL ASLI

Perbaikan:
- Restore lembar kedua sekarang bisa langsung dari file Excel asli:
  .xlsx dan .xls
- Tidak perlu simpan Excel menjadi CSV dulu.
- File CSV tetap didukung.
- File JSON tetap didukung.
- Tambah tombol template Excel asli (.xlsx).
- Kolom minimal tetap:
  tgl, supir, asal, spb, b1, b2

Catatan:
- Pembaca Excel memakai library SheetJS dari CDN.
- Jadi untuk restore .xlsx/.xls, internet perlu aktif saat aplikasi dibuka agar library Excel termuat.
- Jika offline, tetap bisa pakai restore CSV/JSON.

Hasil cek:
- JavaScript syntax: OK
- Paket ZIP: OK


# PSM Sawit Pantau - APK GitHub v29

# LAPORAN FIX v29 - Restore Excel/CSV Buah Keluar

Fitur baru:
- Restore data lembar kedua sekarang bisa dari JSON atau Excel/CSV.
- Tombol DOWNLOAD TEMPLATE EXCEL / CSV ditambahkan di menu Backup lembar kedua.
- File Excel cukup dibuat/diisi lalu disimpan sebagai CSV dari Excel.
- Kolom minimal untuk restore CSV: tgl, supir, asal, spb, b1, b2.
- Kolom tambahan didukung: jam, sampah, tangkai, sortasi, harga, ongkos, gaji, lunas, tgl_bayar.
- Saat restore, aplikasi bertanya mode: TIMPA atau GABUNG.
- Rumus otomatis saat import:
  - nett = abs(b1-b2)
  - CASH JLY: sortasi = 2*sampah + 2*tangkai jika sortasi kosong
  - KOPERASI: sortasi = 3.5% nett jika sortasi kosong
  - nett_akhir = nett - sortasi

Hasil cek:
- JavaScript syntax: OK
- Runtime parser CSV: OK
- Paket ZIP: OK


# PSM Sawit Pantau - APK GitHub v28

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
