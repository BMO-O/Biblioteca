import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String ARQUIVO = "emprestimos.dat";
    private static ArrayList<Emprestimos> listaEmprestimos = carregarEmprestimos();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Pegar algo emprestado");
            System.out.println("2. Devolver item");
            System.out.println("3. Ver itens em atraso");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opçao: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    pegarEmprestado();
                    break;
                case 2:
                    devolverItem();
                    break;
                case 3:
                    verItensAtrasados();
                    break;
                case 0:
                    salvarEmprestimos(listaEmprestimos);
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void pegarEmprestado() {
        System.out.println("O que deseja emprestado?");
        System.out.println("1. Livro");
        System.out.println("2. Utensílio");
        int tipo = Integer.parseInt(scanner.nextLine());

        ItemEmprestavel item;

        if (tipo == 1) {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            System.out.print("Editora: ");
            String editora = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());

            item = new Livros(titulo, autor, editora, ano);
        } else {
            System.out.print("Descrição do utensílio: ");
            String descricao = scanner.nextLine();
            System.out.print("Material: ");
            String material = scanner.nextLine();

            item = new Utensilios(descricao, material);
        }

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        LocalDate hoje = LocalDate.now();
        System.out.print("Data de devoluçao prevista (AAAA-MM-DD): ");
        LocalDate devolucao = LocalDate.parse(scanner.nextLine());

        Emprestimos emprestimo = new Emprestimos(item, email, hoje, devolucao);
        listaEmprestimos.add(emprestimo);

        System.out.println("Item emprestado com sucesso.");
    }

    private static void devolverItem() {
        if (listaEmprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }

        System.out.println("Itens emprestados:");
        for (int i = 0; i < listaEmprestimos.size(); i++) {
            Emprestimos e = listaEmprestimos.get(i);
            if (e.getDevolucaoEfetiva() == null) {
                System.out.println(i + " - " + e.getItem().getDesc());
            }
        }

        System.out.print("Digite o número do item a devolver: ");
        int indice = Integer.parseInt(scanner.nextLine());

        if (indice < 0 || indice >= listaEmprestimos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Emprestimos e = listaEmprestimos.get(indice);
        e.devolucaoFeita(LocalDate.now());
        System.out.println("Item devolvido com sucesso.");
    }

    private static void verItensAtrasados() {
        LocalDate hoje = LocalDate.now();
        boolean encontrou = false;

        for (Emprestimos e : listaEmprestimos) {
            if (e.atraso(hoje)) {
                System.out.println("- " + e.getItem().getDesc());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum item em atraso.");
        }
    }

    private static void salvarEmprestimos(ArrayList<Emprestimos> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os empréstimos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Emprestimos> carregarEmprestimos() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Emprestimos>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os empréstimos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
