public class Utensilios implements ItemEmprestavel {
    private String descricao;
    private String material;

    //Construtor Utensilios
    public Utensilios(String descricao, String material) {
        this.descricao = descricao;
        this.material = material;
    }

    public String getDesc(){
        return "Utensílio: " + descricao + " (Material: " + material + ")";
    }
}