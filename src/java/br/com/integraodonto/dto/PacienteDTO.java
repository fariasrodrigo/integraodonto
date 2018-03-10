package br.com.integraodonto.dto;

public class PacienteDTO {

    //informações
    private long id;
    private String nome;
    private String sexo;
    private String nascimento;
    private String cpf;
    private String rg;
    private String descricao;
    private String deletado; //sim ou não
    private long contatoID; //foreign key
    private long enderecoID; //foreign key
    private long consultorioID; //foreign key
    private long planoID; //foreign key

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

        
}
