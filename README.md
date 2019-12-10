# API de produtos e pedidos de compras

A aplicação é uma API de produtos e pedidos de compras que possam ser confirmados e cancelados.  

Para executar a API é necessário criar um banco de dados vazio no postgresql e substituir alguns dados no application.properties

- Porta que deseja rodar a aplicação:
`server.port=10000` 

- Endereço do banco de dados:  
`spring.datasource.url=jdbc:postgresql://localhost:5432/challenge?useUnicode=true&characterEncoding=UTF-8`  
Trocar **localhost:5432/challenge** pelo endereço do banco de dados em branco que criou  

- Usuário e senha do banco de dados criado:  
`spring.datasource.username=postgres`  
`spring.datasource.password=postgres`

## Abaixo estão relacionados os endpoints da aplicação e alguns exemplos de resposta

### Fabricantes

- Listagem dos fabricantes cadastrados

Solicitação:
`GET localhost:10000/manufacturers?page=0&size=10`  

page = Número da página  
size = Quantidade de resultados por página  

Resposta:

```json
[
  {
    "id": 1,
    "name": "Frimesa"
  },
  {
    "id": 2,
    "name": "Quality farm goods"
  }
]
```

- Consulta dos detalhes de um fabricante usando o identificador

Solicitação:
`GET localhost:10000/manufacturers/2`

Resposta:

```json
{
    "id": 2,
    "name": "Quality farm goods"
}
```

- Inserção de novos fabricantes

Solicitação:
`POST localhost:10000/manufacturers`  
Body:
```json
{
  "name": "Quality farm goods",
}
```

- Atualização de fabricante usando o seu identificador

Solicitação:
`PUT localhost:10000/manufacturers/2`  
Body:
```json
{
  "name": "Quality farm goods",
}
```

Resposta:

```json
{
    "id": 2,
    "name": "Quality farm goods"
}
```

- Remoção de um fabricante

Solicitação:
`DELETE localhost:10000/manufacturers/2`

### Produtos

- Listagem dos produtos cadastrados

Solicitação:
`GET localhost:10000/products?page=0&size=10`  

page = Número da página  
size = Quantidade de resultados por página  

Resposta:

```json
[
  {
    "id": 25,
    "name": "Orange juice"
  },
  {
    "id": 26,
    "name": "Apple juice"
  }
]
```

- Consulta dos detalhes de um produto usando o identificador

Solicitação:
`GET localhost:10000/products/25`

Resposta:

```json
{
  "id": 25,
  "name": "Orange juice",
  "description": "Delicious natural orange juice. No addition of apples to fool consumers.",
  "barcode": "8901072002478",
  "manufacturer": {
    "id": 2,
    "name": "Quality farm goods"
  },
  "unitPrice": 18.55
}
```

- Inserção de novos produtos

Solicitação:
`POST localhost:10000/products`  

Body:
```json
{
  "name": "Grape juice",
  "description": "Natural grape juice",
  "barcode": "7002085002679",
  "idManufacturer": 2,
  "unitPrice": 25.89
}
```

- Atualização de produtos usando o seu identificador

Solicitação:
`PUT localhost:10000/products/25`  

Body:
```json
{
  "name": "Grape juice",
  "description": "Natural grape juice",
  "barcode": "7002085002679",
  "idManufacturer": 2,
  "unitPrice": 25.89
}
```

Resposta:

```json
{
  "id": 27,
  "name": "Grape juice",
  "description": "Natural grape juice",
  "barcode": "7002085002679",
  "manufacturer": {
    "id": 2,
    "name": "Quality farm goods"
  },
  "unitPrice": 25.89
}
```

- Remoção de um produto

Solicitação:
`DELETE localhost:10000/products/25`

----------

- Cadastro de um pedido

Solicitação:
`POST localhost:10000/orders`

```json
{
  "products": [
    { "id": 25, "units": 2 },
    { "id": 26, "units": 2.25 },
    { "id": 29, "units": 1 },
  ],
  "consumer": {
    "name": "John Doe",
    "phone": "+554512345678",
    "email": "some@one.com"
  },
  "payment": {
    "mode": "bank slip",
    "installments": 3
  },
  "delivery": {
    "mode": "in-store withdrawal"
  }
}
```

- Consulta de um pedido

Solicitação:
`GET localhost:10000/orders/2`

Resposta:

```json
{
  "id": 2,
  "status": "pending confirmation",
  "products": [
    { 
      "id": 25, 
      "name": "Orange juice", 
      "units": 2, 
      "unitPrice": 10, 
      "amount": 20 
    }, { 
      "id": 26, 
      "name": "Apple juice", 
      "units": 2.25, 
      "unitPrice": 15.50, 
      "amount": 34.87 
    }, { 
      "id": 29, 
      "name": "Grape juice", 
      "units": 1, 
      "unitPrice": 36.65, 
      "amount": 36.65 
    }
  ],
  "consumer": {
    "id": 3,
    "name": "John Doe",
    "phone": "+554512345678",
    "email": "some@one.com"
  },
  "payment": {
    "id": 12,
    "mode": "bank slip",
    "amount": 91.52,
    "installments": 3,
    "installmentValue": 30.51
  },
  "delivery": {
    "mode": "in-store withdrawal"
  }
}
```

- Cancelamento de um pedido

- Confirmação de um pedido