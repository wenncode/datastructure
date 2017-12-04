
public class NodoPaciente {

	protected Paciente pessoa;	
	protected NodoPaciente next;
	protected NodoPaciente back;
	
	public NodoPaciente(Paciente pessoa){
		this.pessoa = pessoa;
		this.next = null;
		this.back = null;
	}
	
	
}