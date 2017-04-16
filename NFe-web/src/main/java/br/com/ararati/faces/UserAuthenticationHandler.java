/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ararati.faces;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * Captura as exceções no momento da autenticação do usuário, redirecionando-o para a url referente á sua exceção
 * @author Leandroc
 */
public class UserAuthenticationHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {

        //Erro ao efetuar a autenticação, por exemplo, usuário não encontrado ou senha inválida
        if (exception.getClass().isAssignableFrom(InternalAuthenticationServiceException.class) || exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            if (!response.isCommitted()) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login_access.xhtml");
                dispatcher.forward(request, response);
            }
        }            
    }

}
