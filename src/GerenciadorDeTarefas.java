package service;

import model.Tarefa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;


public class GerenciadorDeTarefas {

    private List<Tarefa> tarefas = new ArrayList<>();
    private int contadorId = 1;

    public void adicionarTarefa(String titulo, String descricao, LocalDate prazo) {

        if (titulo == null || titulo.trim().length() <= 5) {
            System.out.println("❌ Erro: o título da tarefa deve ter pelo menos 6 caracteres.");
            return;
        }

        if (prazo.isBefore(LocalDate.now())) {
            System.out.println("❌ Erro: a data do prazo não pode ser no passado.");
            return;
        }

        Tarefa tarefa = new Tarefa(contadorId++, titulo, descricao, prazo);
        tarefas.add(tarefa);
        System.out.println("✅ Tarefa cadastrada com sucesso!");
    }

    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        tarefas.forEach(System.out::println);
    }

    public void filtrarPorStatus(Tarefa.Status status) {
        tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .forEach(System.out::println);
    }

    public void alterarStatusTarefa(int id, Tarefa.Status novoStatus) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setStatus(novoStatus);
                System.out.println("✅ Status alterado para: " + novoStatus);
                return;
            }
        }
        System.out.println("❌ Tarefa não encontrada.");
    }

    public void listarOrdenadoPorPrazo() {
        tarefas.stream()
                .sorted(Comparator.comparing(Tarefa::getDataPrazo))
                .forEach(System.out::println);
    }



    public void atualizarStatus(int id, Tarefa.Status novoStatus) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setStatus(novoStatus);
                System.out.println("✅ Status da tarefa atualizado para: " + novoStatus);
                return;
            }
        }
        System.out.println("❌ Tarefa não encontrada.");
    }


    // ⭐ OBJETIVO EXTRA
    public void verificarPrazosProximos() {
        LocalDate hoje = LocalDate.now();

        tarefas.stream()
                .filter(t -> t.getStatus() != Tarefa.Status.CONCLUIDA)
                .filter(t -> !t.getDataPrazo().isBefore(hoje))
                .filter(t -> t.getDataPrazo().isBefore(hoje.plusDays(2)))
                .forEach(t ->
                        System.out.println(
                                "⚠️ NOTIFICAÇÃO: A tarefa \"" + t.getTitulo() +
                                        "\" vence em " + t.getDataPrazo()
                        )
                );
    }

    public void iniciarMonitoramentoAutomatico() {

        CompletableFuture.runAsync(() -> {
            try {
                while (true) {
                    verificarPrazosProximos();
                    Thread.sleep(60000); // verifica a cada 1 minuto
                }
            } catch (InterruptedException e) {
                System.out.println("Monitoramento interrompido.");
            }
        });
    }

}
