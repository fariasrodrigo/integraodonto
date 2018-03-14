package br.com.integraodonto.controller;

import br.com.integraodonto.dao.ConsultorioDAO;
import br.com.integraodonto.dao.ContatoDAO;
import br.com.integraodonto.dao.ProfissionalDAO;
import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.features.Menu;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MeuPerfilController {

    Menu menu;

    public MeuPerfilController() {
        this.menu = new Menu();
    }

    @RequestMapping("/meu-perfil")
    public ModelAndView meuPerfil(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ModelAndView mv = new ModelAndView("meu-perfil");
        String readOnly = "";

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) {
                readOnly = "";

            } else if ("usuario".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) {
                readOnly = "readonly";
            }

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

            mv.addObject("readonly", readOnly);
            mv.addObject("hidden", hidden);
            mv.addObject("consultorio", consultorioDTO);
            mv.addObject("profissional", profissional);

            return mv;

        } catch (Exception e) {

        } finally {
            if (connection != null) {
                connection.close();

            }
        }
        return null;
    }

    @RequestMapping("/alterando-perfil")
    public String alterar(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ProfissionalDTO profissional = new ProfissionalDTO();
        ContatoDAO contatoDAO = new ContatoDAO(connection);
        ContatoDTO contatoDTO = new ContatoDTO();

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões
                profissional.setNome(request.getParameter("nome"));
                profissional.setSexo(request.getParameter("sexo"));
                profissional.setCpf(request.getParameter("cpf"));
                profissional.setRg(request.getParameter("rg"));

                profissional.setLogin(request.getParameter("login"));
                profissional.setSenha(request.getParameter("senha"));

                contatoDTO.setCelular(request.getParameter("celular"));
                contatoDTO.setFixo(request.getParameter("fixo"));
                contatoDTO.setEmail(request.getParameter("email"));

                profissionalDAO.alterarMeuPerfilAdmin(profissional, profissionalDTO.getId(), profissionalDTO.getConsultorioID());
                contatoDAO.alterar(contatoDTO, profissionalDTO.getContatoID());

                return "redirect:meu-perfil";

            } else if ("usuario".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) {
                profissional.setNome(request.getParameter("nome"));
                profissional.setSexo(request.getParameter("sexo"));

                profissional.setLogin(request.getParameter("login"));
                profissional.setSenha(request.getParameter("senha"));

                contatoDTO.setCelular(request.getParameter("celular"));
                contatoDTO.setFixo(request.getParameter("fixo"));
                contatoDTO.setEmail(request.getParameter("email"));

                profissionalDAO.alterarMeuPerfilUsuario(profissional, profissionalDTO.getId(), profissionalDTO.getConsultorioID());
                contatoDAO.alterar(contatoDTO, profissionalDTO.getContatoID());

                return "redirect:meu-perfil";
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
