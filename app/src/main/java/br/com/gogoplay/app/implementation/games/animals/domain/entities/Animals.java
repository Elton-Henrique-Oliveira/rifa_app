package br.com.gogoplay.app.implementation.games.animals.domain.entities;

public enum Animals {

    AVESTRUZ(1, "Avestruz"),
    AGUIA(2, "Águia"),
    BURRO(3, "Burro"),
    BORBOLETA(4, "Borboleta"),
    CACHORRO(5, "Cachorro"),
    CABRA(6, "Cabra"),
    CARNEIRO(7, "Carneiro"),
    CAMELO(8, "Camelo"),
    COBRA(9, "Cobra"),
    COELHO(10, "Coelho"),
    CAVALO(11, "Cavalo"),
    ELEFANTE(12, "Elefante"),
    GALO(13, "Galo"),
    GATO(14, "Gato"),
    JACARE(15, "Jacaré"),
    LEAO(16, "Leão"),
    MACACO(17, "Macaco"),
    PORCO(18, "Porco"),
    PAVAO(19, "Pavão"),
    PERU(20, "Peru"),
    TOURO(21, "Touro"),
    TIGRE(22, "Tigre"),
    URSO(23, "Urso"),
    VEADO(24, "Veado"),
    VACA(25, "Vaca");

    private final int codigo;
    private final String descricao;

    Animals(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}