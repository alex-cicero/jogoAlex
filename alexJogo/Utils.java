package jogoAlex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Utils {
	
	//private static int contaPalavras = 0;
	
	public static boolean optionsIni() throws ErroEscolhaOpcao {
		System.out.println("Escolha uma opcao: ");		
		System.out.println("J - JOGAR");
		System.out.println("B - BIBLIOTECA");
		System.out.println("\n\n");
		char c = leitura();
		char j = 'J';
		char b = 'B';
		if(c == j) {
			return true;
		}
		else {
			if(c == b) {
			//	Biblio.inserePalavra(); 
				return false;
			}
			else {
				throw new ErroEscolhaOpcao("Opcao nao encontrada!");			
			}
		}
	}
	
	public static char leitura() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		char c = converteChar(str);
		return c;
	}
	
	public static char converteChar(String str) {
		str = str.trim();
		str = str.toUpperCase();
		String[] vet = str.split(" ");
		str = vet[0];
		char c = str.charAt(0);
		return c;
	}
	
	public static void limpaTela() {
		for(int i = 0; i<50; i++) 
			System.out.println();
	}

	public static void objetivo() {
		System.out.println("Este jogo tem como objetivo fazer com que o jogador tente acertar a palavra da partida, ");
		System.out.println("de letra em letra, podendo sempre tentar adivinhar a palavra. \n\n");
	}
	
	public static Palavra sorteador() {
		List<Palavra> b = Biblio.retornaBiblioteca();
		int tam = b.size();
        Random r = new Random();
        int index = r.nextInt(tam);
        Palavra pal = b.get(index);
        return pal;
	}
	
	public static Palavra apresenta1() {
	        Palavra pal = sorteador();
        	System.out.println("A palavra-chave eh: " + pal.getPkey());
	        String p = pal.getP();
        	int tam = p.length();
	        System.out.println("A palavra tem " + tam + " letras.");
        	return pal;
	}
	
	public static void apresenta2(Palavra pal) {
    	System.out.println("A palavra-chave eh: " + pal.getPkey());
        String p = pal.getP();
    	int tam = p.length();
        System.out.println("A palavra tem " + tam + " letras.");
	}
	
	public static void jogador() { //a funcao "principal", a que faz a conexao com a maioria delas
		Palavra pal = apresenta1();
		String word = pal.getP();
		char[] palavra = word.toCharArray();
		int tam = palavra.length;
		char[] player = word.toCharArray();
		for (int i = 0; i < tam; i++) { //inicia partida limpando todas as posicoes do vetor de player
			player[i] = ' ';
		}
        for(int i = 0; i < tam; i++) { //identifica em palavra onde tem caractere e onde nao tem 
    		if(palavra[i] == ' ') { //para que em player na posicao i tenha espacos vazios quando palavra tiver na posicao i
    			player[i] = ' ';
    		}
    		else{
    			if(palavra[i] == '-') {
    				player[i] = '-'; //e que player tenha hifen quando ha o determinado caractere na posicao i
    			}
    			else {
    				player[i] = '_'; //e que player tenha underline quando ha caractere na posicao i de palavra
    			}
    		}
    	}
     //
     //
     //
   		boolean fim = false;
   		while(false == fim) {
	   		fim = jogada(player, palavra, pal);
        	if (fim) {
        		break;
        	}
        	else {
            	fim = tentFinalizacao(word);
            	if(fim) {
            		break;
            	}
        	}
        }
	}
	
	public static void progresso(char[] player) {
		System.out.println("Seu progresso: ");
		for(int i = 0; i<player.length; i++) {
			System.out.print(player[i] + " ");
		}
		System.out.println();
	}
	
	static Set<Character> w = new HashSet<Character>();
	
	public static boolean confereLetra (char ch, char letra) { //palavra, player
		if (ch == letra) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	public static boolean conferePalavraPlayer(char[] palavra, char[] player) {
		boolean teste = false;
		for(int i = 0; i < palavra.length; i++) {
			if(player[i] != palavra[i]) {
				teste = false;
				break;
			}
		}
		if(teste == false) {
			return false;
		}
		else {
			return true;
		}
	}	
	    
	public static boolean tentativa(char[] player, char[] palavra, char c){
		boolean t1 = false; //se letra ja foi escolhida, VIRA TRUE
		boolean t2 = false; //se letra esta na palavra, VIRA TRUE; se FALSE, ela nao esta na palavra
        char letra = c, ch = ' ';
        Iterator<Character> it = w.iterator();
        while(it.hasNext()){
            ch = (char) it.next();
            t1 = confereLetra(ch, letra); //letra do set, letra do jogador
        }
		if(t1) { 
			System.out.println("Letra ja escolhida anteriormente!");
		}
		else {
            for (int i = 0; i<palavra.length; i++) {
               ch = palavra[i]; //cada letra de palavra vai ser testada
               t2 = confereLetra(ch, letra);
               if(t2) {
            	   break;
               }
            }
            if(t2 == false) {
				System.out.println("NAO, nao tem essa letra na palavra!");
                w.add(letra);
            }
            else {
                for (int j = 0; j<palavra.length; j++) {                    
                    if((letra==palavra[j]) && (player[j] == '_')) 
                    { //se a letra do usuario eh igual a letra da palavra e se a posicao de player estah com underline
                       player[j] = letra;
                       w.add(letra); //adiciona letra nas que jah foram escolhidas
                       t2 = true;                        
                    }
                }
        		if(t2){
    				System.out.println("SIM, tem essa letra na palavra!");
    			}
            }
		}
		boolean end = false;
		if(conferePalavraPlayer(palavra, player)){
			end = parabens();
		}
		return end;
	}
	
	public static void letras(Set<Character> w) {
		System.out.println("\n Letras ja usadas: ");
		Iterator<Character> it2 = w.iterator();
		while(it2.hasNext()) {
			System.out.print(it2.next() + " ");
		}
		System.out.println("\n");  
	}
	
	public static boolean jogada(char[] player, char[] palavra, Palavra pal) {
		progresso(player);
		apresenta2(pal);
		letras(w);
		System.out.println("\nEntre com uma letra: ");
		char c = leitura();
		boolean end = tentativa(player, palavra, c);
		return end;
	}
	
	public static boolean tentFinalizacao(String pal) {
		boolean fim = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Voce sabe qual e a palavra? [S/N]");
		String resp = new String();
        char c = leitura();
		if(c=='S') {
			System.out.println("Qual e a palavra? ");
			resp = sc.nextLine();
			sc.close();
			resp = resp.trim();
			resp = resp.toUpperCase();
			if(resp.equals(pal)) {
			    fim = parabens();
				return fim;
			}
			else{
				System.out.println("NAO, a palavra nao eh essa... \n");
				return false;
			}
		}
		else {
			fim = false;
		}
		return fim;
	}
	
	public static void adeus() {
		System.out.println("FIM DE JOGO!");
		System.out.println("OBRIGADO POR JOGAR!");
	}
	
	public static boolean parabens() {
		System.out.println("PARABENS, VOCE ACERTOU A PALAVRA!!");
		return true;
	}
}
