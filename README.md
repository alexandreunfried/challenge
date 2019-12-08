# API de produtos e pedidos de compras

A aplicação é uma API de produtos e pedidos de compras que possam ser confirmados e cancelados. Abaixo estão relacionados os endpoints da aplicação e alguns exemplos de resposta.

----------

- Listagem dos produtos cadastrados

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
- Cadastro de um pedido

Solicitação:

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