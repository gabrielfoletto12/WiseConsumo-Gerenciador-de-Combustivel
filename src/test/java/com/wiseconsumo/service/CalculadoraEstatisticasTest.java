package com.wiseconsumo.service;

import com.wiseconsumo.model.Abastecimento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraEstatisticasTest {

    // TESTE 1: CENÁRIO DE USO CORRETo
    @Test
    void deveCalcularTotalDeLitrosCorretamente() {
        CalculadoraEstatisticas calc = new CalculadoraEstatisticas();
        List<Abastecimento> listaDeTeste = new ArrayList<>();
        listaDeTeste.add(new Abastecimento(1000, 40.0, new BigDecimal("200.00"), LocalDate.now()));
        listaDeTeste.add(new Abastecimento(1050, 10.5, new BigDecimal("55.00"), LocalDate.now()));

        double totalCalculado = calc.calcularTotalLitros(listaDeTeste);

        assertEquals(50.5, totalCalculado, 0.001, "A soma dos litros deve ser exatamente 50.5");
    }

    // TESTE 2: CASO LIMITE (Lista Vazia)
    @Test
    void deveRetornarPrecoMedioZeroQuandoHistoricoEstiverVazio() {
        CalculadoraEstatisticas calc = new CalculadoraEstatisticas();
        List<Abastecimento> listaVazia = new ArrayList<>();

        BigDecimal precoMedio = calc.calcularPrecoMedioCombustivel(listaVazia);

        assertEquals(BigDecimal.ZERO, precoMedio, "Deve retornar ZERO quando a lista estiver vazia.");
    }

    // TESTE 3: REGRA DE NEGÓCIO CENTRAL (Média Ponderada)
    @Test
    void deveCalcularPrecoMedioRealCorretamente() {
        CalculadoraEstatisticas calc = new CalculadoraEstatisticas();
        List<Abastecimento> listaDeTeste = new ArrayList<>();
        listaDeTeste.add(new Abastecimento(1000, 40.0, new BigDecimal("200.00"), LocalDate.now()));
        listaDeTeste.add(new Abastecimento(1050, 10.0, new BigDecimal("60.00"), LocalDate.now()));

        BigDecimal precoMedio = calc.calcularPrecoMedioCombustivel(listaDeTeste);

        assertEquals(new BigDecimal("5.20"), precoMedio, "A média deve ser 5.20 (260 reais / 50 litros).");
    }
}