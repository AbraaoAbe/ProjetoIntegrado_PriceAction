# Projeto Integrado PriceAction

O projeto Price Action baseia-se em uma plataforma com intuito de facilitar a vizualização  de gráficos de ativos da bolsa de valores. O site disponibiliza gráficos de linha e de candleStick em intervalos de tempo de 20 dias, semanas e meses. Os dados apresentados provém de uma API chamada Alpha Vantage com dados atualizados diariamente. O front-end da aplicação foi desenhado com bootstrap e gráficos do google charts.

## Instalação
Para utilizar o PriceAction, é necessário clonar o repositório do github e executar o programa para gerar o servidor local, através do springboot.

## Configuração para Desenvolvimento
Todas as dependências já vem instaladas quando clona o github, logo basta acessar o localhost do seu computador.

## Histórico de lançamentos

* Versão 0.2:
    * Front-End com os graficos com dados fictícios.
* Versão 0.4:
    * Implementação dos GET's da API alphavantage.
* Versão 0.6:
    * Conexão Front-Back-End. Dados dos gráficos conforme os dados da API.
    * Textos da página home do sistema.
* Versão 0.8:
    * Implementação da busca de ativos no Sistema.
    * Mensagem de aviso quando um ativo que não existe é buscado.
* Versão 1.0:
    * Sistema PriceAction finalizado.

## Autoria
* Alex Santos de Oliveira
* Abraão Jesus dos Santos
* Miguel Almeida Carlini
---

# Tecnologias usadas no desenvolvimento do trabalho 
1. Como utilizar os graficos do [Google Charts](https://developers.google.com/chart/interactive/docs/quick_start)
2. [Spring Boot](https://spring.io/projects/spring-boot) para criar um servidor local e fazer testes.
3. [Thymeleaf](https://www.baeldung.com/thymeleaf-in-spring-mvc) para a integração do back-end com o fron-end e também para colocar os dados dos gráficos neste [link](https://www.wimdeblauwe.com/blog/2021/01/05/using-google-charts-with-thymeleaf/)
4. [Bootstrap](https://getbootstrap.com/docs/5.1/getting-started/introduction/) para algumas estruturas HTML
