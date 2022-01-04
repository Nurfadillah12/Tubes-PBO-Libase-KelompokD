package TubesPBO_Libase;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        perpustakaan perpus = new perpustakaan();
        Scanner a = new Scanner(System.in);
        int nim, jumlah;
        String namaMHS;
        System.out.println("==== Selamat Datang di Peminjaman Buku (Perpustakaan) ====");
        String[] db = perpus.getData();
        perpus.insertionAsc(db);
        System.out.println ("\n===========================================================");
        System.out.println ("INPUT DATA PEMINJAM");
        System.out.println ("===========================================================");
        System.out.print   ("Masukkan Nama\t\t\t\t= ");
        namaMHS = a.nextLine();
        perpus.setNama(namaMHS);
        System.out.print   ("Masukkan Nim\t\t\t\t= ");
        nim = a.nextInt();
        perpus.setNim(nim);
        System.out.print   ("Masukkan Jumlah Buku\t\t= ");
        jumlah = a.nextInt();
        perpus.insert(db,jumlah);
    }
}
