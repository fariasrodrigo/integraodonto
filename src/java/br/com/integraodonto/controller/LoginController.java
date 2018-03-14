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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST) //Controller é criado e depois, ela deixa de existir.
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/acessando-consultorio")
    public String efetuaLogin(ProfissionalDTO profissional, HttpSession session, RedirectAttributes model) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ProfissionalDTO profissionalDTO = profissionalDAO.retornaDadosProfissionalLogado(profissional);

        try {
            if (profissionalDTO != null) {
                if ("ativo".equals(profissionalDTO.getStats())) {
                    if ("nao".equals(profissionalDTO.getDeletado())) {

                        session.setAttribute("logando", profissional);
                        return "redirect:painel";

                    } else {
                        model.addFlashAttribute("message", "Usuário Deletado.");
                        return "redirect:login";
                    }

                } else {
                    model.addFlashAttribute("message", "Usuário Inativo.");
                    return "redirect:login";
                }

            } else {
                model.addFlashAttribute("message", "Usuário ou Senha incorretos.");
                return "redirect:login";
            }
        } catch (Exception e) {

        } finally {
            if (connection != null) {
                connection.close();

            }
        }

        return null;
    }

    @RequestMapping("/logout")
    public String efetuaLogout(HttpSession session, HttpServletRequest request) {

        request.getSession().removeAttribute("logando");
        return "redirect:login";
    }
}
