package com.alura.conversor.modelos;

public class MonedasApis {
    private String result;
    private String base_code;
    private ConversionRates conversion_rates;

    // Getters y setters
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getBase_code() { return base_code; }
    public void setBase_code(String base_code) { this.base_code = base_code; }
    public ConversionRates getConversion_rates() { return conversion_rates; }
    public void setConversion_rates(ConversionRates conversion_rates) { this.conversion_rates = conversion_rates; }

    public static class ConversionRates {
        private double ARS; // Peso argentino
        private double BRL; // Real brasileño
        private double COP; // Peso colombiano
        private double USD;
        // Getters y setters
        public double getARS() { return ARS; }
        public void setARS(double ARS) { this.ARS = ARS; }
        public double getBRL() { return BRL; }
        public void setBRL(double BRL) { this.BRL = BRL; }
        public double getCOP() { return COP; }
        public void setCOP(double COP) { this.COP = COP; }
        public double getUSD() { return USD; }
        public void setUSD(double COP) { this.USD = USD; }
    }
}
