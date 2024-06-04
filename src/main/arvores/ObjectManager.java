import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import arvoresAbb.ArvoreBinaria;
import arvoresAbb.No;
import arvoreAVL.*;
import arvoreSplay.*;
import arvoreRubroNegra.*;

public class ObjectManager {

    private double[][] dataTime = new double[3][5];
    private double[][] matrizAlturaArvore = new double[3][5];
    private double[][] matrizTempoAlturaArvore = new double[3][5];
    private double[][] matrizTempoBusca = new double[3][5];
    private int[] arraySizes = { 1000000, 2000000, 3000000 };
    protected int[][][] arrayOfArrays = new int[arraySizes.length][5][];

    private final String[] tiposDeArrays = { "ORDENADO CRESCENTEMENTE", "DESORDENADO", "ORDENADO INVERSAMENTE",
            "90% ORDENADO COM FINAL DESORDENADO", "90% ORDENADO COM COMECO DESORDENADO" };
    private double[][] matrizArvore = new double[3][5];
    private double[][] matrizArvoreVl = new double[3][5];
    private double[][] matrizArvoreRn = new double[3][5];
    private double[][] matrizArvoreSpr = new double[3][5];

    private int rotacoesEsquerda;
    private int rotacoesDireita;

    private String[][] matrizRotArvoreVl = new String[3][5];

    private String[][] matrizRotArvoreRn = new String[3][5];
    private String[][] matrizRotArvoreSpr = new String[3][5];

    public ObjectManager() {

    }

    public String[][] getMatrizRotArvore() {
        return matrizRotArvoreVl;
    }

    public void setMatrizTree(double[][] matriz) {
        this.matrizArvore = matriz;
    }

    public static void insertArrayOnAbb(int[] array, ArvoreBinaria tree) {
        // Buscando o array de valores individuais
        for (int k = 0; k < array.length; k++) {
            tree.inserir(array[k]);
        }
    }

    public static void insertArrayOnAvl(int[] array, AVL tree) {
        // Loop through the array of values
        for (int k = 0; k < array.length; k++) {
            // Insert each value into the AVL tree
            tree.inserir((array[k]));
        }
    }

    public static void insertArrayOnRn(int[] array, RedBlackTree tree) {
        // Loop through the array of values
        for (int k = 0; k < array.length; k++) {
            // Insert each value into the AVL tree
            tree.insert((array[k]));
        }
    }

    public static void insertArrayOnSpr(int[] array, SplayTr tree) {
        // Loop through the array of values
        for (int k = 0; k < array.length; k++) {
            // Insert each value into the AVL tree
            tree.insert((array[k]));
        }
    }


    public double[][] getMatrizAlturaArvore() {
        return matrizAlturaArvore;
    }

    public int searchValuesOnAbb(ArvoreBinaria tree, int[] array) {
        int countValoresEncontrados = 0;
        int busca = 0;
        for (int j = 0; j < array.length; j++) {
            busca = array[j];
            No resultadoBusca = tree.buscar(busca);
            if (resultadoBusca != null && resultadoBusca.item == busca) {
                countValoresEncontrados++;
            }
        }
        return countValoresEncontrados;
    }

    public int searchValuesOnAvl(AVL tree, int[] array) {
        int countValoresEncontrados = 0;
        for (int j = 0; j < array.length; j++) {
            if (tree.buscar(array[j]) != false) {
                countValoresEncontrados++;
            }
        }
        return countValoresEncontrados;
    }

    public int searchValuesOnRn(RedBlackTree tree, int[] array) {
        int countValoresEncontrados = 0;
        for (int j = 0; j < array.length; j++) {
            if (tree.search(array[j])) {
                countValoresEncontrados++;
            }
        }
        return countValoresEncontrados;
    }

    public int searchValuesOnSpr(SplayTr tree, int[] array) {
        int countValoresEncontrados = 0;
        for (int j = 0; j < array.length; j++) {
            if (tree.contains(array[j])) {
                countValoresEncontrados++;
            }
        }
        return countValoresEncontrados;
    }

