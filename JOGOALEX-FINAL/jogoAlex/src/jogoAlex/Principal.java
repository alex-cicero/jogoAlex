package jogoAlex;

import java.util.Scanner;

public class Principal {
	
	public static void main(String [] args) {
		try {
			telaInicial();
		} catch (ErroInicializacao e) {
			e.printStackTrace();
		}
	}
	
	public static void telaInicial() throws ErroInicializacao {
		System.out.println("Feito por Alex Cicero, 3BINFO 2018 \n");
		System.out.println("'Infinite' forca");
		System.out.println("\n");
		Utils.objetivo();
		Scanner sc = new Scanner(System.in);
		try {
			boolean teste = optionsIni(sc);
			if(teste) {
				campanha(sc);
			}
			else {
				System.out.println("Saindo...");
			}
			sc.close();
		}
		catch(ErroEscolhaOpcao f){
			throw new ErroInicializacao ("Erro na Inicializacao");
		}
	}
	
	public static boolean optionsIni(Scanner sc) throws ErroEscolhaOpcao {
		System.out.println("Escolha uma opcao: ");		
		System.out.println("J - JOGAR");
		System.out.println("B - BIBLIOTECA");
		System.out.println("\n\n");
		String c1 = new String();
		if(sc.hasNext()) {
			c1 = sc.next();
		}
		c1 = Utils.processaPalavra(c1, 1);
		if(c1.equals("J")) {
			return true;
		}
		else {
			if(c1.equals("B")) {
				Biblio.chamaInserePalavra(sc); 
				System.out.println("Deseja jogar? [S / Qualquer outra letra]");
				if(sc.hasNext()) {
					c1 = sc.next();
				}
				c1 = Utils.processaPalavra(c1, 1);
				if(c1.equals("S")) {
					return true;
				}
				return false;
			}
			else {
				throw new ErroEscolhaOpcao("Opcao nao encontrada!");			
			}
		}
	}
	
	public static void campanha(Scanner sc) {
		Utils.limpaTela();
		Utils.jogador(sc);
		//Fim do jogo
		System.out.println("Voce deseja jogar novamente?");
		System.out.println("[Entre com N para terminar o jogo]");
		System.out.println("[Entre com qualquer outra letra para continuar]");
		String r = new String();	
		if(sc.hasNext()) {
			r = sc.next();
		}
		r = Utils.processaPalavra(r, 1);		
		if(r.equals("N") == false) {
			campanha(sc);
		}
		else {
			Utils.adeus();
		}
	}
}
