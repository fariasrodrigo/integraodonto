package br.com.integraodonto.dto;

public class ConsultaDTO {

    private long id;
    private String dataConsulta;
    private String horarioInicioConsulta;
    private String horarioFimConsulta;
    private String agendamentoFeitoDia;
    private long quemAgendou;  //foreign key
    private String quemAgendouTemp;
    private String statusConsulta; //ativo ou inativo
    private String obs;

    private long pacienteID;  //foreign key
    private String pacienteTemp;

    private long profissionalID;  //foreign key
    private String profissionalTemp;

    private long consultorioID;  //foreign key

    private String lembreteSMS; //sim ou não
    private String lembreteEMAIL; //sim ou não

    private String deletado; //sim ou não

    //Temporarios
    private String celular;
    private String fixo;
    private String email;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the dataConsulta
     */
    public String getDataConsulta() {
        return dataConsulta;
    }

    /**
     * @param dataConsulta the dataConsulta to set
     */
    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    /**
     * @return the agendamentoFeitoDia
     */
    public String getAgendamentoFeitoDia() {
        return agendamentoFeitoDia;
    }

    /**
     * @param agendamentoFeitoDia the agendamentoFeitoDia to set
     */
    public void setAgendamentoFeitoDia(String agendamentoFeitoDia) {
        this.agendamentoFeitoDia = agendamentoFeitoDia;
    }

    /**
     * @return the quemAgendou
     */
    public long getQuemAgendou() {
        return quemAgendou;
    }

    /**
     * @param quemAgendou the quemAgendou to set
     */
    public void setQuemAgendou(long quemAgendou) {
        this.quemAgendou = quemAgendou;
    }

    /**
     * @return the quemAgendouTemp
     */
    public String getQuemAgendouTemp() {
        return quemAgendouTemp;
    }

    /**
     * @param quemAgendouTemp the quemAgendouTemp to set
     */
    public void setQuemAgendouTemp(String quemAgendouTemp) {
        this.quemAgendouTemp = quemAgendouTemp;
    }

    /**
     * @return the statusConsulta
     */
    public String getStatusConsulta() {
        return statusConsulta;
    }

    /**
     * @param statusConsulta the statusConsulta to set
     */
    public void setStatusConsulta(String statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

    /**
     * @return the pacienteID
     */
    public long getPacienteID() {
        return pacienteID;
    }

    /**
     * @param pacienteID the pacienteID to set
     */
    public void setPacienteID(long pacienteID) {
        this.pacienteID = pacienteID;
    }

    /**
     * @return the pacienteTemp
     */
    public String getPacienteTemp() {
        return pacienteTemp;
    }

    /**
     * @param pacienteTemp the pacienteTemp to set
     */
    public void setPacienteTemp(String pacienteTemp) {
        this.pacienteTemp = pacienteTemp;
    }

    /**
     * @return the profissionalID
     */
    public long getProfissionalID() {
        return profissionalID;
    }

    /**
     * @param profissionalID the profissionalID to set
     */
    public void setProfissionalID(long profissionalID) {
        this.profissionalID = profissionalID;
    }

    /**
     * @return the profissionalTemp
     */
    public String getProfissionalTemp() {
        return profissionalTemp;
    }

    /**
     * @param profissionalTemp the profissionalTemp to set
     */
    public void setProfissionalTemp(String profissionalTemp) {
        this.profissionalTemp = profissionalTemp;
    }

    /**
     * @return the consultorioID
     */
    public long getConsultorioID() {
        return consultorioID;
    }

    /**
     * @param consultorioID the consultorioID to set
     */
    public void setConsultorioID(long consultorioID) {
        this.consultorioID = consultorioID;
    }

    /**
     * @return the lembreteSMS
     */
    public String getLembreteSMS() {
        return lembreteSMS;
    }

    /**
     * @param lembreteSMS the lembreteSMS to set
     */
    public void setLembreteSMS(String lembreteSMS) {
        this.lembreteSMS = lembreteSMS;
    }

    /**
     * @return the lembreteEMAIL
     */
    public String getLembreteEMAIL() {
        return lembreteEMAIL;
    }

    /**
     * @param lembreteEMAIL the lembreteEMAIL to set
     */
    public void setLembreteEMAIL(String lembreteEMAIL) {
        this.lembreteEMAIL = lembreteEMAIL;
    }

    /**
     * @return the deletado
     */
    public String getDeletado() {
        return deletado;
    }

    /**
     * @param deletado the deletado to set
     */
    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }

    /**
     * @return the horarioInicioConsulta
     */
    public String getHorarioInicioConsulta() {
        return horarioInicioConsulta;
    }

    /**
     * @param horarioInicioConsulta the horarioInicioConsulta to set
     */
    public void setHorarioInicioConsulta(String horarioInicioConsulta) {
        this.horarioInicioConsulta = horarioInicioConsulta;
    }

    /**
     * @return the horarioFimConsulta
     */
    public String getHorarioFimConsulta() {
        return horarioFimConsulta;
    }

    /**
     * @param horarioFimConsulta the horarioFimConsulta to set
     */
    public void setHorarioFimConsulta(String horarioFimConsulta) {
        this.horarioFimConsulta = horarioFimConsulta;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the fixo
     */
    public String getFixo() {
        return fixo;
    }

    /**
     * @param fixo the fixo to set
     */
    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
}
