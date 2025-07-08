import java.time.LocalDate;
import java.io.Serializable;

public class Emprestimos implements Serializable {
    //Informações para o emprestimo
    private ItemEmprestavel item;
    private LocalDate retirada;
    private LocalDate devolucaoprevista;
    private LocalDate devolucaoefetiva;
    private String emailAmigo;

    //Construtor Emprestimos
    public Emprestimos(ItemEmprestavel item, String emailAmigo, LocalDate retirada, LocalDate devolucaoprevista){
        this.item = item;
        this.emailAmigo = emailAmigo;
        this.retirada = retirada;
        this.devolucaoprevista = devolucaoprevista;
        this.devolucaoefetiva = null;
    }

    //Registrando devolução realizada
    public void devolucaoFeita(LocalDate devolucaoefetiva){
        this.devolucaoefetiva = devolucaoefetiva;
    }

    //Verificando se houve atraso
    public boolean atraso(LocalDate hoje){
        return devolucaoefetiva == null && devolucaoprevista.isBefore(hoje);
    }

    //Pegar info

    public ItemEmprestavel getItem(){
        return item;
    }
    public String getEmail(){
        return emailAmigo;
    }
    public LocalDate getRetirada(){
        return retirada;
    }
    public LocalDate getDevolucaoPrevista(){
        return devolucaoprevista;
    }
    public LocalDate getDevolucaoEfetiva(){
        return devolucaoefetiva;
    }

    public String toString(){
        return  
        " \nEmail de cobrança: " + emailAmigo +
        " \nRetirado em: " + retirada +
        " \nDevolução prevista: " + devolucaoprevista +
        " \nDevolução efetiva: " + devolucaoefetiva +
        "\nDevolução efetiva: " + (devolucaoefetiva != null ? devolucaoefetiva : "Ainda não devolvido");
                    
        
    }
}