package com.wiseconsumo.service;

import com.wiseconsumo.model.Abastecimento;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.math.RoundingMode;
import java.util.stream.Collectors;


    public class CalculadoraEstatisticas {

        public BigDecimal calcularTotalGasto(List<Abastecimento> historico){
            BigDecimal totalGasto = BigDecimal.ZERO;

            for (Abastecimento a : historico) {
                totalGasto = totalGasto.add(a.getValorGasto());
            }
            return totalGasto;
        }


        public double calcularTotalLitros(List<Abastecimento> historico) {
            double totalLitros = 0.0;

            for (Abastecimento a : historico) {
                totalLitros = totalLitros + a.getLitrosAbastecidos();
            }
            return totalLitros;
        }

        public BigDecimal calcularPrecoMedioCombustivel(List<Abastecimento> historico) {
            if (historico.isEmpty()) {
                return BigDecimal.ZERO;
            }

            BigDecimal totalGasto = calcularTotalGasto(historico);
            double totalLitros = calcularTotalLitros(historico);

            BigDecimal litrosBD = BigDecimal.valueOf(totalLitros);

            return totalGasto.divide(litrosBD, 2, RoundingMode.HALF_UP);
        }

//Calculos da quilometragem por litro:
        public void calcularEExibirKML(List<Abastecimento> historico){
            double quilometragemRodado;

            if(historico.size() < 2){
                System.out.println("Aviso:Cadastre pelo menos 2 abastecimentos para ver o KM/L.");
                return;
            }


            for(int i = 1; i < historico.size(); i++) {
            Abastecimento atual = historico.get(i);
            Abastecimento anterior = historico.get(i-1);

            double distancia = atual.getQuilometragemAtual() - anterior.getQuilometragemAtual();
            double kml = distancia / atual.getLitrosAbastecidos();



                System.out.printf("Entre %s e %s: %.1f km/l%n",
                        anterior.getData(), atual.getData(), kml);

            }

        }

        public double calcularMediaGeralKML(List<Abastecimento> historico) {
            // Precisamos de pelo menos 2 registros para ter um ponto de partida e um de chegada
            if (historico.size() < 2) {
                return 0.0;
            }

            // 1. Calculamos a distância total (Último KM - Primeiro KM)
            double kmInicial = historico.get(0).getQuilometragemAtual();
            double kmFinal = historico.get(historico.size() - 1).getQuilometragemAtual();
            double distanciaTotal = kmFinal - kmInicial;

            // 2. Somamos os litros gastos (IMPORTANTE: Começando do índice 1)
            double litrosConsumidos = 0.0;
            for (int i = 1; i < historico.size(); i++) {
                litrosConsumidos = litrosConsumidos + historico.get(i).getLitrosAbastecidos();
            }

            // 3. Retornamos a média final
            return distanciaTotal / litrosConsumidos;
        }

//Calculos da distancia
        //Distancia Total entre todas as viagems
        public double calcularDistanciaTotal(List<Abastecimento> historico) {
            if (historico.isEmpty()) return 0;

            double kmInicial = historico.get(0).getQuilometragemAtual();
            double kmFinal = historico.get(historico.size() - 1).getQuilometragemAtual();

            return kmFinal - kmInicial;
        }


        //Outros:

        //Filtro por data

        public List<Abastecimento> filtrarPorDatas(List<Abastecimento> historico, LocalDate inicio, LocalDate fim) {
            return historico.stream()
                    .filter(a -> !a.getData().isBefore(inicio) && !a.getData().isAfter(fim))
                    .toList(); // Pega o resultado do filtro e transforma numa Lista de novo
        }
 }
