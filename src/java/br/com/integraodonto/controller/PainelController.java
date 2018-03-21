package br.com.integraodonto.controller;

import br.com.integraodonto.bo.ConsultaBO;
import br.com.integraodonto.bo.ConsultorioBO;
import br.com.integraodonto.bo.PacienteBO;
import br.com.integraodonto.bo.ProfissionalBO;
import br.com.integraodonto.dto.ConsultaDTO;
import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.dto.PacienteDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.features.Menu;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST) //Controller é criado e depois, ela deixa de existir.
public class PainelController {

    Menu menu;
    PacienteBO pacienteBO;
    ConsultaBO consultaBO;
    ConsultorioBO consultorioBO;
    ProfissionalBO profissionalBO;

    public PainelController() {
        this.menu = new Menu();
        this.pacienteBO = new PacienteBO();
        this.consultaBO = new ConsultaBO();
        this.consultorioBO = new ConsultorioBO();
        this.profissionalBO = new ProfissionalBO();
    }

    @RequestMapping("/painel")
    public ModelAndView painel(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ModelAndView mv = new ModelAndView("painel");

        //passando data no painel de consultas
        Date antes = new Date();
        Date depois = new Date();
        depois.setDate(depois.getDate() + 1);
        antes.setDate(antes.getDate() - 1);
        Locale local = new Locale("pt", "BR");
        DateFormat formato = new SimpleDateFormat("dd MMMM", local);

        try {
            ProfissionalDTO profissionalDTO = (ProfissionalDTO) request.getSession().getAttribute("logando");

            if ("ativo".equals(profissionalDTO.getStats()) && "nao".equals(profissionalDTO.getDeletado())) {

                String hidden = menu.menu(profissionalDTO.getNivel());

                ConsultorioDTO consultorio = consultorioBO.buscaPorId(profissionalDTO.getConsultorioID()); // Buscar consultório por ID
                ProfissionalDTO profissional = profissionalBO.buscaPorId(profissionalDTO.getId()); // Buscar contato por ID
                List<PacienteDTO> pacientelList = pacienteBO.comboBoxPacientesPainel(profissionalDTO.getConsultorioID());
                List<ProfissionalDTO> profissionalList = profissionalBO.comboBoxProfissionaisComAgenda(profissionalDTO.getConsultorioID());
                List<ConsultaDTO> consultaList = consultaBO.listarConsultasPainel(profissionalDTO.getConsultorioID());

                mv.addObject("antes", formato.format(antes));
                mv.addObject("depois", formato.format(depois));
                mv.addObject("consultaList", consultaList);
                mv.addObject("pacienteList", pacientelList);
                mv.addObject("profissionalList", profissionalList);
                mv.addObject("hidden", hidden);
                mv.addObject("consultorio", consultorio);
                mv.addObject("profissional", profissional);

                return mv;
            } else {
                response.sendRedirect("login");
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
