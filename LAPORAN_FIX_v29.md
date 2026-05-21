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
