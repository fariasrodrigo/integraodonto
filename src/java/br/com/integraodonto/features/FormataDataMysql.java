package br.com.integraodonto.features;

public class FormataDataMysql {

    public String formataDataMysql(String recebeData) {

        String dia = recebeData.substring(0, 2);
        String mes = recebeData.substring(3, 5);
        String ano = recebeData.substring(6);
        String data = ano + "-" + mes + "-" + dia;
        return data;
    }
}
