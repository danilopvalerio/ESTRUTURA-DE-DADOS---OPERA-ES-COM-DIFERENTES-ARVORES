
public class Main {
    public static void main(String[] args) {

        ObjectManager datagenerator = new ObjectManager();
        datagenerator.generateArrays();

        // Massa de teste com números não repetidos
        int[] uniqueNumbers = datagenerator.generateUniqueRandomNumbers(1000000);
        // Massa de teste com números repetidos
        int[] repeatedNumbers = datagenerator.generateRandomNumbersWithRepetitions(1000000, 3000000);


        datagenerator.initSpr(uniqueNumbers, repeatedNumbers);
        System.out.println("Arvore Splay - Status: Finalizado.\n\n\n");
        
        datagenerator.writeMatricesToFile("Matriz_Tempo_Inserção_ArvoreSpr.txt", datagenerator.getMatrizArvore());
        datagenerator.printMatrix(datagenerator.getMatrizArvore());

        datagenerator.writeMatricesToFile("Matriz_Tempo_ConsultaAltura_ArvoreSpr.txt", datagenerator.getMatrizTempoAlturaArvore());
        datagenerator.printMatrix(datagenerator.getMatrizTempoAlturaArvore());

        datagenerator.writeMatricesToFile("Matriz_Tempo_Busca_ArvoreSpr.txt", datagenerator.getMatrizTempoBusca());
        datagenerator.printMatrix(datagenerator.getMatrizTempoBusca());

        datagenerator.writeMatricesToFile("Matriz_Valor_AlturaPorArray_ArvoreSpr.txt", datagenerator.getMatrizAlturaArvore());
        datagenerator.printMatrix(datagenerator.getMatrizAlturaArvore());

        datagenerator.writeMatricesToFile("Matriz_Rotacoes_PorArray_ArvoreSpr.txt", datagenerator.getMatrizRotArvore());
        datagenerator.printMatrix(datagenerator.getMatrizRotArvore());
        

        /* 
        datagenerator.initRn(uniqueNumbers, repeatedNumbers);
        System.out.println("Arvore Rubro Negra - Status: Finalizado.\n\n\n");

        datagenerator.writeMatricesToFile("Matriz_Tempo_Inserção_ArvoreRN.txt", datagenerator.getMatrizArvore());
        datagenerator.printMatrix(datagenerator.getMatrizArvore());

        datagenerator.writeMatricesToFile("Matriz_Tempo_ConsultaAltura_ArvoreRN.txt", datagenerator.getMatrizTempoAlturaArvore());
        datagenerator.printMatrix(datagenerator.getMatrizTempoAlturaArvore());

        datagenerator.writeMatricesToFile("Matriz_Tempo_Busca_ArvoreRN.txt", datagenerator.getMatrizTempoBusca());
        datagenerator.printMatrix(datagenerator.getMatrizTempoBusca());

        datagenerator.writeMatricesToFile("Matriz_Valor_AlturaPorArray_ArvoreRN.txt", datagenerator.getMatrizAlturaArvore());
        datagenerator.printMatrix(datagenerator.getMatrizAlturaArvore());

        datagenerator.writeMatricesToFile("Matriz_Rotacoes_PorArray_ArvoreRN.txt", datagenerator.getMatrizRotArvore());
        datagenerator.printMatrix(datagenerator.getMatrizRotArvore());



        
        datagenerator.initAvl(uniqueNumbers, repeatedNumbers);
        System.out.println("Arvore AVL - Status: Finalizado.\n\n\n");
        
        //datagenerator.writeMatricesToFile("Matriz_Tempo_Inserção_ArvoreAVL.txt",
        datagenerator.getMatrizArvore());
        datagenerator.printMatrix(datagenerator.getMatrizArvore());
        
        //datagenerator.writeMatricesToFile(
        "Matriz_Tempo_ConsultaAltura_ArvoreAVL.txt",
        datagenerator.getMatrizTempoAlturaArvore());
        datagenerator.printMatrix(datagenerator.getMatrizTempoAlturaArvore());
        
        //datagenerator.writeMatricesToFile("Matriz_Tempo_Busca_ArvoreAVL.txt",
        datagenerator.getMatrizTempoBusca());
        datagenerator.printMatrix(datagenerator.getMatrizTempoBusca());
        
        //datagenerator.writeMatricesToFile(
        "Matriz_Valor_AlturaPorArray_ArvoreAVL.txt",
        datagenerator.getMatrizAlturaArvore());
        datagenerator.printMatrix(datagenerator.getMatrizAlturaArvore());
        
        //datagenerator.writeMatricesToFile("Matriz_Rotacoes_PorArray_ArvoreAVL.txt",
        datagenerator.getMatrizRotArvore());
        datagenerator.printMatrix(datagenerator.getMatrizRotArvore());
        
        
        
        
        
        datagenerator.initAbb(uniqueNumbers,repeatedNumbers);
        System.out.println("Arvore de Busca Binária - Status: Finalizado.\n\n\n");
        
        datagenerator.writeMatricesToFile("Matriz_Tempo_Inserção_ArvoreBB.txt",
        datagenerator.getMatrizArvoreBb());
        datagenerator.printMatrix(datagenerator.getMatrizArvoreBb());
        
        datagenerator.writeMatricesToFile("Matriz_Tempo_ConsultaAltura_ArvoreBB",
        datagenerator.getMatrizTempoAlturaArvore());
        datagenerator.printMatrix(datagenerator.getMatrizTempoAlturaArvore());
        
        datagenerator.writeMatricesToFile("Matriz_Tempo_Busca_ArvoreBB",
        datagenerator.getMatrizTempoBusca());
        datagenerator.printMatrix(datagenerator.getMatrizTempoBusca());
        
        datagenerator.writeMatricesToFile("Matriz_Valor_AlturaPorArray_ArvoreBB",
        datagenerator.getMatrizAlturaArvore());
        datagenerator.printMatrix(datagenerator.getMatrizAlturaArvore());
         */

    }

}
