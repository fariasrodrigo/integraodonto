package br.com.integraodonto.controller;

import br.com.integraodonto.bo.ConsultaBO;
import br.com.integraodonto.dto.ConsultaDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST) //Controller é criado e depois, ela deixa de existir.
public class ConsultaController {

    ConsultaBO consultaBO;

    public ConsultaController() {
        this.consultaBO = new ConsultaBO();
    }

    @RequestMapping("/agendando-consulta")
    public String agendandoConsultaPainel(ConsultaDTO consultaRequest, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                consultaRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                consultaRequest.setQuemAgendou(profissionalDTO.getId());
                consultaBO.agendandoConsultaPainel(consultaRequest);

                return "redirect:painel";

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    @RequestMapping("/reagendando-consulta")
    public String reagendarConsulta(ConsultaDTO consultaRequest, HttpServletRequest request, HttpServletResponse response) {

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando"); // Recupera parametros da session

            if ("ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) { // Verifica se usuário tem permissões

                consultaRequest.setConsultorioID(profissionalDTO.getConsultorioID());
                consultaRequest.setQuemAgendou(profissionalDTO.getId());
                consultaBO.reagendarConsulta(consultaRequest);

                return "redirect:painel";

            } else {
                response.sendRedirect("painel");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }
}
