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
	static int contPaciente;
	
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
		contPaciente++;
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
	public static void telaRelatorio() {
		int menuOpcao = 0;
		System.out.println("[ [1] Tempo médio de espera para atendimento ] ");
		System.out.println("[ [2] Tempo médio de atendimento ]");
		System.out.println("[ [3] Tempo médio de atendimento nas filas de prioridade ]");
		System.out.println("[ [4] Imprimir em arquivo texto os pacientes cadastrados ]");
		System.out.println("[ [5] Atendimentos ocorrendo nesse momento");
		System.out.println("[ [6] Relação de idade e prioridade de atendimento");
		System.out.println("[ [SELECIONE] ]");
		do {
			menuOpcao = scan1.nextInt();
			if (menuOpcao < 1 || menuOpcao > 7) {
				System.out.println("[ INPUT INVÁLIDO ]");
			}
		} while (menuOpcao >= 1 && menuOpcao <= 7);
		if (menuOpcao == 1) {
			
		}
	}
	public static void relatorio1(TADLista listaFinalizados) {
		int somaChegadaHora = 0;
		int somaChegadaMinutos = 0;
		int somaChegada = 0;
		int somaAtendimentoHora = 0;
		int somaAtendimentoMinuto = 0;
		int somaAtendimento = 0;
		double media1 = 0;
		for (int i = 0; i < listaFinalizados.size(); i++) {
			somaChegadaHora += (listaFinalizados.searchByPosition(i).getHoraChegada()) * 60;
			somaChegadaMinutos += listaFinalizados.searchByPosition(i).getMinutoChegada();
			somaAtendimentoHora += (listaFinalizados.searchByPosition(i).getHoraAtendimento()) * 60;
			somaAtendimentoMinuto += listaFinalizados.searchByPosition(i).getMinutoAtendimento();
		}
		somaChegada = somaChegadaHora + somaChegadaMinutos;
		somaAtendimento = somaAtendimentoHora + somaAtendimentoMinuto;
		media1 = (somaAtendimento - somaChegada) / listaFinalizados.size();
	}
	public static void relatorio2(TADLista listaFinalizados) {
		int somaAtendimentoHora = 0;
		int somaAtendimentoMinuto = 0;
		int somaSaidaHora = 0;
		int somaSaidaMinutos = 0;
		int somaAtendimento = 0;
		int somaSaida = 0;
		double media2 = 0;
		for (int i = 0; i < listaFinalizados.size(); i++) {
			somaSaidaHora += (listaFinalizados.searchByPosition(i).getHoraSaida()) * 60;
			somaSaidaMinutos += listaFinalizados.searchByPosition(i).getMinutoSaida();
			somaAtendimentoHora += (listaFinalizados.searchByPosition(i).getHoraAtendimento()) * 60;
			somaAtendimentoMinuto += listaFinalizados.searchByPosition(i).getMinutoAtendimento();
		}
		somaSaida = somaSaidaHora + somaSaidaMinutos;
		somaAtendimento = somaAtendimentoHora + somaAtendimentoMinuto;
		media2 = (somaSaida - somaAtendimento) / listaFinalizados.size();
		}
	public static void relatorio3 (TADLista listaFinalizados) {
		for (int j= 0; j < listaFinalizados.size(); j++) {
			
		}
	}
	public static int relatorioAndamento() {
		return filaPrioridade1.size() + filaPrioridade2.size() + filaPrioridade3.size() + filaPrioridade4.size() + filaPrioridade5.size();
	}
	public static void idadePorFila() {
		int cont1 = 0;
		int cont2 = 0;
		int cont3 = 0;
		int cont4 = 0;
		int cont5 = 0;
		int vetsoma1 = 0;
		int vetsoma2 = 0;
		int vetsoma3 = 0;
		int vetsoma4 = 0;
		int vetsoma5 = 0;
		int vet1[] = new int[listaFinalizados.size()];
		int vet2[] = new int[listaFinalizados.size()];
		int vet3[] = new int[listaFinalizados.size()];
		int vet4[] = new int[listaFinalizados.size()];
		int vet5[] = new int[filaPrioridade5.size()];
		for (int i = 0; i < listaFinalizados.size(); i++) {
			if (listaFinalizados.searchByPosition(i).getPrioridade() == 1) {
				cont1++;
				vet1[i] = listaFinalizados.searchByPosition(i).getPessoa().getAnoNascimento();
				vetsoma1 += vet1[i];
			} else if (listaFinalizados.searchByPosition(i).getPrioridade() == 2) {
				cont2++;
				vet2[i] = listaFinalizados.searchByPosition(i).getPessoa().getAnoNascimento();
				vetsoma2 += vet2[i];
			} else if (listaFinalizados.searchByPosition(i).getPrioridade() == 3) {
				cont3++;
				vet3[i] = listaFinalizados.searchByPosition(i).getPessoa().getAnoNascimento();
				vetsoma3 += vet3[i];
			} else if (listaFinalizados.searchByPosition(i).getPrioridade() == 4) {
				cont4++;
				vet4[i] = listaFinalizados.searchByPosition(i).getPessoa().getAnoNascimento();
				vetsoma4 += vet4[i];
			} else if (listaFinalizados.searchByPosition(i).getPrioridade() == 5) {
				cont5++;
				vet5[i] = listaFinalizados.searchByPosition(i).getPessoa().getAnoNascimento();
				vetsoma5 += vet5[i];
			}
		}
	}
}