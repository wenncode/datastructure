
public class TADLista {
	
	protected Nodo primeiro;
	protected Nodo ultimo;
	protected NodoPaciente primeiro1;
	protected NodoPaciente ultimo1;
	
	public TADLista() {
		this.primeiro = null;
		this.ultimo = null;
		this.primeiro1 = null;
		this.ultimo1 = null;
	}
	public void addPacientBegin(Paciente pessoa) {
		NodoPaciente novoNodo = new NodoPaciente(pessoa);
		if (primeiro1==null) {
			primeiro1 = novoNodo;
			ultimo1 = novoNodo;
		} else {
			ultimo1.proximo1 = novoNodo;
			novoNodo.anterior1 = ultimo1;
			ultimo1 = novoNodo;
		}
	}
	public void addPacienteEnd(Paciente pessoa) {
		NodoPaciente novoNodo = new NodoPaciente(pessoa);
		if (primeiro1==null) {
			primeiro1 = novoNodo;
			ultimo1 = novoNodo;
		} else {
			ultimo1.proximo1 = novoNodo;
			novoNodo.anterior1 = ultimo1;
			ultimo1 = novoNodo;
		}
		
	}
	public void addEnd(Atendimento dado) {
		Nodo novoNodo = new Nodo(dado);
		if (primeiro==null) {
			primeiro = novoNodo;
			ultimo = novoNodo;
		} else {
			ultimo.proximo = novoNodo;
			novoNodo.anterior = ultimo;
			ultimo = novoNodo;
		}
	}
	
	public void addBegin(Atendimento dado) {
		Nodo novoNodo = new Nodo(dado);
		if (primeiro==null) {
			primeiro = novoNodo;
			ultimo = novoNodo;
		} else {
			novoNodo.proximo = primeiro;
			primeiro.anterior = novoNodo;
			primeiro = novoNodo;
		}
	}
	
	public void removePosition(int position) {
		int count=0;
		Nodo aux = primeiro;
		while (count!=position && aux.proximo!=null) {
			aux=aux.proximo;
			count++;
		}
		if (count==position) {
			aux.anterior.proximo = aux.proximo;
			aux.proximo.anterior = aux.anterior;
			aux = null;
		}
	}
	
	public Atendimento searchByPosition(int position) {
		int count=0;
		Nodo aux = primeiro;
		while (count!=position) {
			aux=aux.proximo;
			count++;
		}
		if (aux.dado==null) {
			return null;
		} else {
			return aux.dado;
		}
	}
	
	public Atendimento searchByID(String id) {
		Nodo aux = primeiro;
		while (aux!=null) {
			if(aux.dado.getPessoa().getCpf().equals(id)) {
				return aux.dado;
			} else {
				aux = aux.proximo;
			}
		}
		return null;
	}

}
