public class TADListaPacientes {
	
	protected NodoPaciente first;
	protected NodoPaciente last;
	public TADListaPacientes() {
		this.first = null;
		this.last = null;
	}
	public void addEndPacient(Paciente pessoa) {
		NodoPaciente novoNodo = new NodoPaciente(pessoa);
		if (first == null) {
			first = novoNodo;
			last = novoNodo;
		} else {
			last.next = novoNodo;
			novoNodo.back = last;
			last = novoNodo;
		}
	}
	public void addBeginPacient(Paciente pessoa) {
		NodoPaciente novoNodo = new NodoPaciente (pessoa);
		if (first == null) {
			first = novoNodo;
			last = novoNodo;
		} else {
			novoNodo.next = first;
			first.back = novoNodo;
			first = novoNodo;
		}
	}
	public Paciente searchByIDPaciente(String id) {
		NodoPaciente aux = first;
		while (aux != null) {
			if(aux.pessoa.getCpf().equals(id)) {
				return aux.pessoa;
			} else {
				aux = aux.next;
			}
		}
		return null;
	}
	
	
	
	
	
}
	

