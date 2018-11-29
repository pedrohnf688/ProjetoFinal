package com.example.phnf2.projetounidadefinal.modelo;

public class RelatorioProducaoLeite {

    private String IdRelatorio;
    private String TituloRelatorio;
    private String TipoRelatorio;
    private String DataRelatorio;

    public RelatorioProducaoLeite() {
    }

    public RelatorioProducaoLeite(String idRelatorio, String tituloRelatorio, String tipoRelatorio, String dataRelatorio) {
        IdRelatorio = idRelatorio;
        TituloRelatorio = tituloRelatorio;
        TipoRelatorio = tipoRelatorio;
        DataRelatorio = dataRelatorio;
    }

    public String getIdRelatorio() {
        return IdRelatorio;
    }

    public void setIdRelatorio(String idRelatorio) {
        IdRelatorio = idRelatorio;
    }

    public String getTituloRelatorio() {
        return TituloRelatorio;
    }

    public void setTituloRelatorio(String tituloRelatorio) {
        TituloRelatorio = tituloRelatorio;
    }

    public String getTipoRelatorio() {
        return TipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        TipoRelatorio = tipoRelatorio;
    }

    public String getDataRelatorio() {
        return DataRelatorio;
    }

    public void setDataRelatorio(String dataRelatorio) {
        DataRelatorio = dataRelatorio;
    }
}
