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
		try {
			boolean teste = Utils.optionsIni();
			if(teste) {
				campanha();
				Utils.adeus();
			}
			else {
				System.out.println("Saindo...");
			}
		}
		catch(ErroEscolhaOpcao f){
			throw new ErroInicializacao ("Erro na Inicializacao");
		}
	}
	
	public static void campanha() {
		Utils.limpaTela();
		Utils.jogador();
		//Fim do jogo
		System.out.println("Voce deseja jogar novamente?");
		System.out.println("[Entre com N para terminar o jogo]");
		System.out.println("[Entre com qualquer outra letra para continuar]");
		String r = new String();
		Scanner sc1 = new Scanner(System.in);
		if(sc1.hasNext()) {
			r = sc1.next();
		}
		r = Utils.processaPalavra(r, 1);		
		sc1.close();
		if(r.equals("N") != true) {
			campanha();
		}
		else {
			Utils.adeus();
		}
	}
}
