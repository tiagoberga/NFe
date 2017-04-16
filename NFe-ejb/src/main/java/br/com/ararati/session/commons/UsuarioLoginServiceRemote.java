/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.commons;

import br.com.ararati.autenticacao.Usuario;
import javax.ejb.Remote;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Leandroc
 */
@Remote
public interface UsuarioLoginServiceRemote extends UserDetailsService{
    
    public Usuario findBy(String username) throws UsernameNotFoundException;
    
}
