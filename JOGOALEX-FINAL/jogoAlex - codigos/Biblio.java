package jogoAlex;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Biblio {
		
	//BIBLIOTECA DE PALAVRAS
	
//	private static Set<Palavra> s2 = new HashSet<Palavra>();
	private static ArrayList<Palavra> arrayFinal = new ArrayList<Palavra>();
	 
	public static ArrayList <Palavra> retornaBiblioteca(){
		ArrayList<Palavra> temp1 = new ArrayList<Palavra>();
	//	temp1 = biblio1(); //deverá receber o método de leitura de arquivo;
		arrayFinal.addAll(temp1);
		temp1 = retornaArrayFile();
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
	
	public static void inserePalavra(Scanner sc){
		boolean fim = false;
		Palavra palavra = new Palavra(" ", " ");
		String p1 = new String(); 
		String p2 = new String();
		String c = new String();
		while(fim==false) {
			System.out.println("Deseja inserir nova palavra? [S/N]");
			if(sc.hasNext()) {
				c = sc.next();
			}
			c = Utils.processaPalavra(c, 1);
			if(c.equals("N")) {
				fim = true;
				break;
			}
			else{
				if(c.equals("S")) {
					fim = false;
					System.out.println("Insira a palavra: ");
					if(sc.hasNext()) {
						p1 = sc.next();
					}						
					p1 = Utils.processaPalavra(p1, 0);
					System.out.println("Insira a palavra-chave: ");
					if(sc.hasNext()) {
						p2 = sc.next();	
					}
					p2 = Utils.processaPalavra(p2, 0);
					boolean teste = comparaPalavra(p1);
					if(teste == false) {
						palavra.setP(p1);
						palavra.setPkey(p2);
						arrayFinal.add(palavra);
						try {
							salvaPalavra(palavra);
						}
						catch (IOException e) {
							e.getMessage();
							e.printStackTrace();
						}
					}
					else {
						System.out.println("Palavra jah existente no jogo! ");
					}
				}				
			}
		}
	}
	
	public static void chamaInserePalavra(Scanner sc) {
		inserePalavra(sc);
	}
	
//	public static ArrayList <Palavra> biblio1() {
//		ArrayList<Palavra> tempArray = new ArrayList<Palavra>();
//		tempArray = arrayFinal;
//		arrayFinal.clear();
//		ArrayList<Palavra> a = new ArrayList<Palavra>();
//		a.add(new Palavra("GEOMETRIA", "MATEMATICA"));		
//		a.add(new Palavra("ROMANTISMO", "LITERATURA"));
//		a.add(new Palavra("CARTOGRAFIA", "GEOGRAFIA"));
//		a.add(new Palavra("LEPTOSPIROSE", "DOENCAS"));
//		a.add(new Palavra("LARANJEIRA", "ARVORE FRUTIFERA"));
	/*
	array.add(new Palavra("GEOMETRIA", "MATEMATICA"));		
	array.add(new Palavra("ROMANTISMO", "LITERATURA"));
	array.add(new Palavra("CARTOGRAFIA", "GEOGRAFIA"));
	array.add(new Palavra("LEPTOSPIROSE", "DOENCAS"));
	array.add(new Palavra("LARANJEIRA", "ARVORE FRUTIFERA"));
	
GEOMETRIA#MATEMATICA
ROMANTISMO#LITERATURA
CARTOGRAFIA#GEOGRAFIA
LEPTOSPIROSE#DOENCAS
LARANJEIRA#ARVORE FRUTIFERA
	
	 */
//		if(!tempArray.isEmpty()) {
//			a.addAll(tempArray);
//		}
//		for(int i = 0; i<a.size(); i++) {
//			s2.add(a.get(i));
//		}
//		a.clear();
//		Iterator<Palavra> it = s2.iterator();
//		while(it.hasNext()) {
//			a.add((Palavra) it.next());
//		}
//		arrayFinal.addAll(a);
//		return arrayFinal;
//	}

	public static boolean comparaPalavra(String p) {
		ArrayList<Palavra> array = retornaBiblioteca();
		boolean teste = false;
		for (int i = 0; i < array.size(); i++) {
			Palavra word = array.get(i);
			teste = p.equals(word.getP());
			if(teste == true) {
				return true;
			}
		}
		return teste;
	}

	public static void main(String[] args) {
		ArrayList<Palavra> c = retornaBiblioteca();
		
		for (int i = 0; i < c.size(); i++) {
			System.out.println(c.get(i));
		}
		
	}
	
	public static List<String[]> chamaListFile() {
		List<String[]> lista = new ArrayList<>();    
	    try {

	            FileReader fr = new FileReader("d:\\palavras.txt");
	            BufferedReader br = new BufferedReader(fr);

	            String str;
	            while((str = br.readLine()) != null){
	                lista.add(str.split("#"));
	            } 
	            
	            br.close();
	     } catch(IOException e) {
	           System.out.println("Arquivo não encontrado!");
	     }
	   //  lista.forEach(a -> System.out.println(Arrays.toString(a)));
	     if(lista.isEmpty() == false) {
	    	 return lista;
	     }
	     else return null;
	}
	
	public static ArrayList<Palavra> retornaArrayFile() {
		List <String[]> lista = chamaListFile();
		ArrayList <Palavra> array = new ArrayList<Palavra>();
		String str = new String();
		String str1 = new String(), str2 = new String();
		char[] vetor; 
		char ch1;
		String s3;

		for(int i = 0; i < lista.size(); i++) {
			str = Arrays.toString(lista.get(i)); 
			//transformando o objeto de list em string;
			vetor = str.toCharArray(); 
			//transformando string em vetor de caracteres
			
			ch1 = Character.valueOf('\n');
			s3 = String.valueOf(ch1);
		
			if((str.equals("#")) || (str.equals(s3)) ) {
				continue;
			}
			
			ch1 = vetor[0];

			for(int j = 0; j < vetor.length; j++) { 

				//aqui, o vetor e tratado de forma que fique possível a atribuicao
				//das strings em um objeto de classe Palavra;
				if((vetor[j] == '[' || vetor[j]== ']') || ((vetor[j] == ',') )) {
					vetor[j] = ' ';
				}
			}
			
//			for(int j = 0; j < vetor.length; j++) {
//				System.out.println(vetor[j]);
//			}
			
			str = String.valueOf(vetor);
			str = str.trim();
			
		//	System.out.println("1:" + str);
			
		//	vetor = str.toCharArray();
					
			ch1 = vetor[0];
		//	int empecilho = (Character.getNumericValue(ch1));// retorna -1
		//	char caracter = Character.valueOf((char) empecilho);
		//	System.out.println(caracter);
		
//		for(int k = 0; k < vetor.length; k++) {
//			if(Character.isAlphabetic(vetor[k])) {
//				System.out.println(vetor[k]);
//			}
//		}
			
		Map<Integer, Character> string = new HashMap<Integer, Character>();
		int tam = 0;
		for(int k = 0; k < vetor.length; k++) {
			if((Character.isLetter(vetor[k]) || vetor[k] == ' ') || vetor[k] == '-' ) {
			//	System.out.print(vetor[k] + "-");
				ch1 = vetor[k];
				string.put(tam, ch1);
				tam++;
			}
		}
		
		for (int j = 0; j < vetor.length; j++) {
			vetor[j] = ' ';
		}
		
		for(int k = 0; k < string.size(); k++) {
			ch1 = string.get(k);
			vetor[k] = ch1;
		}
		
//		for(int k = 0; k < vetor.length; k++) {
//			System.out.println(vetor[k]);
//		}
		
		str = String.valueOf(vetor);

		//System.out.println(str);
		
		str = str.trim();
		
//		System.out.println(str);
	//	System.out.println("\n******************");
			
		String s = String.valueOf(' ');

		String[] listaStr = str.split(s, 3);

			
			for(int j = 0; j < listaStr.length; j++) {
				//System.out.println(listaStr[j]);
				str1 = listaStr[0];
				str2 = listaStr[listaStr.length - 1];
				str1 = str1.trim();
				str2 = str2.trim();
			}
			
			if((!str1.isEmpty()) && (!str2.isEmpty())) {
				Palavra p = new Palavra(str1, str2);
				array.add(p);
			}
			
		}
		if(array.isEmpty()) {
			return null;
		}
		else {
			return array;
		}
		
	}
	
	public static void salvaPalavra(Palavra p) throws IOException {
		try {
			saveWordsFile(p);
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void saveWordsFile (Palavra p) throws FileNotFoundException, IOException {
		
		String str = "\n" + p.toString() + "\n";             
		RandomAccessFile arq = new RandomAccessFile("d:\\palavras.txt", "rw");           
		arq.seek(arq.length()); 
		// posiciona o ponteiro de posição no final do arquivo       
		gravarString(arq, str, str.length());
		System.out.println("Palavra salva com sucesso!");
		arq.close();
		
	}        
	
	private static void gravarString(RandomAccessFile arq, String s, int tam) throws IOException {
		StringBuilder result = new StringBuilder(s);
		result.setLength(tam);
		arq.writeChars(result.toString());
	}
		
}