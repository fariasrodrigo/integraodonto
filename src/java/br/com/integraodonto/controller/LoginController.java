package br.com.integraodonto.controller;

import br.com.integraodonto.dao.ProfissionalDAO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST) //Controller é criado e depois, ela deixa de existir.
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/acessando-consultorio")
    public String efetuaLogin(ProfissionalDTO profissional, HttpSession session) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ProfissionalDTO profissionalDTO = profissionalDAO.retornaDadosProfissionalLogado(profissional);

        try {
            if (profissionalDTO != null) {
                if ("ativo".equals(profissionalDTO.getStats())) {
                    if ("nao".equals(profissionalDTO.getDeletado())) {

                        session.setAttribute("logando", profissional);

                    } else {
                        System.out.println("Usuário Deletado");
                    }

                } else {
                    System.out.println("Usuário Inativo");
                }

            } else {
                System.out.println("Usuário ou Senha incorretos");
                return "redirect:login";
            }
        } catch (Exception e) {

        } finally {
            if (connection != null) {
                connection.close();

            }
        }

        return "redirect:painel";
    }

    @RequestMapping("/logout")
    public String efetuaLogout(HttpSession session, HttpServletRequest request) {

        request.getSession().removeAttribute("logando");
        return "redirect:login";
    }
}
