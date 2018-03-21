package br.com.integraodonto.controller;

import br.com.integraodonto.bo.ConsultorioBO;
import br.com.integraodonto.bo.PacienteBO;
import br.com.integraodonto.bo.ProfissionalBO;
import br.com.integraodonto.dao.PacienteDAO;
import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.EnderecoDTO;
import br.com.integraodonto.dto.PacienteDTO;
import br.com.integraodonto.dto.PlanosDTO;
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
public class PacienteController {

    Menu menu;
    PacienteBO pacienteBO;
    ConsultorioBO consultorioBO;
    ProfissionalBO profissionalBO;

    public PacienteController() {
        this.menu = new Menu();
        this.pacienteBO = new PacienteBO();
        this.consultorioBO = new ConsultorioBO();
        this.profissionalBO = new ProfissionalBO();
    }

    @RequestMapping("/todos-pacientes")
    public ModelAndView todosPacientes(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("todos-pacientes");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            if ("ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) {

                String hidden = menu.menu(profissionalDTO.getNivel());
                ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID
                List<PacienteDTO> pacientelList = pacienteBO.todosPacientesConsultorio(profissionalDTO.getConsultorioID());

                mv.addObject("pacienteList", pacientelList);
                mv.addObject("hidden", hidden);
                mv.addObject("consultorio", consultorioDTO);
                mv.addObject("profissional", profissional);

                return mv;
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

    @RequestMapping("/deletando-paciente")
    public void deletar(PacienteDTO pacienteRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                pacienteRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                pacienteBO.alterarDeletadoPaciente(pacienteRequest);

                response.setStatus(200);
            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    @RequestMapping("/alterando-paciente")
    public String alterandoPaciente(PacienteDTO pacienteRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                pacienteRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                pacienteBO.altera(pacienteRequest);

                return "redirect:" + request.getHeader("Referer");
            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    @RequestMapping("/adiciona-paciente")
    public ModelAndView adicionaPaciente(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("adiciona-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            if ("ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) {

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
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();

            }
        }
        return null;
    }

    @RequestMapping("/adicionando-paciente")
    public String adiciona(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        PacienteDAO pacienteDAO = new PacienteDAO(connection);
        ContatoDTO contato = new ContatoDTO();
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        PlanosDTO planos = new PlanosDTO();
        PacienteDTO paciente = new PacienteDTO();

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                paciente.setConsultorioID(profissionalDTO.getConsultorioID());
                paciente.setProntuario(request.getParameter("prontuario"));
                paciente.setNome(request.getParameter("nome"));
                paciente.setSexo(request.getParameter("sexo"));
                paciente.setCpf(request.getParameter("cpf"));
                paciente.setRg(request.getParameter("rg"));

                contato.setCelular(request.getParameter("celular"));
                contato.setFixo(request.getParameter("fixo"));
                contato.setEmail(request.getParameter("email"));

                enderecoDTO.setCep(request.getParameter("cep"));
                enderecoDTO.setEndereco(request.getParameter("endereco"));
                enderecoDTO.setNumero(request.getParameter("numero"));
                enderecoDTO.setCompl(request.getParameter("compl"));
                enderecoDTO.setBairro(request.getParameter("bairro"));
                enderecoDTO.setCidade(request.getParameter("cidade"));
                enderecoDTO.setEstado(request.getParameter("estado"));

                planos.setPlanoDeSaude(request.getParameter("planoDeSaude"));
                planos.setNumeroDoCartao(request.getParameter("numeroDoCartao"));
                planos.setNomeDoPlano(request.getParameter("nomeDoPlano"));
                planos.setTipoDeUsuario(request.getParameter("tipoDeUsuario"));
                planos.setTitularDoPlano(request.getParameter("titularDoPlano"));

                pacienteDAO.adiciona(paciente, contato, enderecoDTO, planos);

                return "redirect:todos-pacientes";

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

    @RequestMapping("/editar-paciente")
    public ModelAndView editarPaciente(String id, HttpServletRequest request) throws SQLException {

        ModelAndView mv = new ModelAndView("editar-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

            long url = Long.parseLong(id);
            PacienteDTO pacientePorId = pacienteBO.pacientePorId(url, profissionalDTO.getConsultorioID());

            mv.addObject("paciente", pacientePorId);
            mv.addObject("hidden", hidden);
            mv.addObject("consultorio", consultorioDTO);
            mv.addObject("profissional", profissional);

            return mv;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return mv;
    }

    @RequestMapping("/consultas-paciente")
    public ModelAndView consultasPacientes(HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("consultas-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

            mv.addObject("hidden", hidden);
            mv.addObject("consultorio", consultorioDTO);
            mv.addObject("profissional", profissional);

            return mv;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();

            }
        }
        return mv;
    }

    @RequestMapping("/anamnese-paciente")
    public ModelAndView anamnesePacientes(HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("anamnese-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
    public ModelAndView financeiroPacientes(HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("financeiro-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
    public ModelAndView arquivosPacientes(HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("arquivos-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
    public ModelAndView orcamentosPacientes(HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("orcamentos-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
    public ModelAndView procedimentosPacientes(HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("procedimentos-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
    public ModelAndView receituarioPacientes(HttpServletRequest request) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("receituario-paciente");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            String hidden = menu.menu(profissionalDTO.getNivel());
            ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
            ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID

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
