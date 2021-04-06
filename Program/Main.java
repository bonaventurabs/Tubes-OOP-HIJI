/**
 * Main/java
 * Driver Game HIJI
 */

import java.util.*;
//import kartu.*;

public class Main {
    public static void printLogo() {
        System.out.println("                  .----------------.  .----------------.  .----------------.  .----------------.  ");
        System.out.println("                  | .--------------. || .--------------. || .--------------. || .--------------. |");
        System.out.println("                  | |  ____  ____  | || |     _____    | || |     _____    | || |     _____    | |");
        System.out.println("                  | | |_   ||   _| | || |    |_   _|   | || |    |_   _|   | || |    |_   _|   | |");
        System.out.println("                  | |   | |__| |   | || |      | |     | || |      | |     | || |      | |     | |");
        System.out.println("                  | |   |  __  |   | || |      | |     | || |   _  | |     | || |      | |     | |");
        System.out.println("                  | |  _| |  | |_  | || |     _| |_    | || |  | |_' |     | || |     _| |_    | |");
        System.out.println("                  | | |____||____| | || |    |_____|   | || |  `.___.'     | || |    |_____|   | |");
        System.out.println("                  | |              | || |              | || |              | || |              | |");
        System.out.println("                  | '--------------' || '--------------' || '--------------' || '--------------' |");
        System.out.println("                  '----------------'  '----------------'  '----------------'  '----------------' "); 
        System.out.println();      
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) {

        new Deck();
        new Scan();

        // Pesan selamat datang
        clearScreen();
        printLogo();
        System.out.println("Selamat datang di game HIJI");

        // Input ada berapa player
        System.out.println("Silakan input banyaknya pemain!");
        System.out.print("Banyak pemain: ");

        //Scanner scanMain = new Scanner(System.in);
        //int playerNum= scanMain.nextInt();
        int playerNum = Scan.intScanner();

        System.out.println();

        // Kalo jumlah player ga 2-6
        while (playerNum<2||playerNum>6){
            System.out.println("Banyak pemain hanya bisa 2-6. Silakan ulangi input banyaknya pemain!");
            System.out.print("Banyak pemain: ");
            //playerNum = scanMain.nextInt();
            playerNum = Scan.intScanner();
            
            System.out.println();
        }

        // Input nama-nama player
        System.out.println("Silakan input nama-nama pemain!");
        //scanMain.nextLine();
        List<Player> playerList = new ArrayList<Player>();
        String name;
        for (int i=1; i<=playerNum; i++){
            System.out.print("Nama pemain "+i+": ");
            //name = scanMain.nextLine();
            name = Scan.strInput();
            playerList.add(new Player(name));
        }   
        System.out.println();

        // Looping game

        int pilihan=0;
        // Start game
        while (pilihan!=1){
            // Output pilihan menu

            System.out.println("Pilihan menu:");
            System.out.println("1. Start game");
            System.out.println("2. List card");
            System.out.println("3. Discard");
            System.out.println("4. Draw");
            System.out.println("5. Declare HIJI");
            System.out.println("6. List Player");
            System.out.println("7. View Player in Turn");
            System.out.println("8. Help");

            System.out.print("Input pilihan: ");
            //pilihan = scanMain.nextInt();
            pilihan = Scan.intScanner();
            if (pilihan==8){
                System.out.println("Peraturan Permainan:");
                System.out.println("1) HIJI dimainkan oleh 2-6 pemain.");
                System.out.println("2) Di awal permainan, semua pemain akan mendapatkan 7 buah kartu, dan satu kartu angka dipilih secara acak untuk dijadikan kartu awal.");
                System.out.println("3) Pemain yang akan memulai giliran pertama akan diacak.");
                System.out.println("4) Aturan permainan adalah sebagai berikut.");
                System.out.println("a) Pada setiap giliran, pemain boleh mengeluarkan satu atau lebih kartu yang dapat dimainkan pada giliran tersebut. Ketentuan kartu apa saja yang boleh dikeluarkan ada pada tabel Jenis Kartu.");
                System.out.println("b) Apabila pemain tidak mengeluarkan kartu, pemain wajib mengambil satu kartu dari deck.");
                System.out.println("c) Apabila kartu yang baru diambil tersebut bisa dikeluarkan, pemain boleh mengeluarkan kartu tersebut (tidak wajib).");
                System.out.println("d) Apabila kartu tersebut tidak dapat dimainkan, maka giliran diselesaikan tanpa mengeluarkan kartu.");
                System.out.println("5) Beberapa jenis kartu memiliki power tertentu yang dapat memengaruhi jalannya permainan.");
                System.out.println("a) Reverse Card: memutar urutan permainan");
                System.out.println("b) Skip Card: melompati giliran player selanjutnya");
                System.out.println("c) Wildcard: memilih warna selanjutnya");
                System.out.println("d) Draw Two Card: player selanjutnya wajib mengambil 2 kartu jika tidak membalas");
                System.out.println("e) Draw Four Card: memilih warna selanjutnya dan player selanjutnya wajib mengambil 4 kartu");
                System.out.println("6) Apabila pemain memiliki sisa satu kartu, maka pemain harus melakukan \"Declare HIJI\" dalam waktu 3 detik. Apabila tidak, pemain wajib mengambil dua kartu dari deck.");
                System.out.println("7) Pemain dinyatakan menang apabila kartu yang dipegangnya sudah habis, dan permainan selesai.");
            }
            else if (pilihan!=1){
                System.out.println("Kamu belum start game");
            }
        }
        clearScreen();
        printLogo();
        System.out.println("Game HIJI dimulai!");
        Game game = new Game(playerList);
        game.startGame();


        while(game.getWinner().equals("_NO_WINNER_")){
            
            if (game.getNextTurn()) {

                promptEnterKey();
                clearScreen();
                printLogo();
                game.resetNextTurn();
            }

            System.out.println();
            System.out.println("Saat ini giliran: "+game.getPlayerInTurn().getNama());
            System.out.println("Kartu di discard pile: "+Deck.getDiscardPile().toString());

            // Output pilihan menu

            System.out.println("Pilihan menu:");
            System.out.println("1. List card");
            System.out.println("2. Discard");
            System.out.println("3. Draw");
            System.out.println("4. Declare HIJI");
            System.out.println("5. List Player");
            System.out.println("6. View Player in Turn");
            System.out.println("7. Help");

            System.out.print("Input pilihan: ");
            //pilihan = scanMain.nextInt();
            pilihan = Scan.intScanner();

            if (game.getDeclareHIJICommand()){
                game.startTimerHIJI();
            }

            switch (pilihan){
                // List cards
                case 1:
                    game.getPlayerInTurn().cardList();
                    break;
                // Discard
                case 2:
                    game.playerDiscard();
                    break;
                // Draw
                case 3:
                    game.playerDraw();
                    break;
                // Declare HIJI
                case 4:
                    game.playerDeclareHIJI();
                    break;
                // List player
                case 5:
                    game.printPlayerList();
                    break;
                // View player in turn
                case 6:
                    game.printPlayerInTurn();
                    break;
                // Help
                case 7:
                    System.out.println("Peraturan Permainan:");
                    System.out.println("1) HIJI dimainkan oleh 2-6 pemain.");
                    System.out.println("2) Di awal permainan, semua pemain akan mendapatkan 7 buah kartu, dan satu kartu angka dipilih secara acak untuk dijadikan kartu awal.");
                    System.out.println("3) Pemain yang akan memulai giliran pertama akan diacak.");
                    System.out.println("4) Aturan permainan adalah sebagai berikut.");
                    System.out.println("a) Pada setiap giliran, pemain boleh mengeluarkan satu atau lebih kartu yang dapat dimainkan pada giliran tersebut. Ketentuan kartu apa saja yang boleh dikeluarkan ada pada tabel Jenis Kartu.");
                    System.out.println("b) Apabila pemain tidak mengeluarkan kartu, pemain wajib mengambil satu kartu dari deck.");
                    System.out.println("c) Apabila kartu yang baru diambil tersebut bisa dikeluarkan, pemain boleh mengeluarkan kartu tersebut (tidak wajib).");
                    System.out.println("d) Apabila kartu tersebut tidak dapat dimainkan, maka giliran diselesaikan tanpa mengeluarkan kartu.");
                    System.out.println("5) Beberapa jenis kartu memiliki power tertentu yang dapat memengaruhi jalannya permainan.");
                    System.out.println("a) Reverse Card: memutar urutan permainan");
                    System.out.println("b) Skip Card: melompati giliran player selanjutnya");
                    System.out.println("c) Wildcard: memilih warna selanjutnya");
                    System.out.println("d) Draw Two Card: player selanjutnya wajib mengambil 2 kartu jika tidak membalas");
                    System.out.println("e) Draw Four Card: memilih warna selanjutnya dan player selanjutnya wajib mengambil 4 kartu");
                    System.out.println("6) Apabila pemain memiliki sisa satu kartu, maka pemain harus melakukan \"Declare HIJI\" dalam waktu 3 detik. Apabila tidak, pemain wajib mengambil dua kartu dari deck.");
                    System.out.println("7) Pemain dinyatakan menang apabila kartu yang dipegangnya sudah habis, dan permainan selesai.");
                    break;
            }
            
        }

        System.out.println("Selamat! HIJI dimenangkan oleh: "+game.getWinner());
        System.out.println("Terima kasih telah bermain.");
        Scan.closeScanner();
    }
    public static void promptEnterKey(){
        System.out.println("Tekan enter untuk pindah giliran.");
        //Scanner scanner = new Scanner(System.in);
        //scanner.nextLine();
        Scan.strInput();
        //scanner.close();
    }
}
