package br.com.integraodonto.dto;

public class ConsultorioDTO {

    //informações
    private long id;
    private String nome;
    private String nomeResponsavel;
    private String cnpj;
    private String stats; //ativo ou inativo
    private String deletado; //sim ou não
    private long contatoID; //foreign key
    private long enderecoID; //foreign key

    //informações de horário
    private String horarioAbertura; //horário de abertura da empresa
    private String horarioFechamento; //horário de fechamento da empresa
    private String intervaloAbertura; //horário de inicio do intervalo de almoço
    private String intervaloFechamento; //horário de fim do intervalo de almoço

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
     * @return the nomeResponsavel
     */
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    /**
     * @param nomeResponsavel the nomeResponsavel to set
     */
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    

}
