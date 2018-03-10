package br.com.integraodonto.features;

public class Menu {

    public String menu(String nivel) {

        if (nivel == null) {
            throw new IllegalArgumentException("nivel não deve ser nulo");
        }
        String itens = "";
        if ("admin".equals(nivel)) {
            itens = "";

        } else if ("usuario".equals(nivel)) {
            itens = "hidden";
        }
        return itens;
    }

}
