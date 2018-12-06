package jogoAlex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Utils {
	
        private static Scanner sc;
	//private static int contaPalavras = 0;
	
	public static boolean optionsIni() throws ErroEscolhaOpcao {
		System.out.println("Escolha uma opcao: ");		
		System.out.println("J - JOGAR");
		System.out.println("B - BIBLIOTECA");
		System.out.println("\n\n");
		char str = scanf();
		char j = 'J';
		char b = 'B';
		if(str == j) {
			return true;
		}
		else {
			if(str == b) {
			//	Biblio.inserePalavra(); 
				return false;
			}
			else {
				throw new ErroEscolhaOpcao("Opcao nao encontrada!");			
			}
		}
	}
	
	public static char scanf() {
		sc = new Scanner(System.in);       
        String c1 = sc.next();
		c1 = c1.trim();
		c1 = c1.toUpperCase();
		String[] vet = c1.split(" ");
		c1 = vet[0];
                char c = c1.charAt(0);
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
		List<Palavra> b = Biblio.biblioteca();
		int tam = b.size();
                Random r = new Random();
                int index = r.nextInt(tam);
                Palavra pal = b.get(index);
                return pal;
	}
	
	public static Palavra apresenta() {
	        Palavra pal = sorteador();
        	System.out.println("A palavra-chave eh: " + pal.getPkey());
	        String p = pal.getP();
        	int tam = p.length();
	        System.out.println("A palavra tem " + tam + " letras.");
        	return pal;
	}
	
	public static void jogador() { //a funcao "principal", a que faz a conexao com a maioria delas
		Palavra pal = apresenta();
		String word = pal.getP();
		char[] palavra = word.toCharArray();
		int tam = palavra.length;
		char[] player = word.toCharArray();
		for (int i = 0; i < tam; i++) { //inicia partida limpando todas as posicoes do vetor
			player[i] = ' ';
		}
	        for(int i = 0; i < tam; i++) { //identifica em palavra onde tem caractere e onde nao tem 
        		if(palavra[i] == ' ') { //para que em player tenha espacos vazios quando palavra assim tiver
        			player[i] = ' ';
        		}
        		else{
        			if(palavra[i] == '-') {
        				player[i] = '-'; //e que player tenha hifen quando ha o determinado caractere na posicao i de palavra
        			}
        			else {
        				player[i] = '_'; //e que player tenha underlines quando ha caractere na posicao i de palavra
        			}
        		}
        	}
       		boolean fim = false;
	        do {
	        	fim = jogada(player, palavra);
	        	if (fim) {
	        		break;
	        	}
            	fim = tentFinalizacao(word);
            	if(fim) {
            		break;
            	}
	        }while(false == fim);

	}
	
	public static void progresso(char[] player) {
		System.out.println("Seu progresso: ");
		for(int i = 0; i<player.length; i++) {
			System.out.print(player[i] + " ");
		}
	}
	
	static Set<Character> w = new HashSet<Character>();
        
	public static boolean tentativa(char[] player, char[] palavra, char c){
		boolean t1 = false; //se letra ja foi escolhida, VIRA TRUE
		boolean t2 = false; //se letra esta na palavra, VIRA TRUE; se FALSE, ela nao esta na palavra
                char letra = c, t3 = ' ';
                Iterator<Character> it = w.iterator();
                while(it.hasNext()){
                    t3 = (char) it.next();
                    for(int j = 0; j<player.length; j++){
                        char d = player[j]; //cada letra de player vai ser comparada
                        if(t3==letra) { //testa se a letra jah foi escolhida
                            t1 = true;
                            break;
                        }
                        if(!t1){//se a letra ainda NAO foi escolhida
                            for (int i = 0; i<palavra.length; i++) {
                                char v = palavra[i]; //cada letra de palavra vai ser testada
                                 if((letra==v) && (d == '_')) { //se a letra do usuario eh igual a letra da palavra e se a posicao de player estah vazia
                                    player[j] = letra;
                                    w.add(letra); //adiciona letra nas que jah foram escolhidas
                                    t2 = true;
                                }
                                else{
                                    w.add(letra);
                                    t2 = false;
                                } 
                            }
                        }
                    }
                }                      
		if(t1) { 
			System.out.println("Letra ja escolhida anteriormente!");
		}
		else{
			if(t2){
				System.out.println("SIM, tem essa letra na palavra!");
			}
			else{
				System.out.println("NAO, nao tem essa letra na palavra!");
			}
		}
		boolean end = false;
		if(player == palavra){
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
	
	public static boolean jogada(char[] player, char[] palavra) {
		progresso(player);
		letras(w);
		System.out.println("\nEntre com uma letra: ");
		char c = scanf();
		boolean end = tentativa(player, palavra, c);
		return end;
	}
	
	public static boolean tentFinalizacao(String pal) {
		boolean fim = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Voce sabe qual e a palavra? [S/N]");
		String resp = new String();
        char r = scanf();
		if(r=='S') {
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
