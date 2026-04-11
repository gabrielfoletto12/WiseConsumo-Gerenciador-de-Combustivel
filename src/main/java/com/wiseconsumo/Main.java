package com.wiseconsumo;

import java.util.Scanner;
import com.wiseconsumo.model.Abastecimento;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main (String[] args){

        Scanner leitor = new Scanner(System.in);

        while(true){
            System.out.println("---------------- MENU WiseConsumo ------------");
            System.out.println("OPÇÃO 1: Regristrar Abastecimento");
            System.out.println("OPÇÃO 2: Historico de Abastecimento (Em Breve)");
            System.out.println("OPÇÃO 3: Sair do menu");
            System.out.println("Digite a opção desejada: ");

            int opcao = leitor.nextInt();

            if(opcao==1){
                System.out.println("------Insira as informações solicitadas------------");

                System.out.println("Digite a kilometragem atual: ");
                double km = leitor.nextDouble();

                System.out.println("Digite a quantidade de litros abastecidos: ");
                double litros = leitor.nextDouble();

                System.out.println("Digite o valor total gasto: ");
                BigDecimal valor = leitor.nextBigDecimal();

                LocalDate data = LocalDate.now();


                Abastecimento abastecimento_1 = new Abastecimento(km,litros,valor,data);
                abastecimento_1.mostrarInfo();

                System.out.println("\n");
//                System.out.println("Abastecimento registrado com sucesso \n Resumo: \n - Quilometragem atual: "+abastecimento_1.getQuilometragemAtual()+"\n- Valor gasto: R$"+abastecimento_1.getValorGasto());

            }else if(opcao==3){
                System.out.println("Saiu do menu");
                break;
            }else{
                System.out.println("Opção Inválida, digite uma opção válida");
            }

        }

    }
}


        /*
        Abastecimento abastecimento_1 = new Abastecimento(10500,5,new BigDecimal(30), LocalDate.now());

        System.out.println("=== TESTE DE ABASTECIMENTO ===");
        System.out.println("KM do Carro: " + abastecimento_1.getQuilometragemAtual());
        System.out.println("Litros Colocados: " + abastecimento_1.getLitrosAbastecidos());
        System.out.println("Valor Pago: R$ " + abastecimento_1.getValorGasto());
        System.out.println("Data do Registro: " + abastecimento_1.getData());

        abastecimento_1.setLitrosAbastecidos(8);

        System.out.println("Litros Colocados: " + abastecimento_1.getLitrosAbastecidos());

         */