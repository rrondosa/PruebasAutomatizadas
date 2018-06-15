package ar.edu.unlam.tallerweb1.pruebasAutomaticas;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class UsuarioDaoTest extends SpringTest{
//	@Autowired
	@Inject
	private UsuarioDao usuarioDao;
   
	@Test
    @Transactional
    @Rollback(true)
    public void buscarUsuarioExistenteDeberiaEncontrarlo(){
    	//preparacion
    	Usuario u = new Usuario(1L,"email","password","Admin");
    	getSession().save(u);
    	
    	//Ejeccucion
    	Usuario encontrado = usuarioDao.consultarUsuario(u);
    	
    	//validadcion
        assertThat(encontrado).isNotNull();
    }
	
    //otro test no existe yque haya al menos un user
	@Test
    @Transactional
    @Rollback(true)
    public void buscarUsuarioNoExistenteDeberiaNoEncontrarlo(){
    	//preparacion
    	Usuario u1 = new Usuario(1L,"user1@email.com","1234","Admin");
    	getSession().save(u1);
    	
    	Usuario u2 = new Usuario(2L,"user2@email.com","5678","Oprrador");
    	getSession().save(u2);
    	
    	//Ejeccucion
    	Usuario encontrado = usuarioDao.consultarUsuario(u1);
    	
    	//validadcion
        assertThat(encontrado).isNotNull();
    }
}
