import java.util.Arrays;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayCreator {


    public static double[] createSortedArray(int size) {
        Random random = new Random();
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {//2147483038
            array[i] = random.nextDouble((5000)-1000);
        }
        Arrays.sort(array);
        return array;
    }

    public static double[] createReverseSortedArray(int size) {
        Random random = new Random();
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextDouble();
        }
        Arrays.sort(array);
        reverseArray(array);
        return array;
    }

    private static void reverseArray(double[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            double temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    public static double[] createConstantArray(int size, int constant) {
        double[] array = new double[size];
        Arrays.fill(array, constant);
        return array;
    }

    public static double[] createDoubleArrayFromFile(String filePath) {
        List<Double> numeros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Parse da linha para double e adiciona ao ArrayList
                double numero = Double.parseDouble(linha);
                numeros.add(numero);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter para double: " + e.getMessage());
        }

        // Converter o ArrayList para um array de double
        double[] arrayNumeros = new double[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            arrayNumeros[i] = numeros.get(i);
        }

        return arrayNumeros;
    }

}
