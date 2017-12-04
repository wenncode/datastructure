import java.io.*;
import java.util.Scanner;
public class Principal {
	//static BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	static Scanner scan1 = new Scanner(System.in);
	static TADFila filaInicial = new TADFila();
	static TADFila filaPrioridade1 = new TADFila();
	static TADFila filaPrioridade2 = new TADFila();
	static TADFila filaPrioridade3 = new TADFila();
	static TADFila filaPrioridade4 = new TADFila();
	static TADFila filaPrioridade5 = new TADFila();
	static TADListaPacientes listaPacientes = new TADListaPacientes();
	static TADLista listaFinalizados = new TADLista();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		desenhaMenu();
	}
	public static Paciente novaChegada() throws IOException {
		String nome;
		String cpf;
		int anoNascimento;
		System.out.print("Digite o nome do paciente: ");
		scan1.nextLine();
		nome = scan1.nextLine();
		System.out.printf("Digite o CPF do paciente: ");
		cpf = scan1.nextLine();
		System.out.print("Digite o ano de nascimento do paciente: ");
		anoNascimento = scan1.nextInt();
		Paciente pessoa = new Paciente(nome, cpf, anoNascimento);
		listaPacientes.addEndPacient(pessoa);
		System.out.println("[ CADASTRO REALIZADO ]");
		return pessoa;
	}
	public static void novoAtendimento() throws IOException {
		Atendimento dado = new Atendimento();
		System.out.println("[ Digite o CPF da pessoa a receber o Atendimento ] ");
		scan1.nextLine();
		String cpf = scan1.nextLine();
		listaPacientes.searchByIDPaciente(cpf);
		if (listaPacientes.searchByIDPaciente(cpf) == null) {
			dado.setPessoa(novaChegada());	
			filaInicial.enqueue(dado);
		} else { 
			System.out.println("[ PACIENTE ENCONTRADO! ]");
			dado.setPessoa(listaPacientes.searchByIDPaciente(cpf));
			filaInicial.enqueue(dado);
		}
		System.out.println("Digite a hora de chegada do paciente: (Formato 24H)");
		dado.setHoraChegada(scan1.nextInt());
		System.out.println("Digite os minutos de chegada do paciente: (Formato 60 minutos)");
		dado.setMinutoChegada(scan1.nextInt());
	}
	public static void desenhaMenu() throws IOException, InterruptedException {
		int menuOp = 0;
		do {
			do { 
				menuOp = 0;
				System.out.println("SELECIONE A OPCAO DESEJADA: ");
				System.out.println("[ [1] CADASTRAR NOVO PACIENTE ]");
				System.out.println("[ [2] PESQUISAR PACIENTE ]");
				System.out.println("[ [3] INICIAR UM NOVO ATENDIMENTO ]");
				System.out.println("[ [4] CHAMAR PACIENTE PARA A TRIAGEM ] ");
				System.out.println("[ [5] CHAMAR PACIENTE PARA CONSULTA ]");
				System.out.println("[ [6] LIBERAR PACIENTE ]");
				System.out.println("[ [7] RELATORIOS ]");
				System.out.println("[ [8] SAIR ]");
				menuOp = scan1.nextInt();
				if ((menuOp < 1) || (menuOp > 8)) {
					System.out.println("[ INPUT INVALIDO. SISTEMA PARADO POR 2 SEGUNDOS.]");
				}				
			} while (menuOp < 1 || menuOp > 7);
			if (menuOp == 1) {
				novaChegada();
			} else if (menuOp == 2) {
				System.out.println("[ DIGITE O CPF DO PACIENTE ]");
				scan1.nextLine();
				String cpf = scan1.nextLine();
				System.out.println(listaPacientes.searchByIDPaciente(cpf).toString());
			} else if (menuOp == 3) {
				novoAtendimento();
			} else if (menuOp == 4) {
				Atendimento at = chamadaTriagem();
				System.out.println("PRÓXIMO A IR PARA A TRIAGEM: "+ at.getPessoa().getNome());
			} else if (menuOp == 5) {
				proximaConsulta();
			} else if (menuOp == 6) {
				finalizaAtendimento();
			} else if (menuOp == 7) {
				//telaRelatorios();
			}		
		} while (menuOp!=8);
	}
	public void triagemPaciente(Atendimento dado) throws IOException {
		int pergunta1, pergunta2, pergunta3, pergunta4;
		System.out.println("[ TRIAGEM DO PACIENTE! ]");
		System.out.println("[ O paciente está: Entubado, apnéico, sem pulso ou sem reação? ]");
		System.out.println("[[1] SIM || [2] NÃO ]");
		pergunta1 = scan1.nextInt();
		if (pergunta1 == 1) {
			dado.setPrioridade(1);
			filaPrioridade1.enqueue(dado);
			}
		System.out.println("[ O pacinte está em situação de alto risco? Está confuso, letárgico ou em dor/sofrimento agudo? ]");
		System.out.println("[ [1] SIM || [2] NÃO ]");
		pergunta2 = scan1.nextInt();
		if (pergunta2 == 1) {
			dado.setPrioridade(2);
			filaPrioridade2.enqueue(dado);
		}
		System.out.println("[ Quantas coisas são necessárias para o atendimento? RAIOX, TESTES, INJEÇÕES, CONSULTAS... ]");
		System.out.println("[ [1] NENHUMA || [2] UMA || [3] MUITAS ]");
		pergunta3 = scan1.nextInt();
		if (pergunta3 == 1) {
			dado.setPrioridade(5);
			filaPrioridade5.enqueue(dado);
		} else if (pergunta3 == 2) {
			dado.setPrioridade(4);
			filaPrioridade4.enqueue(dado);
		} else if (pergunta3 == 3) {
			System.out.println("[ Frequencia cardíaca acima de 90bpm? Frequencia respiratório menor que 20? Temperatura entre 35 e 38? ]");
			System.out.println("[ Se tomada, Oximetria de pulso menor que 90%? Índice de pico do fluxo respiratório menor que 200? ]");
			System.out.println("[ [1] SIM || [2] NÃO ]");
			pergunta4 = scan1.nextInt();
			if (pergunta4 == 1) {
				dado.setPrioridade(2);
				filaPrioridade2.enqueue(dado);
			} else if (pergunta4 == 2) {
				dado.setPrioridade(3);
				filaPrioridade3.enqueue(dado);
			}
		}
	}
	public static Atendimento chamadaTriagem() {
			System.out.println("[PROXIMO PACIENTE A SER CHAMADO: " + filaInicial.head().getPessoa().getNome());
			return filaInicial.dequeue();	
	}
	public Atendimento perguntasAtendimento(Atendimento at) throws NumberFormatException, IOException{
		System.out.println("Dados para consulta: ");
		System.out.print("Peso: ");
		at.setPeso(Double.parseDouble(scan1.nextLine()));
		System.out.print("Altura: ");
		at.setAltura(Double.parseDouble(scan1.nextLine()));
		System.out.print("Temperatura: ");
		at.setTemperatura(Double.parseDouble(scan1.nextLine()));
		System.out.print("Pressão Diastólica: ");
		at.setPressaoDiastolica(scan1.nextInt());
		System.out.print("Pressão Sistólica: ");
		at.setPressaoSistolica(scan1.nextInt());		
		return at;
	}
	public static void proximaConsulta() throws IOException {
		Atendimento proximoAtendimento;
		if (!filaPrioridade1.isEmpty()) {
			proximoAtendimento = filaPrioridade1.dequeue();
		} else if (!filaPrioridade2.isEmpty()) {
			proximoAtendimento = filaPrioridade2.dequeue();
		} else if (!filaPrioridade3.isEmpty()) {
			proximoAtendimento = filaPrioridade3.dequeue();
		} else if (!filaPrioridade4.isEmpty()) {
			proximoAtendimento = filaPrioridade4.dequeue();
		} else if (!filaPrioridade5.isEmpty()) {
			proximoAtendimento = filaPrioridade5.dequeue();
		} else {
			System.out.println("[ NÃO HÁ PACIENTES. FILAS VAZIAS. ]");
			proximoAtendimento = null;
		} 
		
		if (proximoAtendimento != null) {
			chamadaConsulta(proximoAtendimento.getPessoa().getNome());
			System.out.println("Digite a hora de Atendimento do paciente: (Formato 24H)");
			proximoAtendimento.setHoraAtendimento(scan1.nextInt());
			System.out.println("Digite os minutos de atendimento do paciente: (Formato 60 minutos)");
			proximoAtendimento.setMinutoAtendimento(scan1.nextInt());
			listaFinalizados.addEnd(proximoAtendimento);
		}
	}
	public static void chamadaConsulta(String nome) {
		System.out.println("[ PRÓXIMO PACIENTE PARA CONSULTA: " + nome);
	}
	public static void finalizaAtendimento() throws IOException {
		Atendimento ultimo1 = listaFinalizados.retornaUltimo();
		System.out.println("Digite o parecer do médico: ");
		scan1.nextLine();
		ultimo1.setParecer(scan1.nextLine());
		System.out.println("Digite a hora de saída do paciente: (Formato 24H)");
		ultimo1.setHoraSaida(scan1.nextInt());
		System.out.println("Digite os minutos de saída do paciente: (Formato 60 minutos)");
		ultimo1.setMinutoSaida(scan1.nextInt());
	}
}