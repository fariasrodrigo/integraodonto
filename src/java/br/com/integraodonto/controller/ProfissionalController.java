package br.com.integraodonto.controller;

import br.com.integraodonto.bo.ConsultorioBO;
import br.com.integraodonto.bo.ProfissionalBO;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST) //Controller é criado e depois, ela deixa de existir.
public class ProfissionalController {

    Menu menu;
    ConsultorioBO consultorioBO;
    ProfissionalBO profissionalBO;

    public ProfissionalController() {
        this.menu = new Menu();
        this.consultorioBO = new ConsultorioBO();
        this.profissionalBO = new ProfissionalBO();
    }

    @RequestMapping("/todos-profissionais")
    public ModelAndView todosProfissionais(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        ModelAndView mv = new ModelAndView("todos-profissionais");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões              

                String hidden = menu.menu(profissionalDTO.getNivel());
                ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

                List<ProfissionalDTO> profissionalList = profissionalBO.todosProfissionaisConsultorio(profissionalDTO.getConsultorioID());

                mv.addObject("hidden", hidden);
                mv.addObject("profissionalList", profissionalList);
                mv.addObject("consultorio", consultorioDTO);
                mv.addObject("profissional", profissional);

                return mv;

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    @RequestMapping("/adiciona-profissional")
    public ModelAndView adicionaProfissional(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        ModelAndView mv = new ModelAndView("adiciona-profissional");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                String hidden = menu.menu(profissionalDTO.getNivel());
                ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

                mv.addObject("hidden", hidden);
                mv.addObject("consultorio", consultorioDTO);
                mv.addObject("profissional", profissional);

                return mv;

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {

        } finally {

        }
        return null;
    }

    @RequestMapping("/adicionando-profissional")
    public String adiciona(ProfissionalDTO profissionalRequest, ContatoDTO contatoRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                if (profissionalDAO.verificaSeEmailExisteProfissional(profissionalRequest.getLogin(), profissionalDTO.getConsultorioID()) == false) {
                    profissionalRequest.setConsultorioID(profissionalDTO.getConsultorioID());

                    profissionalBO.adiciona(profissionalRequest, contatoRequest);
                    return "redirect:todos-profissionais";
                } else {
                    System.out.println("Email pertence a um profissional ja cadastrado");
                    response.sendRedirect("adiciona-profissional");
                }

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();

            }
        }
        return null;
    }

    @RequestMapping("/alterando-profissional")
    public String alterar(ProfissionalDTO profissionalRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        ContatoDTO contatoDTO = new ContatoDTO();

        contatoDTO.setId(profissionalRequest.getContatoID());
        contatoDTO.setCelular(profissionalRequest.getCelular());
        contatoDTO.setFixo(profissionalRequest.getFixo());
        contatoDTO.setEmail(profissionalRequest.getEmail());

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                profissionalRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                profissionalBO.alterarProfissional(profissionalRequest, contatoDTO);

                return "redirect:todos-profissionais";
            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {

        } finally {

        }
        return null;
    }

    @RequestMapping("/deletando-profissional")
    public void deletar(ProfissionalDTO profissionalRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                profissionalRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                profissionalBO.alterarDeletadoProfissional(profissionalRequest);

                response.setStatus(200);
            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {

        } finally {

        }
    }
}
