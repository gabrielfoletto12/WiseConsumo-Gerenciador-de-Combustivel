package com.wiseconsumo.util;

import com.wiseconsumo.model.Abastecimento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class GeradorDeDados {

    public static void preencherHistorico(List<Abastecimento> lista) {
        // --- MÊS PASSADO (MARÇO 2026) ---
        // 1º Abastecimento (Ponto de partida)
        lista.add(new Abastecimento(50000.0, 40.0, new BigDecimal("220.00"), LocalDate.of(2026, 3, 1)));

        // 2º Abastecimento (420km depois)
        lista.add(new Abastecimento(50420.0, 38.0, new BigDecimal("210.50"), LocalDate.of(2026, 3, 12)));

        // 3º Abastecimento (450km depois)
        lista.add(new Abastecimento(50870.0, 41.0, new BigDecimal("230.00"), LocalDate.of(2026, 3, 25)));

        // --- ESTE MÊS (ABRIL 2026) ---
        // 4º Abastecimento (Início de Abril - 430km depois do último de Março)
        lista.add(new Abastecimento(51300.0, 39.5, new BigDecimal("225.00"), LocalDate.of(2026, 4, 2)));

        // 5º Abastecimento (400km depois)
        lista.add(new Abastecimento(51700.0, 37.0, new BigDecimal("212.00"), LocalDate.of(2026, 4, 10)));

        // 6º Abastecimento (Último registo - 350km depois)
        lista.add(new Abastecimento(52050.0, 35.0, new BigDecimal("205.00"), LocalDate.of(2026, 4, 12)));
    }
}