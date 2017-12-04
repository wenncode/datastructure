public class TADListaPacientes {
	protected NodoPaciente first;
	protected NodoPaciente last;
	public TADListaPacientes() {
		this.first = null;
		this.last = null;
	}
	public void addBeginPacient(Paciente pessoa) {
		NodoPaciente novoNodo = new NodoPaciente(pessoa);
		if (first==null) {
			first = novoNodo;
			last = novoNodo;
		} else {
			last.next = novoNodo;
			novoNodo.back = last;
			last = novoNodo;
		}
	}
	public void addLastPacient(Paciente pessoa) {
		
	}
	
}
	


