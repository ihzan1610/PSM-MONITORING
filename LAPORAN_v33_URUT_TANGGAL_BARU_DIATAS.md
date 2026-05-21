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
