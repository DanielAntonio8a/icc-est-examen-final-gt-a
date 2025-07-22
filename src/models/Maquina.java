package models;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Maquina {
    private String nombre;
    private String ip;
    private List<Integer> codigos;
    private int subred;
    private int riesgo;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
        this.subred = calcularSubred();
        this.riesgo = calcularRiesgo();
    }

    private int calcularSubred() {
        String[] octetos = ip.split("\\.");
        return Integer.parseInt(octetos[3]);
    }

    private int calcularRiesgo() {
        int suma = 0;
        for (int c : codigos) {
            if (c % 3 == 0) {
                suma += c;
            }
        }

        String sinEspacios = nombre.replace(" ", "");
        Set<Character> unicos = new HashSet<>();
        for (char c : sinEspacios.toCharArray()) {
            unicos.add(c);
        }

        return suma * unicos.size();
    }

    public String getNombre() { return nombre; }
    public String getIp() { return ip; }
    public List<Integer> getCodigos() { return codigos; }
    public int getSubred() { return subred; }
    public int getRiesgo() { return riesgo; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Maquina)) return false;
        Maquina m = (Maquina) o;
        return this.subred == m.subred && this.nombre.equals(m.nombre);
    }

    @Override
    public int hashCode() {
        return subred * 31 + nombre.hashCode();
    }

    @Override
    public String toString() {
        return nombre + " - " + ip + " (subred: " + subred + ", riesgo: " + riesgo + ")";
    }
}
