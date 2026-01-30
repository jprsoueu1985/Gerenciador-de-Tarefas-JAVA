import model.Tarefa;
import service.GerenciadorDeTarefas;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        gerenciador.iniciarMonitoramentoAutomatico();


        int opcao;

        do {
            System.out.println("\n=== GERENCIADOR DE TAREFAS ===");
            System.out.println("1 - Cadastrar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Ver prazos próximos");
            System.out.println("4 - Alterar status da tarefa");
            System.out.println("5 - Menu de filtragem");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {

                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Prazo (AAAA-MM-DD): ");
                    LocalDate prazo = LocalDate.parse(scanner.nextLine());

                    gerenciador.adicionarTarefa(titulo, descricao, prazo);
                    break;

                case 2:
                    gerenciador.listarTarefas();
                    break;

                case 3:
                    gerenciador.verificarPrazosProximos();
                    break;

                case 4:
                    gerenciador.listarTarefas();
                    System.out.print("Informe o ID da tarefa: ");
                    int id = scanner.nextInt();

                    System.out.println("1 - PENDENTE");
                    System.out.println("2 - EM ANDAMENTO");
                    System.out.println("3 - CONCLUIDA");
                    System.out.print("Novo status: ");
                    int statusOpcao = scanner.nextInt();

                    Tarefa.Status status = null;

                    switch (statusOpcao) {
                        case 1:
                            status = Tarefa.Status.PENDENTE;
                            break;
                        case 2:
                            status = Tarefa.Status.EM_ANDAMENTO;
                            break;
                        case 3:
                            status = Tarefa.Status.CONCLUIDA;
                            break;
                        default:
                            System.out.println("Status inválido.");
                    }

                    if (status != null) {
                        gerenciador.alterarStatusTarefa(id, status);
                    }
                    break;

                case 5:
                    int opcaoFiltro;

                    do {
                        System.out.println("\n=== MENU DE FILTRAGEM ===");
                        System.out.println("1 - Exibir todas as tarefas");
                        System.out.println("2 - Filtrar por status");
                        System.out.println("3 - Ordenar por data limite");
                        System.out.println("0 - Voltar");
                        System.out.print("Escolha: ");
                        opcaoFiltro = scanner.nextInt();

                        switch (opcaoFiltro) {

                            case 1:
                                gerenciador.listarTarefas();
                                break;

                            case 2:
                                System.out.println("1 - PENDENTE");
                                System.out.println("2 - EM ANDAMENTO");
                                System.out.println("3 - CONCLUIDA");
                                System.out.print("Status: ");
                                int filtroStatus = scanner.nextInt();

                                Tarefa.Status statusFiltro = null;

                                switch (filtroStatus) {
                                    case 1:
                                        statusFiltro = Tarefa.Status.PENDENTE;
                                        break;
                                    case 2:
                                        statusFiltro = Tarefa.Status.EM_ANDAMENTO;
                                        break;
                                    case 3:
                                        statusFiltro = Tarefa.Status.CONCLUIDA;
                                        break;
                                    default:
                                        System.out.println("Status inválido.");
                                }

                                if (statusFiltro != null) {
                                    gerenciador.filtrarPorStatus(statusFiltro);
                                }
                                break;

                            case 3:
                                gerenciador.listarOrdenadoPorPrazo();
                                break;

                            case 0:
                                System.out.println("Voltando ao menu principal...");
                                break;

                            default:
                                System.out.println("Opção inválida!");
                        }

                    } while (opcaoFiltro != 0);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}

