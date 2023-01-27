import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;

    public static void main(String[] args) {

        int[][] lavirint;
        try {
            lavirint = ucitajMapu("C:\\Users\\Dejan\\Desktop\\Progrmming\\BackTrackLavirint\\src\\Map.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ispisiMatricu(lavirint);
        ArrayList<Koordinata> predjeniPut = new ArrayList<>();
        putanja(lavirint,predjeniPut);

    }

    public static boolean sadrzi(Koordinata k, ArrayList<Koordinata> predjeniPut){
        for (int i = 0; i < predjeniPut.size(); i++) {
            if(predjeniPut.get(i).jednaka(k)) return true;
        }
        return false;
    }

    public static void izbaci(Koordinata k, ArrayList<Koordinata> predjeniPut){
        for (int i = 0; i < predjeniPut.size(); i++) {
            if(predjeniPut.get(i).jednaka(k)){
                predjeniPut.remove(i);
            }
        }
    }
    public static boolean jeliSigurno(int[][] lavirint, Koordinata k, ArrayList<Koordinata> predjeniPut){
        if(k.x < lavirint.length && k.y < lavirint.length && k.x >= 0 && k.y >= 0 && lavirint[k.y][k.x] != 1){
            return !sadrzi(k, predjeniPut);
        }
        return false;
    }

    public static boolean pronadjiPut(int[][] lavirint, Koordinata k, ArrayList<Koordinata> predjeniPut){
        predjeniPut.add(k);
        System.out.println("Zakoracujem u " + k);
        if(lavirint[k.y][k.x] == 3){
            return true;
        }
        Koordinata[] susedi = new Koordinata[]{k.dole(), k.gore(), k.desno(), k.levo()};
        for (int i = 0; i < 4; i++) {
            if(jeliSigurno(lavirint, susedi[i], predjeniPut)){
                if(pronadjiPut(lavirint, susedi[i], predjeniPut)) return true;
            }
        }
        System.out.println("Uklanjam " + k);
        predjeniPut.remove(k);
        return false;
    }

    public static void putanja(int[][] lavirint, ArrayList<Koordinata> predjeniPut){
        Koordinata pocetnaTacka = pocetnaTacka(lavirint);
        if(pronadjiPut(lavirint, pocetnaTacka, predjeniPut)){
            //ovde ispisati predjeni put.
            System.out.println("Resenje: ");
            for (Koordinata k : predjeniPut){
                System.out.println(k);
            }
        }
        else{
            System.out.println("Nema resenja");
        }
    }

    public static int[][] ucitajMapu(String mapa) throws FileNotFoundException {

        File file = new File(mapa);
        Scanner citac = new Scanner(file);
        String dimenzija = citac.nextLine();
        N = Integer.parseInt(dimenzija);
        int[][] matrica = new int[N][N];
        for (int i = 0; i < N; i++) {
            String data = citac.nextLine();
            String[] polja = data.split(" ");
            for (int k = 0; k < N; k++) {
                matrica[i][k] = Integer.parseInt(polja[k]);
            }
        }
        citac.close();
        return matrica;
    }

    public static void ispisiMatricu(int[][] matrica){
        for (int i = 0; i < matrica.length; i++) {
            System.out.println(Arrays.toString(matrica[i]));
        }
    }

    public static Koordinata pocetnaTacka(int[][] lavirint){
        for (int i = 0; i < lavirint.length; i++) {
            for (int k = 0; k < lavirint.length; k++) {
                if(lavirint[i][k] == 2){
                    return new Koordinata(i, k);
                }
            }
        }

        return null;
    }

    public static boolean mozeDalje(int[][]lavirint, int posecen, int sledeci){
        return false;
    }


}
