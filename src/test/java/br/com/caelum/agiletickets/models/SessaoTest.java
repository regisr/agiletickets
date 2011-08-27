package br.com.caelum.agiletickets.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SessaoTest {
	
	private Sessao s;
	
	@Before
	public void criaSessao()
	{
		this.s = new Sessao();
	}

	@Test
	public void deveVender1ingressoSeHa2vagas() throws Exception {
        s.setTotalIngressos(2);

        Assert.assertTrue(s.podeReservar(1));
	}

	@Test
	public void naoDeveVender3ingressoSeHa2vagas() throws Exception {
		s.setTotalIngressos(2);

		Assert.assertFalse(s.podeReservar(3));
	}

	@Test
	public void naoDeveVender2ingressoSeHa2vagas() throws Exception {
		s.setTotalIngressos(2);

		Assert.assertFalse(s.podeReservar(3));
	}
	
	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		s.setTotalIngressos(5);

		s.reserva(3);
		Assert.assertEquals(2, s.getIngressosDisponiveis().intValue());
	}
}
