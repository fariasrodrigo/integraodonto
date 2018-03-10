package br.com.integraodonto.password;

public class PasswordBox {

    private final String databaseUsuario = "integraodonto";
    private final String databaseSenha = "Sv87n8?e-MNi";
    private final String databaseUrl = "jdbc:mysql://den1.mysql5.gear.host:3306/integraodonto?autoReconnect=true&useSSL=false";

    /**
     * @return the databaseUsuario
     */
    public String getDatabaseUsuario() {
        return databaseUsuario;
    }

    /**
     * @return the databaseSenha
     */
    public String getDatabaseSenha() {
        return databaseSenha;
    }

    /**
     * @return the databaseUrl
     */
    public String getDatabaseUrl() {
        return databaseUrl;
    }

}
