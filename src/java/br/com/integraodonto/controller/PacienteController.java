package br.com.integraodonto.controller;

import br.com.integraodonto.dao.ConsultorioDAO;
import br.com.integraodonto.dao.ProfissionalDAO;
import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.features.Menu;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST) //Controller é criado e depois, ela deixa de existir.
public class PacienteController {

    Menu menu;

    public PacienteController() {
        this.menu = new Menu();
    }

    @RequestMapping("/todos-pacientes")
    public ModelAndView todosPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("todos-pacientes");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/adiciona-paciente")
    public ModelAndView adicionaPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("adiciona-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/editar-paciente")
    public ModelAndView aeditarPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("editar-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/consultas-paciente")
    public ModelAndView consultasPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("consultas-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/anamnese-paciente")
    public ModelAndView anamnesePacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("anamnese-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/financeiro-paciente")
    public ModelAndView financeiroPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("financeiro-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/arquivos-paciente")
    public ModelAndView arquivosPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("arquivos-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/orcamentos-paciente")
    public ModelAndView orcamentosPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("orcamentos-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/procedimentos-paciente")
    public ModelAndView procedimentosPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("procedimentos-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }

    @RequestMapping("/receituario-paciente")
    public ModelAndView receituarioPacientes(HttpSession session, HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
        ModelAndView mv = new ModelAndView("receituario-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioDAO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalDAO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
        return mv;
    }
}
