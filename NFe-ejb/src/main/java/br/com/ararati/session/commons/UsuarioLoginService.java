/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.session.commons;

import br.com.ararati.enums.Role;
import br.com.ararati.entity.atenticacao.Usuario;
import br.com.ararati.session.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Obs: Para capturar o usuario logado, utilize a seguinte expressão: <br> User
 * user = (User)
 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 *
 * @author leandro
 */
@Stateless
public class UsuarioLoginService extends AbstractFacade<Usuario> implements UserDetailsService, UsuarioLoginServiceRemote {

    @PersistenceContext(unitName = "NFE-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioLoginService() {
        super(Usuario.class);
    }

    /**
     * Procura o usuário pelo seu login.
     *
     * @param login
     * @return
     */
    @Override
    public Usuario findBy(String login) {
        try {
            String sql = "SELECT o FROM Usuario o WHERE o.login = :pLogin";
            TypedQuery<Usuario> q = getEntityManager().createQuery(sql, Usuario.class);
            q.setParameter("pLogin", login);
            return q.getSingleResult();
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("Usuário não encontrado", e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = findBy(username);
        List<GrantedAuthority> listGrantAuthority = getGrantAuthorities(user);
        UserDetails userDetails = toUserDetails(user, listGrantAuthority);
        return userDetails;
    }

    /**
     * Adiciona os roles do usuário em uma lista do tipo GrantedAuthority
     * (Spring).
     *
     * @param user
     * @return Uma lista do tipo GrantedAuthority
     */
    private List<GrantedAuthority> getGrantAuthorities(Usuario user) {
        List<GrantedAuthority> toReturn = new ArrayList<>();
        if (user != null && user.getUserRoles() != null && !user.getUserRoles().isEmpty()) {
            //prefixo necessário, pois o spring security 4 espera esse prefixo
            final String PREFIX = "ROLE_";
            for (Role role : user.getUserRoles()) {
                toReturn.add(new SimpleGrantedAuthority(PREFIX + role.getDescricao()));
            }
        }
        return toReturn;
    }

    /**
     * Retorna um objeto do tipo UserDetails (Spring)
     *
     * @param usuario utilizado para criar o objeto retornado
     * @param listGrantAuthority lista de roles do usuário
     * @return
     */
    private UserDetails toUserDetails(Usuario usuario, List<GrantedAuthority> listGrantAuthority) {

        UserDetails userDetails = null;
        if (usuario != null) {
            boolean accountNonLocked = !usuario.isBloqueado();
            boolean enabledUser = usuario.isAtivo();
            boolean accountNonExpired = !usuario.isUsuarioExpirado();
            boolean credentialsNonExpired = true;
            userDetails = new org.springframework.security.core.userdetails.User(usuario.getLogin(), usuario.getSenha(), enabledUser, accountNonExpired,
                    credentialsNonExpired, accountNonLocked, listGrantAuthority);
        }

        return userDetails;
    }

    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public boolean checkPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        return new BCryptPasswordEncoder().matches(password, encryptedPassword);
    }

    /**
     * Cria senhas encriptografadas.
     *
     * @param args
     */
    public static void main(String[] args) {
        UsuarioLoginService usuarioLoginService = new UsuarioLoginService();
        System.out.println(usuarioLoginService.encodePassword("admin"));
    }

}
