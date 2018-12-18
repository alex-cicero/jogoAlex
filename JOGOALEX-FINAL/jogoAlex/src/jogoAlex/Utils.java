package jogoAlex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Utils {
	
	public static Jogador joga = new Jogador(); 
	
	public static String processaPalavra(String c, int parar) {
		String str = c;
		//p == 0 -> trim e toUpperCase <- parar == 0
		//p == 1 -> primeiro caractere <- parar == 1
		char c2;
		for(int p = 0; p <= parar; p++) {
			if(p == 0) {
				str = str.trim();
				str = str.toUpperCase();
			}
			if(p == 1) { //Pegar primeira letra
				c2 = str.charAt(0);
				str = Character.toString(c2);
			}
		}
		return str;
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
	
	public static void apresenta(Palavra pal) {
    	System.out.println("A palavra-chave eh: " + pal.getPkey());
        String p = pal.getP();
    	int tam = p.length();
        System.out.println("A palavra tem " + tam + " letras.");
	}
	
	public static void jogador(Scanner sc) { //a funcao "principal", a que faz a conexao com a maioria delas
		Palavra pal = sorteador();
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
        String jogador = String.valueOf(player);
        joga.setWPlayer(jogador);
        String palSort = word;

        System.out.println(joga.getWPlayer());
        
        w.clear();
     //
     //magica do jogo
     //
   		boolean fim = false;
   		while(false == fim) {
	   		fim = jogada(palSort, pal, sc);
	   		System.out.println(joga.getWPlayer());
        	if (fim) {
        		break;
        	}
        	else {
            	fim = tentFinalizacao(word, sc);
            	if(fim) {
            		break;
            	}
        	}
        }
	}
	
	public static void progresso() {
		String jogador = joga.getWPlayer();
		System.out.println("Seu progresso: ");
		char[] player = jogador.toCharArray();
		for(int i = 0; i<player.length; i++) {
			System.out.print(player[i] + " ");
		}
		System.out.println();
	}
	
	
	
	public static Set<String> w = new HashSet<String>();
	
	
	
	public static boolean confereLetra (String ch, String c) { //palavra, c
		return c.equals(ch);
	}
	
	public static boolean conferePalavraPlayer(String palSort) {
		return palSort.equals(joga.getWPlayer());
	}	
	    
	public static boolean tentativa(String palSort, String c){
		//c = letra inserida
		String jogador = joga.getWPlayer();
		boolean t1 = false; //se letra ja foi escolhida, VIRA TRUE
		boolean t2 = false; //se letra esta na palavra, VIRA TRUE; se FALSE, ela nao esta na palavra 
        String str = new String(); //String que vai ser testada (set, palavra, jogador)
        Iterator<String> it = w.iterator();
        char[] player = jogador.toCharArray();
        char[] palavra = palSort.toCharArray();
        while(it.hasNext()){
            str = (String) it.next(); //letra do set
            t1 = c.equals(str); //letra do jogador teste se é igual a letra do set
    		if(t1) { 
    			System.out.println("Letra ja escolhida anteriormente!");
    			break;
    		}
        }
		if(t1 == false){ //se nao foi escolhida ainda
            for (int i = 0; i<palavra.length; i++) {
               str = String.valueOf(palavra[i]); //cada letra de palavra vai ser testada
               t2 = confereLetra(str, c);
               if(t2) {
            	   break;
               }
            }
            if(t2 == false) {
				System.out.println("NAO, nao tem essa letra na palavra!");
                w.add(c);
            }
            else {
                for (int k = 0; k < palavra.length; k++) {
                	str = String.valueOf(palavra[k]);
                    if((c.equals(str)) && (player[k] == '_')) 
                    { //se a letra do usuario eh igual a letra da palavra e se a posicao de player estah com underline
                       player[k] = palavra[k];
                    //   System.out.println(player[k]);
                       w.add(c); //adiciona letra nas que jah foram escolhidas
                       t2 = true;                        
                    }
                }
        		if(t2){
    				System.out.println("SIM, tem essa letra na palavra!");
    			}
            }
		}
		
		jogador = String.valueOf(player);
		joga.setWPlayer(jogador);
//		System.out.println(jogador);
		boolean end = false;
		if(conferePalavraPlayer(palSort)){
			end = true;
		}
		return end;
	}
	
	public static void letras(Set<String> w) {
		System.out.println("\n Letras ja usadas: ");
		Iterator<String> it2 = w.iterator();
		while(it2.hasNext()) {
			System.out.print(it2.next() + " ");
		}
		System.out.println("\n");  
	}
	
	public static boolean jogada(String palSort, Palavra pal, Scanner sc) {
		progresso();
		apresenta(pal);
		letras(w);
		System.out.println("\nEntre com uma letra: ");
		String c = new String();
		if(sc.hasNext()) {
			c = sc.next();
		}
		c = processaPalavra(c, 1);
		boolean end = tentativa(palSort, c);
		return end;
	}
	
	public static boolean tentFinalizacao(String pal, Scanner sc) {
		boolean fim = false;
		System.out.println("Voce sabe qual eh a palavra? [S/N]");
		String resp = new String();
		if(sc.hasNext()) {
			resp = sc.next();
		}
        resp = processaPalavra(resp, 1);
		if(resp.equals("S")) {
			System.out.println("Qual e a palavra? ");
			if(sc.hasNext()) {
				resp = sc.next();
			}
			resp = resp.trim();
			resp = resp.toUpperCase();
			if(resp.equals(pal)) {
				parabens();
				fim = true;
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
	
	public static void parabens() {
		System.out.println("PARABENS, VOCE ACERTOU A PALAVRA!!");
	}
}
