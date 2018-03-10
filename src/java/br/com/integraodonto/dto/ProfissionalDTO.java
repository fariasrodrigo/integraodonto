package br.com.integraodonto.dto;

public class ProfissionalDTO {

    //informações
    private long id;
    private String nome;
    private String sexo;
    private String especializacao;
    private String cro;
    private String cpf;
    private String rg;
    private String stats; //ativo ou inativo
    private String deletado; //sim ou não
    private int contatoID; //foreign key
    private int consultorioID; //foreign key    

    //dados de acesso
    private String login;
    private String senha;
    private String nivel; //admin ou usuário

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the especializacao
     */
    public String getEspecializacao() {
        return especializacao;
    }

    /**
     * @param especializacao the especializacao to set
     */
    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    /**
     * @return the cro
     */
    public String getCro() {
        return cro;
    }

    /**
     * @param cro the cro to set
     */
    public void setCro(String cro) {
        this.cro = cro;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the stats
     */
    public String getStats() {
        return stats;
    }

    /**
     * @param stats the stats to set
     */
    public void setStats(String stats) {
        this.stats = stats;
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
     * @return the contatoID
     */
    public int getContatoID() {
        return contatoID;
    }

    /**
     * @param contatoID the contatoID to set
     */
    public void setContatoID(int contatoID) {
        this.contatoID = contatoID;
    }

    /**
     * @return the consultorioID
     */
    public int getConsultorioID() {
        return consultorioID;
    }

    /**
     * @param consultorioID the consultorioID to set
     */
    public void setConsultorioID(int consultorioID) {
        this.consultorioID = consultorioID;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
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
