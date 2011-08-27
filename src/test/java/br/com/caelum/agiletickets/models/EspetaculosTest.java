package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

public class EspetaculosTest {
	
	private Espetaculo e;
	
	@Before
	public void criaEspetaculo()
	{
		this.e = new Espetaculo();
	}

	@Test
	public void deveCriarUmaSessaoParaInicioIgualAFim() throws Exception {
		LocalDate inicio = new LocalDate(2011, 1, 23);
		LocalDate fim = new LocalDate(2011, 1, 23);
		LocalTime horario = new LocalTime(17,0,0);
		
		List<Sessao> sessoes = e.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		assertEquals(1, sessoes.size());
		assertEquals(inicio.toDateTime(horario), sessoes.get(0).getInicio());
		
	}
	
	@Test
	public void deveCriarSessoesParaMaisDeUmDia() throws Exception {
		LocalDate inicio = new LocalDate(2011, 1, 23);
		LocalDate fim = new LocalDate(2011, 1, 27);
		LocalTime horario = new LocalTime(17,0,0);
		
		List<Sessao> sessoes = e.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		assertEquals(5, sessoes.size());
		
	}
	
	@Test
	public void deveCriarSessoesSemanais() throws Exception {
		
		LocalDate inicio = new LocalDate(2011, 1, 1);
		LocalDate fim = new LocalDate(2011, 1, 15);
		LocalTime horario = new LocalTime(17,0,0);
		
		List<Sessao> sessoes = e.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		assertEquals(3, sessoes.size());
	}

}
