package Condicional;

import java.util.Scanner;

public class SwitchCase {

	public static void main(String[] args) {
		
		int mes;
		String nomeMes;
		Scanner leia = new Scanner(System.in);
		
		System.out.println(
				"Escolha o seu m�s de nascimento: "
				+ "\n ################################################################"
				+ "\n # 1 - Janeiro;		# 2 - fevereiro;	# 3 - Mar�o	#"
				+ "\n # 4 - Abril;		# 5 - Maio;		# 6 - Junho	#"			
				+ "\n # 7 - Julho;		# 8 - Agosto;		# 9 - Setembro	#"
				+ "\n # 10 - Outubro;	# 11 - Novembro;	# 12 - Dezembro #"
				+ "\n ################################################################"
				+ "\n\n Resposta: "
		);
		mes = leia.nextInt();
		leia.close();
		
		switch (mes) {
			case 1: {
				nomeMes = "Janeiro";
				break;
			}
			case 2: {
				nomeMes = "Fevereiro";
				break;			
			}
			case 3: {
				nomeMes = "Mar�o";
				break;
			}
			case 4: {
				nomeMes = "Abril";
				break;
			}
			case 5: {
				nomeMes = "Maio";
				break;
			}
			case 6: {
				nomeMes = "Junho";
				break;
			}
			case 7:{
				nomeMes = "Julho";
				break;
			}
			case 8: {
				nomeMes = "Agosto";
				break;
			}
			case 9: {
				nomeMes = "Setembro";
				break;
			}
			case 10: {
				nomeMes = "Outubro";
				break;
			}
			case 11: {
				nomeMes = "Novembro";
				break;
			}
			case 12: {
				nomeMes = "Dezembro";
				break;
			}
		default:
			throw new IllegalArgumentException("O m�s selecionado � inv�lido: " + mes);
		}
		
		System.out.println("\n\n\n\n\n Voc� me informou que nasceu no m�s de " + nomeMes);
		
	}
	
}
