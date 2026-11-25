package org.example.dados;

import org.example.model.Entidade;

import java.util.ArrayList;
import java.util.List;

public class Tabela<T extends Entidade> {

    private final List<T> registros = new ArrayList<>();
    private int proximoId = 1;

    public T inserir(T obj) {
        obj.setId(proximoId++);
        registros.add(obj);
        return obj;
    }

    public boolean alterar(T obj) {
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getId() == obj.getId()) {
                registros.set(i, obj);
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        return registros.removeIf(r -> r.getId() == id);
    }

    public T buscarPorId(int id) {
        for (T r : registros) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    // devolve uma cópia pra ninguém mexer na lista interna por fora
    public List<T> listarTodos() {
        return new ArrayList<>(registros);
    }
}
