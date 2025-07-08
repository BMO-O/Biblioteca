public class Utensilios implements ItemEmprestavel {
    private String descricao;
    private String material;

    //Construtor Utensilios
    public Utensilios(String descricao, String material) {
        this.descricao = descricao;
        this.material = material;
    }

    //Retorna descrição formatada do utensílio
    public String getDesc(){
        return "Utensílio: " + descricao + " (Material: " + material + ")";
    }
}
