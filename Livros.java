public class Livros implements ItemEmprestavel {
    private String titulo;
    private String autor;
    private String editora;
    private int ano;

    //Construtor livro
    public Livros(String titulo, String autor, String editora, int ano){
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano = ano;
    }
    //Retorna descrição formatada do livro
    public String getDesc(){
        return "Livro: " + titulo + " ( " + ano + " ), de: " + autor + ", Editora: " + editora;
    }
}
