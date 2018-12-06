package jogoAlex;

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
				Utils.adeus();
			}
		}
		catch(ErroEscolhaOpcao f){
			f.getMessage();
			f.printStackTrace();
			throw new ErroInicializacao ("Erro na Inicializacao");
		}
	}
	
	public static void campanha() {
		Utils.limpaTela();
		Utils.jogador();
		//Fim do jogo
		System.out.println("Voce deseja jogar novamente?");
		System.out.println("[Digite S para continuar]");
		char r = Utils.scanf();
		if(r=='S') {
			campanha();
		}
	}
	
}
