package br.com.integraodonto.controller;

import br.com.integraodonto.dao.ConsultorioDAO;
import br.com.integraodonto.dao.ProfissionalDAO;
import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.features.Menu;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
public class ProfissionalController {

    Menu menu;

    public ProfissionalController() {
        this.menu = new Menu();
    }

    @RequestMapping("/todos-profissionais")
    public ModelAndView todosProfissionais(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ModelAndView mv = new ModelAndView("todos-profissionais");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel())) { // Verifica se usuário tem permissões

                List<ProfissionalDTO> profissionalList = profissionalDAO.listar(profissionalDTO.getConsultorioID());

                String hidden = menu.menu(profissionalDTO.getNivel());
                ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

                mv.addObject("hidden", hidden);
                mv.addObject("profissionalList", profissionalList);
                mv.addObject("consultorio", consultorioDTO);
                mv.addObject("profissional", profissional);

                return mv;

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {

        } finally {
            if (connection != null) {
                connection.close();

            }
        }
        return null;
    }

    @RequestMapping("/adiciona-profissional")
    public ModelAndView adicionaProfissional(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ModelAndView mv = new ModelAndView("adiciona-profissional");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel())) { // Verifica se usuário tem permissões

                String hidden = menu.menu(profissionalDTO.getNivel());
                ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

                mv.addObject("hidden", hidden);
                mv.addObject("consultorio", consultorioDTO);
                mv.addObject("profissional", profissional);

                return mv;

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {

        } finally {
            if (connection != null) {
                connection.close();

            }
        }
        return null;
    }

    @RequestMapping("/adicionando-profissional")
    public String adiciona(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ProfissionalDTO profissional = new ProfissionalDTO();
        ContatoDTO contatoDTO = new ContatoDTO();

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel())) { // Verifica se usuário tem permissões
                profissional.setNome(request.getParameter("nome"));
                profissional.setSexo(request.getParameter("sexo"));
                profissional.setEspecializacao(request.getParameter("especializacao"));
                profissional.setCro(request.getParameter("cro"));
                profissional.setCpf(request.getParameter("cpf"));
                profissional.setRg(request.getParameter("rg"));

                profissional.setLogin(request.getParameter("login"));
                profissional.setSenha(request.getParameter("senha"));
                profissional.setConsultorioID(profissionalDTO.getConsultorioID());

                contatoDTO.setCelular(request.getParameter("celular"));
                contatoDTO.setFixo(request.getParameter("fixo"));
                contatoDTO.setEmail(request.getParameter("email"));

                profissionalDAO.adiciona(profissional, contatoDTO);

                return "redirect:todos-profissionais";
            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {

        } finally {
            if (connection != null) {
                connection.close();

            }
        }
        return null;
    }
}
