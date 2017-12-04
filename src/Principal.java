import java.io.*;
public class Principal {
	static BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	static TADFila filaInicial = new TADFila();
	TADFila filaPrioridade1 = new TADFila();
	TADFila filaPrioridade2 = new TADFila();
	TADFila filaPrioridade3 = new TADFila();
	TADFila filaPrioridade4 = new TADFila();
	TADFila filaPrioridade5 = new TADFila();
	static TADLista listaTodos = new TADLista();
	TADLista listaFinalizados = new TADLista();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int sair = 0;
		do {
			desenhaMenu();
			System.out.println("[ VOCÊ DESEJA CONTINUAR NO SISTEMA? ]");
			System.out.println("[ [1] SIM || [2] NÃO ]");
			sair = scan.read();
		} while (sair != 1);
	}
	public static void novaChegada() {
		String nome;
		String cpf;
		int anoNascimento;
		try {
			System.out.println("Digite o nome do paciente: ");
			nome = scan.readLine();
			System.out.printf("Digite o CPF do paciente: ");
			cpf = scan.readLine();
			System.out.println("Digite o ano de nascimento do paciente: ");
			anoNascimento = scan.read();
			Paciente pessoa = new Paciente(nome, cpf, anoNascimento);
			listaTodos.addLast(pessoa);
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void novoAtendimento() throws IOException {
		System.out.println("[ Digite o CPF da pessoa a receber o Atendimento ] ");
		String cpf = scan.readLine();
		
		
		
	}
	public static void desenhaMenu() throws IOException, InterruptedException {
		int menuOp = 0;
		do { 
			System.out.println("SELECIONE A OPÃ‡ÃƒO DESEJADA: ");
			System.out.println("[ [1] CADASTRAR NOVO PACIENTE ]");
			System.out.println("[ [2] PESQUISAR PACIENTE ]");
			System.out.println("[ [3] INICIAR UM NOVO ATENDIMENTO ]");
			System.out.println("[ [4] CHAMAR PACIENTE PARA A TRIAGEM ] ");
			System.out.println("[ [5] CHAMAR PACIENTE PARA CONSULTA ]");
			System.out.println("[ [6] LIBERAR PACIENTE ]");
			System.out.println("[ [7] RELATÃ“RIOS ]");
			menuOp = scan.read();
			if (menuOp < 1 || menuOp > 7) {
				Runtime.getRuntime().exec("cls");
				System.out.println("[ INPUT INVÃ�LIDO. SISTEMA PARADO POR 2 SEGUNDOS.]");
				Thread.currentThread();
				Thread.sleep(2000);
			}				
		} while (menuOp >=1 && menuOp <= 7);
		if (menuOp == 1) {
			novaChegada();
		} else if (menuOp == 2) {
			System.out.println("[ DIGITE O CPF DO PACIENTE ]");
			String cpf = scan.readLine();
			listaTodos.searchByID(cpf);
		} else if (menuOp == 3) {
			novoAtendimento();
		} else if (menuOp == 4) {
			iniciarTriagem();
		} else if (menuOp == 5) {
			chamarConsulta();
		} else if (menuOp == 6) {
			liberarPaciente();
		} else if (menuOp == 7) {
			telaRelatorios();
		}		
	}
	
}
