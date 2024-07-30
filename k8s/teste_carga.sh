#!/bin/bash
for i in {1..10000}; do
  curl --location 'http://localhost:8080/produtos' \
  --header 'Content-Type: application/json' \
  --data '{
      "ativo": true,
      "categoria": "Lanche",
      "descricao": "Dois hamburgueres, alface, queijo, molho especial, cebola e picles no pao com gergelim.",
      "imagem": "big_mac.jpg",
      "nome": "Big Mac",
      "preco": "15.90"
  }'
  sleep $1
done