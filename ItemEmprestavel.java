import java.io.Serializable;

/*
Interface que define o contrato para itens que podem ser emprestados.
Estende a interface Serializable para permitir que os objetos sejam salvos em arquivos.
 */

public interface ItemEmprestavel extends Serializable {
    //Retorna a descrição textual do item emprestável.
    String getDesc();
}
