import java.time.LocalDate;
import java.io.Serializable;

/*
 Classe que representa um empréstimo de item.
 Armazena informações sobre a data de retirada, devolução prevista,
 devolução efetiva e o e-mail da pessoa que pegou emprestado.
*/

public class Emprestimos implements Serializable {

    // ======== ATRIBUTOS ========

    private ItemEmprestavel item;          
    private LocalDate retirada;             // Data em que o item foi emprestado
    private LocalDate devolucaoprevista;    // Data em que o item deve ser devolvido
    private LocalDate devolucaoefetiva;     // Data em que o item foi realmente devolvido (inicialmente null)
    private String emailAmigo;             

    // ======== CONSTRUTOR ========

    /*
    Cria um novo empréstimo com os dados fornecidos.
    A devolução efetiva inicia como null, indicando que o item ainda não foi devolvido.
    */
    
    public Emprestimos(ItemEmprestavel item, String emailAmigo, LocalDate retirada, LocalDate devolucaoprevista) {
        this.item = item;
        this.emailAmigo = emailAmigo;
        this.retirada = retirada;
        this.devolucaoprevista = devolucaoprevista;
        this.devolucaoefetiva = null;
    }

    // ======== MÉTODOS ========

    
    //Registra a devolução do item na data especificada.
     
    public void devolucaoFeita(LocalDate devolucaoefetiva) {
        this.devolucaoefetiva = devolucaoefetiva;
    }

    
    //Verifica se o item está atrasado com base na data atual.
   
    public boolean atraso(LocalDate hoje) {
        return devolucaoefetiva == null && devolucaoprevista.isBefore(hoje);
    }

    // ======== INFORMAÇÕES ========

    public ItemEmprestavel getItem() {
        return item;
    }

    public String getEmail() {
        return emailAmigo;
    }

    public LocalDate getRetirada() {
        return retirada;
    }

    public LocalDate getDevolucaoPrevista() {
        return devolucaoprevista;
    }

    public LocalDate getDevolucaoEfetiva() {
        return devolucaoefetiva;
    }

    // ======== TO STRING ========

    public String toString() {
        return  
            "\nEmail de cobrança: " + emailAmigo +
            "\nRetirado em: " + retirada +
            "\nDevolução prevista: " + devolucaoprevista +
            "\nDevolução efetiva: " + (devolucaoefetiva != null ? devolucaoefetiva : "Ainda não devolvido");
    }
}
