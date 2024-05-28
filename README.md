# Tech Challenge Auto Atendimento

Essa aplicação serve para um cliente solicitar um pedido de produtos de uma lanchonete a partir de uma interface eletrônica.
Ela possui as seguintes funcionalidades para o Estabelecimento e o Cliente interagirem:
 - Cadastro, edição e exclusão de produtos
 - Cadastro e identificação de clientes
 - Cadastro, atualização e consulta de pedidos

# Como executar localmente:
 
## Pré-requisitos:
 - Docker instalado na máquina

## Passo a passo:
 - Executar o docker
 - Abrir um terminal de comandos (git bash por exemplo)
 - Executar o comando: docker build -t autoatendimento:1.0 .
 - Executar o comando: docker-compose up -d
 - Abrir o browser e digitar o seguinte caminho: http://localhost:8080/swagger-ui/index.html
 - Executar os endpoints através da especificação Open API
 