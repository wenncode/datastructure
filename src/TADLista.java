
public class TADLista {
	
	protected Nodo primeiro;
	protected Nodo ultimo;

	public TADLista() {
		this.primeiro = null;
		this.ultimo = null;
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
		int count = 0;
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
	public int size() {
		int count = 0;
		Nodo aux = primeiro;
		while (aux!=null) {
			aux = aux.proximo;
			count++;
		}
		return count;
	}
	public Atendimento retornaUltimo() {
		return ultimo.dado;
	}

}
