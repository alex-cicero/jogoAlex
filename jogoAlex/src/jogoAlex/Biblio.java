package jogoAlex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Biblio {
		
	//BIBLIOTECA DE PALAVRAS
	
	static Set<Palavra> lib = new HashSet<Palavra>();
	static Set<Palavra> s2 = new HashSet<Palavra>();
	static ArrayList<Palavra> arrayFinal = new ArrayList<Palavra>();
	 
	
	public static ArrayList <Palavra> retornaBiblioteca(){
		ArrayList<Palavra> temp1 = new ArrayList<Palavra>();
		temp1 = biblio1();
		arrayFinal.addAll(temp1);
		Set<Palavra> s = new HashSet<Palavra>();
		Iterator<Palavra> it = arrayFinal.iterator();
		while(it.hasNext()) {
			s.add(it.next());
		}
		arrayFinal.clear();
		it = s.iterator();
		while(it.hasNext()) {
			arrayFinal.add(it.next());
		}
		return arrayFinal; 
	}
	
	public static void inserePalavra(){
		Scanner sc2 = new Scanner(System.in);
		boolean fim = false;
		Palavra palavra = new Palavra(" ", " ");
		String p1, p2;
		String s = "S";
		String n = "N";
		while(fim==false) {
			System.out.println("Deseja inserir nova palavra? [S/N]");
			String c = sc2.nextLine();
			c = Utils.processaPalavra(c, 1);
			sc2.nextLine();
			if(c.equals(n)) {
				fim = true;
			}
			else{
				if(c.equals(s)) {
					fim = false;
					System.out.println("Insira a palavra: ");
					Scanner sc3 = new Scanner(System.in);
					p1 = sc3.nextLine();
					p1 = Utils.processaPalavra(p1, 0);
					sc3.nextLine();
					System.out.println("Insira a palavra-chave: ");
					p2 = sc3.nextLine();
					p2 = Utils.processaPalavra(p2, 0);
					sc3.nextLine();
					boolean teste = comparaPalavra(p1);
					if(teste == false) {
						palavra = new Palavra(p1, p2);
						arrayFinal.add(palavra);
					}
					else {
						System.out.println("Palavra já existente no jogo! ");
					}
					sc3.close();
				}				
			}
		}
		sc2.close();
	}
	
	public static void chamaInserePalavra() {
		inserePalavra();
	}
	
	public static ArrayList <Palavra> biblio1() {
		ArrayList<Palavra> tempArray = new ArrayList<Palavra>();
		tempArray = arrayFinal;
		arrayFinal.clear();
		ArrayList<Palavra> a = new ArrayList<Palavra>();
		a.add(new Palavra("GEOMETRIA", "MATEMATICA"));		
		a.add(new Palavra("ROMANTISMO", "LITERATURA"));
		a.add(new Palavra("CARTOGRAFIA", "GEOGRAFIA"));
		a.add(new Palavra("LEPTOSPIROSE", "DOENCAS"));
		a.add(new Palavra("LARANJEIRA", "ARVORE FRUTIFERA"));
		if(!tempArray.isEmpty()) {
			a.addAll(tempArray);
		}
		for(int i = 0; i<a.size(); i++) {
			s2.add(a.get(i));
		}
		a.clear();
		Iterator<Palavra> it = s2.iterator();
		while(it.hasNext()) {
			a.add((Palavra) it.next());
		}
		arrayFinal = a;
		return arrayFinal;
	}

	public static boolean comparaPalavra(String p) {
		ArrayList<Palavra> array = retornaBiblioteca();
		boolean teste = false;
		Palavra word  = new Palavra(null, null);
		for (int i = 0; i < array.size(); i++) {
			word = array.get(i);
			teste = p.equals(word.getP());
			if(teste == true) {
				return true;
			}
		}
		return teste;
	}

	public static void main(String[] args) {
		ArrayList<Palavra> c = arrayFinal;
		for (int i = 0; i < c.size(); i++) {
			System.out.println(c.get(i));
		}
	}
	
//SALVAR NUM ARQUIVO
	//DIRETORIO: C:\Users\Alex\eclipse-workspace\jogoAlex
	
	public static void saveWord(){
		
		List<Palavra> array = retornaBiblioteca();
		Iterator<Palavra> it = array.iterator();
		Palavra p;
		String wordFile, wordArray;
		boolean teste  = false;
		
	    try {
	    	FileReader arq = new FileReader("d:\\palavras.txt");
	    	BufferedReader lerArq = new BufferedReader(arq);
   
	    	String linha = lerArq.readLine(); // lê a primeira linha
	    	while (linha != null) {
	    		System.out.printf("%s\n", linha);
	    		while(it.hasNext()) {
	    			p = it.next();
	    			wordArray = p.toString();
	    			wordFile = linha;
	    			if(wordArray.equals(wordFile)) {
	    				teste = true;
	    				break;
	    			}
	    		}
	    		linha = lerArq.readLine(); // lê da segunda até a última linha
	    	}
    
	    	arq.close();
       } catch (IOException e) {
          System.out.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
       }
       System.out.println();
    }
	
	public static ArrayList<String> lendoFile(){
		ArrayList<String> array = new ArrayList<String>();
		String str = new String();
		Palavra p;
		String[] line = new String();
		
		
		try {
	    	FileReader arq = new FileReader("d:\\palavras.txt");
	    	BufferedReader lerArq = new BufferedReader(arq);
   
	    	String linha = lerArq.readLine(); // lê a primeira linha
	    	while (linha != null) {
	    		
	    		System.out.printf("%s\n", linha);
	    		str = linha;
	    		str = str.trim();
	    		str = str.toUpperCase();
	    		if(str.endsWith("%n")) {
	    			str = str.substring(0, str.length() - 2 );
	    		}
	    		str = str.
	    		linha = lerArq.readLine(); // lê da segunda até a última linha
	    	}
    
	    	arq.close();
	    	return array;
	    			
       } catch (IOException e) {
          System.out.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
       }
       
	    System.out.println();
       if(array.isEmpty() == false) {
    	   return array;
       }
       else {
    	   return null;
       }
	
    }
	
	public static void salvandoNumArquivo() throws FileNotFoundException, IOException {
		
	      double nota1, nota2;
	      long n;
	      
		  List<Palavra> array = retornaBiblioteca();
		  Iterator<Palavra> it = array.iterator();
		  Palavra p;
		  String wordFile, wordArray;
		  boolean teste  = false;
	    
	      RandomAccessFile archive = new RandomAccessFile("d:\\palavras.txt", "rw");
	    
	      archive.seek(archive.length()); // posiciona o ponteiro de posição no final do arquivo
	      n = (archive.length() / 56) + 1; // número do novo registro
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
	    }
	    
	    private static void gravarString(RandomAccessFile arq, String s, int tam) throws IOException {
	      StringBuilder result = new StringBuilder(s);
	      result.setLength(tam);
	      arq.writeChars(result.toString());
	    }
	

	}
