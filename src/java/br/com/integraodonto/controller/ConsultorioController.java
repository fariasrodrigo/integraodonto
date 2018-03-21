package br.com.integraodonto.controller;

import br.com.integraodonto.bo.ConsultorioBO;
import br.com.integraodonto.bo.ContatoBO;
import br.com.integraodonto.bo.EnderecoBO;
import br.com.integraodonto.bo.ProfissionalBO;
import br.com.integraodonto.dao.ConsultorioDAO;
import br.com.integraodonto.dao.ContatoDAO;
import br.com.integraodonto.dao.EnderecoDAO;
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
    ConsultorioBO consultorioBO;
    ProfissionalBO profissionalBO;
    ContatoBO contatoBO;
    EnderecoBO enderecoBO;

    public ConsultorioController() {
        this.menu = new Menu();
        this.consultorioBO = new ConsultorioBO();
        this.profissionalBO = new ProfissionalBO();
        this.contatoBO = new ContatoBO();
        this.enderecoBO = new EnderecoBO();
    }

    @RequestMapping("/consultorio")
    public ModelAndView consultorio(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        Connection connection = new MysqlConnectionPool().getConnection();

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                String hidden = menu.menu(profissionalDTO.getNivel());
                ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ContatoDTO contatoDTO = contatoBO.buscaPorId(consultorioDTO.getContatoID()); // Buscar contato por ID
                EnderecoDTO enderecoDTO = enderecoBO.buscaPorId(consultorioDTO.getEnderecoID()); // Buscar endereço por ID
                ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID
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
        consultorio.setCnpj(request.getParameter("cnpjConsultorio"));
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

    @RequestMapping("/alterando-consultorio")
    public String alterar(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);
        ConsultorioDTO consultorioDTO = new ConsultorioDTO();
        ContatoDAO contatoDAO = new ContatoDAO(connection);
        ContatoDTO contatoDTO = new ContatoDTO();
        EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões
                consultorioDTO.setNome(request.getParameter("nome"));
                consultorioDTO.setNomeResponsavel(request.getParameter("nomeResponsavel"));
                consultorioDTO.setCnpj(request.getParameter("cnpj"));

                contatoDTO.setCelular(request.getParameter("celular"));
                contatoDTO.setFixo(request.getParameter("fixo"));
                contatoDTO.setEmail(request.getParameter("email"));

                enderecoDTO.setCep(request.getParameter("cep"));
                enderecoDTO.setEndereco(request.getParameter("endereco"));
                enderecoDTO.setNumero(request.getParameter("numero"));
                enderecoDTO.setCompl(request.getParameter("compl"));
                enderecoDTO.setBairro(request.getParameter("bairro"));
                enderecoDTO.setCidade(request.getParameter("cidade"));
                enderecoDTO.setEstado(request.getParameter("estado"));

                ConsultorioDTO recuperandoConsultorioSession = consultorioDAO.recuperaConsultorio(profissionalDTO.getConsultorioID());
                consultorioDAO.alterar(consultorioDTO, profissionalDTO.getConsultorioID());
                contatoDTO.setId(recuperandoConsultorioSession.getContatoID());
                contatoDAO.alterar(contatoDTO);
                enderecoDTO.setId(recuperandoConsultorioSession.getEnderecoID());
                enderecoDAO.alterar(enderecoDTO);
                return "redirect:consultorio";

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
