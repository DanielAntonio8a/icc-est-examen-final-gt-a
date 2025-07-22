package controllers;

import java.util.*;
import models.Maquina;

public class MaquinaController {

    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> stack = new Stack<>();
        for (Maquina m : maquinas) {
            if (m.getSubred() < umbral) {
                stack.push(m);
            }
        }
        return stack;
    }

    public Set<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        return new TreeSet<>(new Comparator<Maquina>() {
            @Override
            public int compare(Maquina m1, Maquina m2) {
                if (m1.getSubred() != m2.getSubred()) {
                    return Integer.compare(m1.getSubred(), m2.getSubred());
                }
                return m1.getNombre().compareTo(m2.getNombre());
            }
        }) {{
            addAll(pila);
        }};
    }

    public Map<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        Map<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina m : maquinas) {
            mapa.computeIfAbsent(m.getRiesgo(), k -> new LinkedList<>()).add(m);
        }
        return mapa;
    }

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        int maxCantidad = 0;
        int riesgoObjetivo = 0;

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int cantidad = entry.getValue().size();
            int riesgo = entry.getKey();
            if (cantidad > maxCantidad || (cantidad == maxCantidad && riesgo > riesgoObjetivo)) {
                maxCantidad = cantidad;
                riesgoObjetivo = riesgo;
            }
        }

        Queue<Maquina> grupo = mapa.get(riesgoObjetivo);
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina m : grupo) {
            resultado.push(m);
        }

        return resultado;
    }
}
