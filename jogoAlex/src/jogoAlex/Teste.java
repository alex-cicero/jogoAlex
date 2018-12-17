package jogoAlex;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Teste {
	
	    	    
	    public static void main(String[] args) throws FileNotFoundException, IOException {
	      Scanner ler = new Scanner(System.in);
	      String nome;
	      double nota1, nota2;
	      long n;
	    
	      RandomAccessFile diario = new RandomAccessFile("d:\\diario.txt", "rw");
	    
	      diario.seek(diario.length()); // posiciona o ponteiro de posição no final do arquivo
	      n = (diario.length() / 56) + 1; // número do novo registro
	      while (true) {
	        System.out.printf("%do. registro-------------------------------\n", n);
	        System.out.printf("Informe o nome do aluno, FIM para encerrar:\n");
	        nome = ler.nextLine();
	        if (nome.equalsIgnoreCase("FIM"))
	           break;
	         
	        System.out.printf("\nInforme a 1a. nota: ");
	        nota1 = ler.nextDouble();
	    
	        System.out.printf("Informe a 2a. nota: ");
	        nota2 = ler.nextDouble();
	    
	        ler.nextLine(); // esvazia o buffer do teclado
	    
	        gravarString(diario, nome, 20);
	        diario.writeDouble(nota1);
	        diario.writeDouble(nota2);
	    
	        n = n + 1;
	    
	        System.out.printf("\n");
	      }
	      diario.close();
	      ler.close();
	    }
	    
	    private static void gravarString(RandomAccessFile arq, String s, int tam) throws IOException {
	      StringBuilder result = new StringBuilder(s);
	      result.setLength(tam);
	      arq.writeChars(result.toString());
	    }

}
