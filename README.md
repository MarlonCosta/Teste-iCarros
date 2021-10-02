# Teste iCarros

<br/>

Projeto para seleção de vaga de backend na iCarros, sendo desenvolvido em Java 8, porém tendo compatibilidiade com versões superiores.

## Desafios

Os desafios de lógica (com exceção da API) se encontram no pacote [com.icarros.challenges](https://github.com/MarlonCosta/Teste-iCarros/tree/master/src/main/java/com/icarros/challenges)

- [Questão 1 - Fizzing 'n buzzing](https://github.com/MarlonCosta/Teste-iCarros/blob/master/src/main/java/com/icarros/challenges/FizzingNBuzzing.java)
- [Questão 2 - Angry Marvin Birds](https://github.com/MarlonCosta/Teste-iCarros/blob/master/src/main/java/com/icarros/challenges/AngryMavinBirds.java)
- [Questão 3 - Creepy hours](https://github.com/MarlonCosta/Teste-iCarros/blob/master/src/main/java/com/icarros/challenges/CreepyHours.java)
- [Questão 4 - We are the champions, my friend](https://github.com/MarlonCosta/Teste-iCarros/blob/master/src/main/java/com/icarros/challenges/WeAreTheChampions.java)
- [Questão 5 - Venting the costs](https://github.com/MarlonCosta/Teste-iCarros/blob/master/src/main/java/com/icarros/challenges/VentingTheCosts.sql)
- [Questão 6 - API descrita a seguir, código fonte neste link](https://github.com/MarlonCosta/Teste-iCarros/tree/master/src/main/java/com/icarros/formula1)
- [Questão 7 – Robot](https://github.com/MarlonCosta/Teste-iCarros/blob/master/src/main/java/com/icarros/challenges/Robot.java)

## Execução da API
Para executar a API, é possível utilizar o [Dockerfile](https://github.com/MarlonCosta/Teste-iCarros/blob/master/Dockerfile) do projeto:

  ```docker build -t icarros-formula1 . && docker run -p 8080:8080 --name icarros-formula1 icarros-formula1```
  
  ou caso prefira rodar sem o uso do container, basta executar os comandos a seguir em ordem, na raiz do projeto:
  
  ```mvn clean package```
  
  ```java -jar ./target/formula1-0.0.1-SNAPSHOT.jar```
  
  Após a execução, na raiz da API (se utilizado o comando acima, estará localizada em [http://localhost:8080/](http://localhost:8080/)) temos a documentação da mesma, mostrando os endpoints no Swagger-UI.

## Ferramental
  Foram usados no projeto:
  - ``` Java ```
  - ``` Maven ```
  - ``` Spring ```
  - ``` Swagger ```
  - ``` SpringFox ```
  - ``` Docker ```
  - ``` JUnit ```
  
  Dentre outros...
