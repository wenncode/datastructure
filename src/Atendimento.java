import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Atendimento {

	protected Paciente pessoa;
	private int horaChegada;
	private int minutoChegada;
	private int horaAtendimento;
	private int minutoAtendimento;
	private int horaSaida;
	private int minutoSaida;
	private double temperatura;
	private double peso;
	private double altura;
	private int pressaoSistolica;
	private int pressaoDiastolica;
	private int prioridade;

	private String parecer;
	BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	
	public Paciente getPessoa() {
		return pessoa;
	}
	public void setPessoa(Paciente pessoa) {
		this.pessoa = pessoa;
	}
	public int getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(int horaChegada) {
		this.horaChegada = horaChegada;
	}
	public int getMinutoChegada() {
		return minutoChegada;
	}
	public void setMinutoChegada(int minutoChegada) {
		this.minutoChegada = minutoChegada;
	}
	public int getHoraAtendimento() {
		return horaAtendimento;
	}
	public void setHoraAtendimento(int horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	public int getMinutoAtendimento() {
		return minutoAtendimento;
	}
	public void setMinutoAtendimento(int minutoAtendimento) {
		this.minutoAtendimento = minutoAtendimento;
	}
	public int getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(int horaSaida) {
		this.horaSaida = horaSaida;
	}
	public int getMinutoSaida() {
		return minutoSaida;
	}
	public void setMinutoSaida(int minutoSaida) {
		this.minutoSaida = minutoSaida;
	}
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public int getPressaoSistolica() {
		return pressaoSistolica;
	}
	public void setPressaoSistolica(int pressaoSistolica) {
		this.pressaoSistolica = pressaoSistolica;
	}
	public int getPressaoDiastolica() {
		return pressaoDiastolica;
	}
	public void setPressaoDiastolica(int pressaoDiastolica) {
		this.pressaoDiastolica = pressaoDiastolica;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public String getParecer() {
		return parecer;
	}
	public void setParecer(String parecer) {
		this.parecer = parecer;
	}
	public String toStringAtendimento(Atendimento at){
			return "Prioridade: "+at.getPrioridade()+"Hora Chegada: "+at.getHoraChegada()+":"+at.getMinutoChegada()+
					"Hora Atendimento: "+at.getHoraAtendimento()+":"+at.getMinutoAtendimento()+"Hora Saida: "+at.getHoraSaida()+":"+at.getMinutoSaida()+"Altura: "+at.getAltura()+
					"Peso: "+at.getPeso()+"Pressão Diastólica: "+at.getPressaoDiastolica()+"Pressao Sistólica: "+at.getPressaoSistolica()+"Temperatura corporal: "+at.getTemperatura()+
					"Parecer: "+at.getParecer();
		}
	
	
}
