package br.com.integraodonto.controller;

import br.com.integraodonto.bo.ConsultorioBO;
import br.com.integraodonto.bo.ProcedimentoBO;
import br.com.integraodonto.bo.ProfissionalBO;
import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.dto.ProcedimentoDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.features.Menu;
import java.io.IOException;
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
public class ProcedimentoController {

    Menu menu;
    ConsultorioBO consultorioBO;
    ProfissionalBO profissionalBO;
    ProcedimentoBO procedimentoBO;

    public ProcedimentoController() {
        this.menu = new Menu();
        this.consultorioBO = new ConsultorioBO();
        this.profissionalBO = new ProfissionalBO();
        this.procedimentoBO = new ProcedimentoBO();
    }

    @RequestMapping("/procedimentos")
    public ModelAndView procedimentos(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        ModelAndView mv = new ModelAndView("procedimentos");

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                String hidden = menu.menu(profissionalDTO.getNivel());
                ConsultorioDTO consultorioDTO = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID
                List<ProcedimentoDTO> procedimentoList = procedimentoBO.todosProcedimentosConsultorio(profissionalDTO.getConsultorioID());

                mv.addObject("hidden", hidden);
                mv.addObject("procedimentoList", procedimentoList);
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

    @RequestMapping("/adicionando-procedimento")
    public String adiciona(ProcedimentoDTO procedimentoRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                procedimentoRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                procedimentoBO.adiciona(procedimentoRequest);

                return "redirect:procedimentos";

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    @RequestMapping("/alterando-procedimento")
    public String alterar(ProcedimentoDTO procedimentoRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                procedimentoRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                procedimentoBO.alterar(procedimentoRequest);

                return "redirect:procedimentos";

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    @RequestMapping("/deletando-procedimento")
    public void deletar(ProcedimentoDTO procedimentoRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("admin".equals(profissionalDTO.getNivel()) && "ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                procedimentoRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                procedimentoBO.alterarDeletadoProcedimento(procedimentoRequest);

                response.setStatus(200);
            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
