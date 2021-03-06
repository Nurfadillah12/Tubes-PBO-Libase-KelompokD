package TubesPBO_Libase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

// Inheritance dari class User
public class perpustakaan extends user {
    // Array Data Buku
    protected String[] data = {"Laskar Pelangi","Kambing Jantan","Marmut Merah Jambu","Ubur-Ubur Lembur","Manusia Setengah Salmon","Koala Kumal","Filosofi Kopi","Hantu Bangku Kosong","Sang Pemimpi","Orang-orang Biasa","Negeri Para Berdebah","Hujan","Rembulan Tenggelam di Wajahmu","Cinta Brontosaurus","Kasat Mata","Kumpulan Budak Setan","Lelaki Harimau","Serendipity","Anak Semu Bangsa","Jejak Langkah"};

    // Method Inheritance
    public void setNim(int n)
    {
        super.nim = n;
    }
    public void setNama(String n)
    {
        super.nama = n;
    }
    public static void insertionAsc(String array[])
    {

        // Variabel Temp
        String temp;

        // looping
        for (int i = 0; i < array.length; i++)
        {
            int j = i;
            while(j>0 && array[j-1].compareTo(array[j])>0){
                temp = array[j-1];
                array[j-1] = array[j];
                array[j] = temp;
                j--;
            }
        }
//        System.out.println("Daftar Buku yang tersedia berdasarkan Abjad (ASC): ");
        for (int i = 0; i < array.length; i++) {
            System.out.println(+ (i+1) + ". " + array[i]);
        }
    }
    // Method Insert
    public void insert(String db[], int jumlah)
    {
        // Variabel
        Scanner a = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        int total_harga=0;
        int jenis_arr[] = new int[jumlah];
        int waktu_arr[] = new int[jumlah];
        int harga_sewa_arr[] = new int[jumlah];
        int sub_harga_arr[] = new int[jumlah];
        String judul_arr[] = new String[jumlah];

        // Looping
        for (int b = 0; b < jumlah; b++)
        {
            boolean ulang = true;
            while(ulang) {
                ulang = false;
                System.out.print("\n- Masukkan Judul Buku ke-" + (b + 1) + "\t= ");
                judul_arr[b] = scanner.nextLine();
            }
            int result = sequential(db, judul_arr[b]);

            // Kondisi Pencarian Judul Buku
            if(result == -1)
            {
                System.out.println("  Buku '"+ judul_arr[b] + "' tidak ditemukan");
                b = b-1;
                ulang = true;
            }
            else
            {
//                System.out.println("Buku '" + judul_arr[b] + "' ditemukan");
                boolean ulang2 = true;
                while(ulang2)
                {
                    ulang2 = false;
                    System.out.print("  Jenis Buku\t\t\t\t= 1. E-Book | 2. Fisik \n  Masukkan Pilihan Buku\t\t= ");
                    jenis_arr[b] = a.nextInt();
                    if (jenis_arr[b] != 1 && jenis_arr[b] != 2) {
                        System.out.println("  Masukkan ulang jenis buku!");
                        ulang2 = true;
                    }
                }
                System.out.print("  Waktu Pengembalian (hari)\t= ");
                waktu_arr[b] = a.nextInt();

                // Percabangan
                if(jenis_arr[b]==1){
                    harga_sewa_arr[b] = 5000;
                    sub_harga_arr[b] = harga_sewa_arr[b] * waktu_arr[b];
                    total_harga = total_harga + sub_harga_arr[b];
                }else if(jenis_arr[b]==2){
                    harga_sewa_arr[b] = 10000;
                    sub_harga_arr[b] = harga_sewa_arr[b] * waktu_arr[b];
                    total_harga = total_harga + sub_harga_arr[b];
                }
            }

        }
        cetak(super.nama,super.nim,total_harga,jumlah,judul_arr,jenis_arr,waktu_arr,harga_sewa_arr);
    }
    // Method Cetak
    public static void cetak(String namaMHS, int nim, int total_harga, int jumlah, String judul_arr[], int jenis_arr[], int waktu_arr[], int harga_sewa_arr[])
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        System.out.println ("==========================================================");
        System.out.println ("DAFTAR BUKU YANG DIPINJAM " + sdf.format(cal.getTime()));
        System.out.println ("==========================================================");

        // Looping
        for (int b = 0;b<jumlah;b++)
        {
            System.out.println("- Judul Buku ke-" + (b+1) +"\t\t\t= " + judul_arr[b]);
            // Kondisi
            if(jenis_arr[b] == 1)
            {
                Calendar kalender = Calendar.getInstance();
                kalender.add(Calendar.DAY_OF_MONTH, + waktu_arr[b]);
                System.out.println("  Jenis Buku\t\t\t\t= E-Book");
                System.out.println("  Harga Sewa\t\t\t\t= Rp." + harga_sewa_arr[b] + " x " + waktu_arr[b] + " Hari");
                System.out.println("  Waktu Pengembalian\t\t= " + sdf.format(kalender.getTime()));
            }
            else if(jenis_arr[b] == 2)
            {
                Calendar kalender = Calendar.getInstance();
                kalender.add(Calendar.DAY_OF_MONTH, + waktu_arr[b]);
                System.out.println("  Jenis Buku\t\t\t\t= Fisik");
                System.out.println("  Harga Sewa\t\t\t\t= Rp." + harga_sewa_arr[b] + " x " + waktu_arr[b] + " Hari");
                System.out.println("  Waktu Pengembalian\t\t= " + sdf.format(kalender.getTime()));
            }
        }
        System.out.println ("==========================================================");
        System.out.println ("DATA PEMINJAM");
        System.out.println ("==========================================================");
        System.out.println ("Nama Mahasiswa\t\t\t\t= "+namaMHS);
        System.out.println ("Nim Mahasiswa\t\t\t\t= "+nim);
        System.out.println ("Total Biaya Pinjam\t\t\t= Rp."+total_harga);
    }
    // Method Sequential
    public static int sequential(String arr[], String x)
    {
        // Variabel
        int n = arr.length;

        //Looping
        for (int i = 0; i < n; i++)
        {

            //Kondisi
            if(x.toLowerCase(Locale.ROOT).equals(arr[i].toLowerCase()))
            {
                return i;
            }
        }
        return -1;
    }

    //Method GetData
    public String[] getData()
    {
        return this.data;
    }

}
