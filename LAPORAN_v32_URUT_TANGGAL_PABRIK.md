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
