package jogoAlex;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Biblio {
		
	//BIBLIOTECA DE PALAVRAS
	
	static Set<Palavra> lib = new HashSet<Palavra>();
	static Set<Palavra> s2 = new HashSet<Palavra>();
	static ArrayList<Palavra> arrayFinal = new ArrayList<Palavra>();
	 
	public static ArrayList <Palavra> retornaBiblioteca(){
		ArrayList<Palavra> temp1 = new ArrayList<Palavra>();
		temp1 = biblio1(); //deverá receber o método de leitura de arquivo;
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
		String p1 = new String(); 
		String p2 = new String();
		String c = new String();
		while(fim==false) {
			System.out.println("Deseja inserir nova palavra? [S/N]");
			if(sc2.hasNext()) {
				c = sc2.next();
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
					if(sc2.hasNext()) {
						p1 = sc2.next();
					}						
					p1 = Utils.processaPalavra(p1, 0);
					System.out.println("Insira a palavra-chave: ");
					if(sc2.hasNext()) {
						p2 = sc2.next();	
					}
					p2 = Utils.processaPalavra(p2, 0);
					boolean teste = comparaPalavra(p1);
					if(teste == false) {
						palavra = new Palavra(p1, p2);
						arrayFinal.add(palavra);
					}
					else {
						System.out.println("Palavra já existente no jogo! ");
					}
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
		arrayFinal.addAll(a);
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
		ArrayList<Palavra> c = retornaBiblioteca();
		for (int i = 0; i < c.size(); i++) {
			System.out.println(c.get(i));
		}
	}
		
}