    public void initAbb(int[] arrayUniqueValues, int[] arrayRepeatedValues) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 4; k++) {
                System.out.println(".");
            }
            System.out.print("_".repeat(120));
            System.out.println("\nInserido em arrays de tamanho: " + arraySizes[i]);
            // Inserindo cada array em uma árvore
            for (int j = 0; j < 5; j++) {
                System.out.print("\nArvore Binaria de Busca" + " | " + tiposDeArrays[j] + " | Tamanho: " + arraySizes[i]
                        + " | Inserindo valores...");
                ArvoreBinaria arvore = new ArvoreBinaria();
                double inicio = System.nanoTime();
                int[] array = Arrays.copyOfRange(getArrayOfArrays(i, j), 0, arraySizes[i]);
                insertArrayOnAbb(array, arvore); // Inserindo os valores do array em questão

                double inicioAltura = System.nanoTime();
                System.out.print("Buscando altura...");
                matrizAlturaArvore[i][j] = arvore.altura(arvore.getRoot());
                System.out.print("(" + matrizAlturaArvore[i][j] + ")");
                matrizTempoAlturaArvore[i][j] = (System.nanoTime() - inicioAltura) / 1000000000.0;

                double fim = System.nanoTime();
                setDataTime(i, j, ((fim - inicio) / 1000000000.0));

                // Só vai efetuar a busca quando o tamanho for 3 milhões (Consoante orientação
                // do classroom)
                if (i == 2) {
                    System.out.print("Buscando valores...");
                    double inicioBusca = System.nanoTime();
                    int uniqueValuesFoundCount = searchValuesOnAbb(arvore, arrayUniqueValues);
                    int repeatedValuesFoundCount = searchValuesOnAbb(arvore, arrayRepeatedValues);
                    double tempoBusca = System.nanoTime() - inicioBusca;
                    matrizTempoBusca[i][j] = tempoBusca / 1000000000.0;
                    System.out.println(" Finalizado\nQuantidade de valores escontrados no array de valores unicos: "
                            + uniqueValuesFoundCount);
                    System.out.println(
                            "Quantidade de encontrados no array de valores repetidos: " + repeatedValuesFoundCount);
                }

            }
            System.out.println(
                    "\nTODOS OS ARRAYS DE TAMANHO: " + arraySizes[i] + " FORAM ANALISADOS.\n" + "_".repeat(120));
        }
        setMatrizTree(getMatrizCopy(dataTime));

    }

    public void initAvl(int[] arrayUniqueValues, int[] arrayRepeatedValues) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 4; k++) {
                System.out.println(".");
            }
            System.out.print("_".repeat(120));
            System.out.println("\nInserido em arrays de tamanho: " + arraySizes[i]);
            // Inserindo cada array em uma árvore
            for (int j = 0; j < 5; j++) {
                System.out.print("\nArvore Avl" + " | " + tiposDeArrays[j] + " | Tamanho: " + arraySizes[i]
                        + " | Inserindo valores...");

                AVL arvore = new AVL();

                double inicio = System.nanoTime();
                int[] array = Arrays.copyOfRange(getArrayOfArrays(i, j), 0, arraySizes[i]);
                insertArrayOnAvl(array, arvore); // Inserindo os valores do array em questão
                rotacoesDireita = arvore.getRotacoesDireita();
                rotacoesEsquerda = arvore.getRotacoesEsquerda();
                matrizRotArvoreVl[i][j] = ("(" + rotacoesEsquerda + "," + rotacoesDireita + ")");

                double inicioAltura = System.nanoTime();
                System.out.print("Buscando altura...");
                matrizAlturaArvore[i][j] = arvore.alturaArvore();
                System.out.print("(" + matrizAlturaArvore[i][j] + ")");
                matrizTempoAlturaArvore[i][j] = (System.nanoTime() - inicioAltura) / 1000000000.0;
                System.out.println("\nRotacoes Esquerda: " + arvore.getRotacoesEsquerda() + "  -  Rotacoes Direita: "
                        + arvore.getRotacoesDireita());

                double fim = System.nanoTime();
                setDataTime(i, j, ((fim - inicio) / 1000000000.0));

                // Só vai efetuar a busca quando o tamanho for 3 milhões (Consoante orientação
                // do classroom)
                if (i == 2) {
                    System.out.print("Buscando valores...");
                    double inicioBusca = System.nanoTime();
                    int uniqueValuesFoundCount = searchValuesOnAvl(arvore, arrayUniqueValues);
                    int repeatedValuesFoundCount = searchValuesOnAvl(arvore, arrayRepeatedValues);
                    double tempoBusca = System.nanoTime() - inicioBusca;
                    matrizTempoBusca[i][j] = tempoBusca / 1000000000.0;
                    System.out.println(" Finalizado\nQuantidade de valores escontrados no array de valores unicos: "
                            + uniqueValuesFoundCount);
                    System.out.println(
                            "Quantidade de encontrados no array de valores repetidos: " + repeatedValuesFoundCount);

                }

            }
            System.out.println(
                    "\nTODOS OS ARRAYS DE TAMANHO: " + arraySizes[i] + " FORAM ANALISADOS.\n" + "_".repeat(120));
        }
        setMatrizTree(getMatrizCopy(dataTime));

    }

    public void initRn(int[] arrayUniqueValues, int[] arrayRepeatedValues) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 4; k++) {
                System.out.println(".");
            }
            System.out.print("_".repeat(120));
            System.out.println("\nInserido em arrays de tamanho: " + arraySizes[i]);
            // Inserindo cada array em uma árvore
            for (int j = 0; j < 5; j++) {
                System.out.print("\nArvore Preto-Vermelho" + " | " + tiposDeArrays[j] + " | Tamanho: " + arraySizes[i]
                        + " | Inserindo valores...");

                RedBlackTree arvore = new RedBlackTree();

                double inicio = System.nanoTime();
                int[] array = Arrays.copyOfRange(getArrayOfArrays(i, j), 0, arraySizes[i]);
                insertArrayOnRn(array, arvore); // Inserindo os valores do array em questão
                rotacoesDireita = arvore.getRotacao_dir();
                rotacoesEsquerda = arvore.getRotacao_esq();
                matrizRotArvoreVl[i][j] = ("(" + rotacoesEsquerda + "," + rotacoesDireita + ")");

                double inicioAltura = System.nanoTime();
                System.out.print("Buscando altura...");
                matrizAlturaArvore[i][j] = arvore.getHeight();
                System.out.print("(" + matrizAlturaArvore[i][j] + ")");
                matrizTempoAlturaArvore[i][j] = (System.nanoTime() - inicioAltura) / 1000000000.0;
                System.out.println("\nRotacoes Esquerda: " + rotacoesEsquerda + "  -  Rotacoes Direita: "
                        + rotacoesDireita);

                double fim = System.nanoTime();
                setDataTime(i, j, ((fim - inicio) / 1000000000.0));

                // Só vai efetuar a busca quando o tamanho for 3 milhões (Consoante orientação
                // do classroom)
                if (i == 2) {
                    System.out.print("Buscando valores...");
                    double inicioBusca = System.nanoTime();
                    int uniqueValuesFoundCount = searchValuesOnRn(arvore, arrayUniqueValues);
                    int repeatedValuesFoundCount = searchValuesOnRn(arvore, arrayRepeatedValues);
                    double tempoBusca = System.nanoTime() - inicioBusca;
                    matrizTempoBusca[i][j] = tempoBusca / 1000000000.0;
                    System.out.println(" Finalizado\nQuantidade de valores escontrados no array de valores unicos: "
                            + uniqueValuesFoundCount);
                    System.out.println(
                            "Quantidade de encontrados no array de valores repetidos: " + repeatedValuesFoundCount);

                }

            }
            System.out.println(
                    "\nTODOS OS ARRAYS DE TAMANHO: " + arraySizes[i] + " FORAM ANALISADOS.\n" + "_".repeat(120));
        }
        setMatrizTree(getMatrizCopy(dataTime));

    }

    public void initSpr(int[] arrayUniqueValues, int[] arrayRepeatedValues) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 4; k++) {
                System.out.println(".");
            }
            System.out.print("_".repeat(120));
            System.out.println("\nInserido em arrays de tamanho: " + arraySizes[i]);
            // Inserindo cada array em uma árvore
            for (int j = 0; j < 5; j++) {
                System.out.print("\nArvore Splay" + " | " + tiposDeArrays[j] + " | Tamanho: " + arraySizes[i]
                        + " | Inserindo valores...");

                SplayTr arvore = new SplayTr();

                double inicio = System.nanoTime();
                int[] array = Arrays.copyOfRange(getArrayOfArrays(i, j), 0, arraySizes[i]);
                insertArrayOnSpr(array, arvore); // Inserindo os valores do array em questão
                rotacoesDireita = arvore.getRotacao_dir();
                rotacoesEsquerda = arvore.getRotacao_esq();
                matrizRotArvoreVl[i][j] = ("(" + rotacoesEsquerda + "," + rotacoesDireita + ")");

                double inicioAltura = System.nanoTime();
                System.out.print("Buscando altura...");
                matrizAlturaArvore[i][j] = arvore.getHeight();
                System.out.print("(" + matrizAlturaArvore[i][j] + ")");
                matrizTempoAlturaArvore[i][j] = (System.nanoTime() - inicioAltura) / 1000000000.0;
                System.out.println("\nRotacoes Esquerda: " + rotacoesEsquerda + "  -  Rotacoes Direita: "
                        + rotacoesDireita);

                double fim = System.nanoTime();
                setDataTime(i, j, ((fim - inicio) / 1000000000.0));

                // Só vai efetuar a busca quando o tamanho for 3 milhões (Consoante orientação
                // do classroom)
                if (i == 2) {
                    System.out.print("Buscando valores...");
                    double inicioBusca = System.nanoTime();
                    int uniqueValuesFoundCount = searchValuesOnSpr(arvore, arrayUniqueValues);
                    int repeatedValuesFoundCount = searchValuesOnSpr(arvore, arrayRepeatedValues);
                    double tempoBusca = System.nanoTime() - inicioBusca;
                    matrizTempoBusca[i][j] = tempoBusca / 1000000000.0;
                    System.out.println(" Finalizado\nQuantidade de valores escontrados no array de valores unicos: "
                            + uniqueValuesFoundCount);
                    System.out.println(
                            "Quantidade de encontrados no array de valores repetidos: " + repeatedValuesFoundCount);

                }

            }
            System.out.println(
                    "\nTODOS OS ARRAYS DE TAMANHO: " + arraySizes[i] + " FORAM ANALISADOS.\n" + "_".repeat(120));
        }
        setMatrizTree(getMatrizCopy(dataTime));

    }


    public int[] generateUniqueRandomNumbers(int size) {
        Random random = new Random();
        Set<Integer> uniqueSet = new HashSet<>(size);
        while (uniqueSet.size() < size) {
            uniqueSet.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] uniqueNumbers = new int[size];
        int index = 0;
        for (Integer number : uniqueSet) {
            uniqueNumbers[index++] = number;
        }
        return uniqueNumbers;
    }

    public static int[] generateRandomNumbersWithRepetitions(int size, int maxValue) {
        int[] randomNumbers = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            // Gera um número aleatório entre -maxValue e maxValue (inclusive)
            randomNumbers[i] = rand.nextInt(2 * maxValue + 1) - maxValue;
        }

        return randomNumbers;
    }

    public double[][] getMatrizArvore() {
        return matrizArvore;
    }

    public void setMatrizArvore(double[][] matrizArvoreBb) {
        this.matrizArvore = matrizArvoreBb;
    }

    public double[][] getMatrizArvoreVl() {
        return matrizArvoreVl;
    }

    public void setMatrizArvoreVl(double[][] matrizArvoreVl) {
        this.matrizArvoreVl = matrizArvoreVl;
    }

    public double[][] getMatrizArvoreRn() {
        return matrizArvoreRn;
    }

    public void setMatrizArvoreRn(double[][] matrizArvoreRn) {
        this.matrizArvoreRn = matrizArvoreRn;
    }

    public double[][] getMatrizArvoreSpr() {
        return matrizArvoreSpr;
    }

    public void setMatrizArvoreSpr(double[][] matrizArvoreSpr) {
        this.matrizArvoreSpr = matrizArvoreSpr;
    }

    public int[] getArraySizes() {
        return arraySizes;
    }

    public double[][] getDataTime() {
        return dataTime;
    }

    public void setDataTime(double[][] dataTime) {
        this.dataTime = dataTime;
    }

    public void generateArrays() {
        for (int i = 0; i < this.arraySizes.length; i++) {
            arrayOfArrays[i][0] = createSortedArray(arraySizes[i]);
            // unsortedArray
            String fileUnsortedArray = "test-array-" + (i + 1) + "M-desordenado.txt";
            arrayOfArrays[i][1] = createIntArrayFromFile(fileUnsortedArray);

            // ReverseSortedArray
            arrayOfArrays[i][2] = createReverseSortedArray(arraySizes[i]);

            // PartiallySorted, unsorted at End
            String PartiallySortedArrayEnd = "test-array-" + (i + 1) + "M-desordenado-nos-10-finais.txt";
            arrayOfArrays[i][3] = createIntArrayFromFile(PartiallySortedArrayEnd);

            // PartiallySorted, unsorted at Start
            String PartiallySortedArrayStart = "test-array-" + (i + 1) + "M-desordenado-nos-10-iniciais.txt";
            arrayOfArrays[i][4] = createIntArrayFromFile(PartiallySortedArrayStart);
        }
    }

    public void setDataTime(int i, int j, double value) {
        if (i >= 0 && i < dataTime.length && j >= 0 && j < dataTime[0].length) {
            dataTime[i][j] = value;
        } else {
            // Trate aqui o caso em que os índices estão fora do alcance do array.
            System.out.println("Índices fora do alcance.");
        }
    }

    public void setArraySizes(int[] arraySizes) {
        this.arraySizes = arraySizes;
    }

    public void setSpecialElementAtArrayOfSizes(int i, int j) {
        // Implementação específica se necessário
    }

    public int[][][] getArrayOfArrays() {
        return arrayOfArrays;
    }

    public int[] getArrayOfArrays(int i, int j) {
        return arrayOfArrays[i][j];
    }

    public void setArrayOfArrays(int[][][] arrayOfArrays) {
        this.arrayOfArrays = arrayOfArrays;
    }

    public void printMatrix(double[][] matriz) {
        DecimalFormat df = new DecimalFormat("0.#############"); // Define o formato desejado

        for (int i = 0; i < matriz.length; i++) {
            System.out.print(arraySizes[i] + "-> ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + df.format(matriz[i][j]) + "] "); // Formata o valor antes de imprimir
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printMatrix(String[][] matriz) {

        System.out.println("Rotacoes:");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(arraySizes[i] + "-> ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "] "); // Formata o valor antes de imprimir
            }
            System.out.println();
        }
        System.out.println();
    }

    public double[][] getMatrizCopy(double[][] matriz) {
        double[][] copy = new double[matriz.length][];
        for (int i = 0; i < matriz.length; i++) {
            copy[i] = matriz[i].clone();
        }
        return copy;
    }

    public void writeMatricesToFile(String fileName, double[][] matriz) {
        try {
            String filePath = "resultados/" + fileName;
            FileWriter writer = new FileWriter(filePath);
            writeMatrixToFile(writer, matriz);

            writer.close();
            System.out.println("Matrize salva em " + fileName);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private void writeMatrixToFile(FileWriter writer, double[][] matrix) throws IOException {
        DecimalFormat df = new DecimalFormat("#.##########"); // Define o formato desejado aqui

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                writer.write(df.format(matrix[i][j]) + " ");
            }
            writer.write("\n");
        }
    }

    public void writeMatricesToFile(String fileName, String[][] matriz) {
        try {
            String filePath = "resultados/" + fileName;
            FileWriter writer = new FileWriter(filePath);
            writeMatrixToFile(writer, matriz);

            writer.close();
            System.out.println("Matrize salva em " + fileName);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private void writeMatrixToFile(FileWriter writer, String[][] matrix) throws IOException {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                writer.write(matrix[i][j] + " ");
            }
            writer.write("\n");
        }
    }

    public void printMatrixToFile(String fileName, double[][] matrix) {
        try {
            FileWriter writer = new FileWriter(fileName);

            // Formato para os valores double
            DecimalFormat df = new DecimalFormat("#.##########");

            // Itera sobre a matriz e escreve os valores no arquivo
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(df.format(matrix[i][j]) + " ");
                }
                writer.write("\n"); // Quebra de linha após cada linha da matriz
            }

            writer.close();
            System.out.println("Matriz foi salva em " + fileName);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static int[] createSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] createReverseSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i - 1;
        }
        return array;
    }

    public static int[] createConstantArray(int size, int constant) {
        int[] array = new int[size];
        Arrays.fill(array, constant);
        return array;
    }

    public static int[] createIntArrayFromFile(String filePath) {
        List<Integer> numeros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Parse da linha para double e converte para int
                double numeroDouble = Double.parseDouble(linha);
                int numero = (int) numeroDouble;
                numeros.add(numero);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter para int: " + e.getMessage());
        }

        // Converter o ArrayList para um array de int
        int[] arrayNumeros = new int[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            arrayNumeros[i] = numeros.get(i);
        }

        return arrayNumeros;
    }

    public double[][] getMatrizTempoBusca() {
        return matrizTempoBusca;
    }

    public void setMatrizTempoBusca(double[][] matrizTempoBusca) {
        this.matrizTempoBusca = matrizTempoBusca;
    }

    public double[][] getMatrizTempoAlturaArvore() {
        return matrizTempoAlturaArvore;
    }

    public void setMatrizTempoAlturaArvore(double[][] matrizTempoAlturaArvore) {
        this.matrizTempoAlturaArvore = matrizTempoAlturaArvore;
    }
}
