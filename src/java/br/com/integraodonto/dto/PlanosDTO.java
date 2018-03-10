package br.com.integraodonto.dto;

public class PlanosDTO {

    private long id;
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
    
    
}
