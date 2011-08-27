package br.com.caelum.agiletickets;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;

import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;
import br.com.caelum.vraptor.util.jpa.EntityManagerCreator;
import br.com.caelum.vraptor.util.jpa.EntityManagerFactoryCreator;

public class PreencheBanco {
	
	private static EntityManagerFactoryCreator creator;
	private static EntityManagerCreator managerCreator;
	private static EntityManager manager;
	private static Estabelecimento estabelecimento;
	private static Espetaculo espetaculo;
	
	public static void main(String[] args) {
		
		InitializeObjects();
		
		manager.getTransaction().begin();
		manager.createQuery("delete from Sessao").executeUpdate();
		manager.createQuery("delete from Espetaculo").executeUpdate();
		manager.createQuery("delete from Estabelecimento").executeUpdate();
		
		estabelecimento.setNome("Casa de shows");
		estabelecimento.setEndereco("Rua dos Silveiras, 12345");
		espetaculo.setEstabelecimento(estabelecimento);
		espetaculo.setNome("Depeche Mode");
		espetaculo.setTipo(TipoDeEspetaculo.SHOW);

		manager.persist(estabelecimento);
		manager.persist(espetaculo);

		adicionaEspetaculo();

		manager.getTransaction().commit();
		manager.close();
	}

	private static void adicionaEspetaculo() {
		for (int i = 0; i < 10; i++) {
			Sessao sessao = new Sessao();
			sessao.setEspetaculo(espetaculo);
			sessao.setInicio(new DateTime().plusDays(7+i));
			sessao.setDuracaoEmMinutos(60 * 3);
			sessao.setTotalIngressos(10);
			sessao.setIngressosReservados(10 - i);
			manager.persist(sessao);
		}
	}

	private static void InitializeObjects() {
		creator = new EntityManagerFactoryCreator();
		creator.create();
		managerCreator = new EntityManagerCreator(creator.getInstance());
		managerCreator.create();
		manager = managerCreator.getInstance();
		estabelecimento = new Estabelecimento();
		espetaculo = new Espetaculo();
	}
}
