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
