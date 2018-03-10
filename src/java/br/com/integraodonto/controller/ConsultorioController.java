package br.com.integraodonto.controller;

import br.com.integraodonto.dao.ConsultorioDAO;
import br.com.integraodonto.dao.ContatoDAO;
import br.com.integraodonto.dao.EnderecoDAO;
import br.com.integraodonto.dao.ProfissionalDAO;
import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.EnderecoDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.features.Menu;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST) //Controller é criado e depois, ela deixa de existir.
public class ConsultorioController {

    Menu menu;

    public ConsultorioController() {
        this.menu = new Menu();
    }

    @RequestMapping("/consultorio")
    public ModelAndView consultorio(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ContatoDAO contatoDAO = new ContatoDAO(connection);
        EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel())) { // Verifica se usuário tem permissões

                String hidden = menu.menu(profissionalDTO.getNivel());
                ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ContatoDTO contatoDTO = contatoDAO.buscaPorId(consultorioDTO.getContatoID()); // Buscar contato por ID
                EnderecoDTO enderecoDTO = enderecoDAO.buscaPorId(consultorioDTO.getEnderecoID()); // Buscar endereço por ID
                ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID
                ModelAndView mv = new ModelAndView("consultorio");

                mv.addObject("consultorio", consultorioDTO);
                mv.addObject("contato", contatoDTO);
                mv.addObject("endereco", enderecoDTO);
                mv.addObject("hidden", hidden);
                mv.addObject("profissional", profissional);

                return mv;

            } else {
                response.sendRedirect("painel");
            }

        } catch (SQLException e) {

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @RequestMapping("/criando-consultorio")
    public String criandoConsultorio() {
        return "criando-consultorio";
    }

    @RequestMapping("/adiciona-consultorio")
    public String adicionaConsultorio(HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDTO consultorio = new ConsultorioDTO();
        ProfissionalDTO profissional = new ProfissionalDTO();
        consultorio.setNome(request.getParameter("nomeConsultorio"));
        profissional.setNome(request.getParameter("nomeProfissional"));
        profissional.setLogin(request.getParameter("login"));
        profissional.setSenha(request.getParameter("senha1"));

        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);

        try {

            consultorioDAO.adicionaConsultorio(consultorio, profissional);

        } catch (SQLException e) {

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return "redirect:painel";

    }
}
