package br.com.gogoplay.app.infra.game.domain.errors;

public class GameErrors {

    public static final String GAME_CREATE_NOT_INFORMED = "JSON não foi passado no corpo da requisição";

    public static final String GAME_UPDATE_NOT_INFORMED = "JSON não foi passado no corpo da requisição";

    public static final String CODE_NOR_INFORMED = "Codigo do jogo não foi informadoa";

    public static final String GAME_NOT_FOUND_IN_DATABASE = "Jogo não encontrado no banco de dados";
    public static final String GAME_CODE_GAS_TO_BE_GREATER_THAN_ZERO = "Codigo do jogo tem que ser maior que zero";
    public static final String NAME_NOT_INFORMED = "Nome do jogo não informado";

    public static final String DESCRIPTION_NOT_INFORMED = "Descrição do jogo não informado";

    public static final String BET_MULTIPLIER_OR_FINAL_PRIZE_NOT_INFORMED = "Multiplicador da aposta ou premio final deve ser informado";

    public static final String GAME_TYPE_NOT_INFORMED = "Tipo de jogo não informado";

    public static final String INITIAL_DATE_NOT_INFORMED = "Data inicial não informado";

    public static final String FINAL_DATE_NOT_INFORMED = "Data final não informado";

    public static final String DRAW_DATE_NOT_INFORMED = "Data sorteio não informado";

    public static final String TYPE_NOT_REGISTERED = "Tipo de jogo não registrado";

    public static final String DRAW_DATE_IS_BEFORE_INITIAL_DATE = "Data sorteio não pode ser menor que data inicial do jogo";

    public static final String FINAL_DATE_IS_BEFORE_INITIAL_DATE = "Data final não pode ser menor que data inicial do jogo";
}
