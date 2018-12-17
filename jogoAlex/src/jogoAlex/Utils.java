package jogoAlex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Utils {
		
	public static boolean optionsIni() throws ErroEscolhaOpcao {
		Scanner sc4 = new Scanner(System.in);
		System.out.println("Escolha uma opcao: ");		
		System.out.println("J - JOGAR");
		System.out.println("B - BIBLIOTECA");
		System.out.println("\n\n");
		String c1 = sc4.nextLine();
		sc4.nextLine();
		c1 = processaPalavra(c1, 1);
		if(c1.equals("J")) {
			sc4.close();
			return true;
		}
		else {
			if(c1.equals("B")) {
				Biblio.chamaInserePalavra(); 
				System.out.println("Deseja jogar? [S / Qualquer outra letra]");
				c1 = sc4.nextLine();
				c1 = processaPalavra(c1, 1);
				sc4.nextLine();
				sc4.close();
				return false;
			}
			else {
				sc4.close();
				throw new ErroEscolhaOpcao("Opcao nao encontrada!");			
			}
		}
	}
	
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
	
	public static Palavra apresenta1() {
	        Palavra pal = sorteador();
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
        String jogador = String.valueOf(player);
        String palSort = String.valueOf(palavra);
     //
     //
     //
   		boolean fim = false;
   		while(false == fim) {
	   		fim = jogada(palSort, jogador, pal);
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
	
	public static void progresso(String jogador) {
		System.out.println("Seu progresso: ");
		char[] player = jogador.toCharArray();
		for(int i = 0; i<player.length; i++) {
			System.out.print(player[i] + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	static Set<String> w = new HashSet<String>();
	
	public static boolean confereLetra (String ch, String c) { //palavra, c
		return c.equals(ch);
	}
	
	public static boolean conferePalavraPlayer(String palSort, String jogador) {
		return palSort.equals(jogador);
	}	
	    
	public static boolean tentativa(String palSort, String jogador, String c){
		//c = letra inserida
		boolean t1 = false; //se letra ja foi escolhida, VIRA TRUE
		boolean t2 = false; //se letra esta na palavra, VIRA TRUE; se FALSE, ela nao esta na palavra 
        String ch = new String(); //String que vai ser testada (set, palavra, jogador)
        Iterator<String> it = w.iterator();
        char[] player = jogador.toCharArray();
        char[] palavra = palSort.toCharArray();
        while(it.hasNext()){
            ch = (String) it.next(); //letra do set
            t1 = c.equals(ch); //letra do jogador teste se é igual a letra do set
    		if(t1) { 
    			System.out.println("Letra ja escolhida anteriormente!");
    			break;
    		}
        }
		if(t1 == false){
            for (int i = 0; i<palavra.length; i++) {
               ch = String.valueOf(palavra[i]); //cada letra de palavra vai ser testada
               t2 = confereLetra(ch, c);
               if(t2) {
            	   break;
               }
            }
            if(t2 == false) {
				System.out.println("NAO, nao tem essa letra na palavra!");
                w.add(c);
            }
            else {
                for (int j = 0; j<palavra.length; j++) {                    
                    if((c.charAt(0)==palavra[j]) && (player[j] == '_')) 
                    { //se a letra do usuario eh igual a letra da palavra e se a posicao de player estah com underline
                       player[j] = c.charAt(0);
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
		palSort = String.valueOf(palavra);
		boolean end = false;
		if(conferePalavraPlayer(palSort, jogador)){
			end = parabens();
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
	
	public static boolean jogada(String palSort, String jogador, Palavra pal) {
		progresso(jogador);
		apresenta2(pal);
		letras(w);
		System.out.println("\nEntre com uma letra: ");
		Scanner sc5 = new Scanner (System.in);
		String c = sc5.nextLine();
		c = processaPalavra(c, 1);
		sc5.nextLine();
		sc5.close();
		boolean end = tentativa(palSort, jogador, c);
		return end;
	}
	
	public static boolean tentFinalizacao(String pal) {
		boolean fim = false;
		Scanner sc6 = new Scanner(System.in);
		Scanner sc7 = new Scanner(System.in);
		System.out.println("Voce sabe qual e a palavra? [S/N]");
		String resp = new String();
		String r = sc6.nextLine();
        r = processaPalavra(r, 1);
        sc6.nextLine();
        sc6.close();
		if(r.equals("S")) {
			System.out.println("Qual e a palavra? ");
			resp = sc7.nextLine();
			sc7.nextLine();
			sc7.close();
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
		sc7.close();
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
