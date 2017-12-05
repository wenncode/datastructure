
public class TADFila {
	
	protected Nodo primeiro;
	protected Nodo ultimo;
	
	public TADFila() {
		this.primeiro = null;
		this.ultimo = null;
	}
	
	public void enqueue(Atendimento dado) {
		Nodo novoNodo = new Nodo(dado);
		if (isEmpty()) {
			primeiro = novoNodo;
			ultimo = novoNodo;
		} else {
			ultimo.proximo = novoNodo;
			novoNodo.anterior = ultimo;
			ultimo = novoNodo;
		}
	}
	
	public Atendimento dequeue() {
		if (isEmpty()) {
			return null;
		} else {
			Nodo aux = primeiro;
			primeiro = primeiro.proximo;
			primeiro.anterior = null;
			return aux.dado;
		}
	}
	
	public Atendimento head() {
		if (!isEmpty()) {
			return primeiro.dado;
		}
		return null;
	}
	
	public int size() {
		int count=0;
		Nodo aux = primeiro;
		while (aux!=null) {
			aux = aux.proximo;
			count++;
		}
		return count;
	}

	public boolean isEmpty(){
		if (primeiro==null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		while (!isEmpty()) {
			dequeue();
		}
	}
	public static void getPrioridadeObjeto(Atendimento dado, int prioridade) {
		if (dado.getPrioridade() == prioridade) {
			int ano = dado.getPessoa().getAnoNascimento();
		}
	}
	
	
}
