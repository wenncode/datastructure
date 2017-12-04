
public class NodoPaciente {

	protected Paciente pessoa;	
	protected NodoPaciente proximo1;
	protected NodoPaciente anterior1;
	
	public NodoPaciente(Paciente pessoa){
		this.pessoa = pessoa;
		this.proximo1 = null;
		this.anterior1 = null;
	}
	
	
}