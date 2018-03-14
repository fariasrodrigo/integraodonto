package br.com.integraodonto.dto;

public class PacienteDTO {

    //informações
    private long id;
    private String prontuario;
    private String nome;
    private String sexo;
    private String nascimento;
    private String cpf;
    private String rg;
    private String descricao;
    private String deletado; //sim ou nao
    private long contatoID; //foreign key
    private long enderecoID; //foreign key
    private long consultorioID; //foreign key
    private long planoID; //foreign key

    //Temporarios
    private String celular;
    private String fixo;
    private String email;

    private String cep;
    private String endereco;
    private String numero;
    private String compl;
    private String bairro;
    private String cidade;
    private String estado;

    private String planoDeSaude;
    private String numeroDoCartao;
    private String nomeDoPlano;
    private String tipoDeUsuario; //titular ou dependente
    private String titularDoPlano;

    
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
     * @return the nascimento
     */
    public String getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    public long getContatoID() {
        return contatoID;
    }

    /**
     * @param contatoID the contatoID to set
     */
    public void setContatoID(long contatoID) {
        this.contatoID = contatoID;
    }

    /**
     * @return the enderecoID
     */
    public long getEnderecoID() {
        return enderecoID;
    }

    /**
     * @param enderecoID the enderecoID to set
     */
    public void setEnderecoID(long enderecoID) {
        this.enderecoID = enderecoID;
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
     * @return the planoID
     */
    public long getPlanoID() {
        return planoID;
    }

    /**
     * @param planoID the planoID to set
     */
    public void setPlanoID(long planoID) {
        this.planoID = planoID;
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

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the compl
     */
    public String getCompl() {
        return compl;
    }

    /**
     * @param compl the compl to set
     */
    public void setCompl(String compl) {
        this.compl = compl;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the planoDeSaude
     */
    public String getPlanoDeSaude() {
        return planoDeSaude;
    }

    /**
     * @param planoDeSaude the planoDeSaude to set
     */
    public void setPlanoDeSaude(String planoDeSaude) {
        this.planoDeSaude = planoDeSaude;
    }

    /**
     * @return the numeroDoCartao
     */
    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    /**
     * @param numeroDoCartao the numeroDoCartao to set
     */
    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    /**
     * @return the nomeDoPlano
     */
    public String getNomeDoPlano() {
        return nomeDoPlano;
    }

    /**
     * @param nomeDoPlano the nomeDoPlano to set
     */
    public void setNomeDoPlano(String nomeDoPlano) {
        this.nomeDoPlano = nomeDoPlano;
    }

    /**
     * @return the tipoDeUsuario
     */
    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    /**
     * @param tipoDeUsuario the tipoDeUsuario to set
     */
    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    /**
     * @return the titularDoPlano
     */
    public String getTitularDoPlano() {
        return titularDoPlano;
    }

    /**
     * @param titularDoPlano the titularDoPlano to set
     */
    public void setTitularDoPlano(String titularDoPlano) {
        this.titularDoPlano = titularDoPlano;
    }

    /**
     * @return the prontuario
     */
    public String getProntuario() {
        return prontuario;
    }

    /**
     * @param prontuario the prontuario to set
     */
    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

}
