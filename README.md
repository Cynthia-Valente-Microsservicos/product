# product

Módulo compartilhado que define a interface do microsserviço de produtos. Contém os DTOs (`ProductIn`, `ProductOut`) e o cliente Feign (`ProductController`) usados por outros serviços para se comunicar com o `product-service`.

## Visão geral

Este módulo **não é uma aplicação executável** — é uma biblioteca Maven. Ele é declarado como dependência em qualquer serviço que precise chamar o `product-service` via OpenFeign.

```
api/product          ← este módulo (interface + DTOs)
api/product-service  ← implementação real do serviço
```

## Dependência Maven

```xml
<dependency>
    <groupId>store</groupId>
    <artifactId>product</artifactId>
    <version>1.0.0</version>
</dependency>
```

## DTOs

### `ProductIn` — corpo da requisição de criação

| Campo   | Tipo     | Descrição                      |
|---------|----------|--------------------------------|
| `name`  | `String` | Nome do produto                |
| `price` | `Float`  | Preço unitário                 |
| `unit`  | `String` | Unidade de medida (ex: kg, un) |

```java
ProductIn.builder()
    .name("Arroz")
    .price(5.99f)
    .unit("kg")
    .build();
```

### `ProductOut` — corpo da resposta

| Campo   | Tipo     | Descrição             |
|---------|----------|-----------------------|
| `id`    | `String` | UUID do produto       |
| `name`  | `String` | Nome do produto       |
| `price` | `Float`  | Preço unitário        |
| `unit`  | `String` | Unidade de medida     |

## Interface — `ProductController`

Cliente Feign apontando para `http://product:8080`. Para usar em outro serviço, habilite com `@EnableFeignClients` e injete a interface normalmente.

| Método   | Path                     | Header obrigatório | Corpo       | Resposta            |
|----------|--------------------------|--------------------|-------------|---------------------|
| `POST`   | `/products`              | `role: ADMIN`      | `ProductIn` | `201 Created`       |
| `GET`    | `/products`              | —                  | —           | `List<ProductOut>`  |
| `GET`    | `/products/{id}`         | —                  | —           | `ProductOut`        |
| `DELETE` | `/products/{id}`         | `role: ADMIN`      | —           | `204 No Content`    |
| `GET`    | `/products/health-check` | —                  | —           | `200 OK`            |

> Os endpoints de escrita exigem o header `role: ADMIN`. Sem ele o serviço retorna `403 Forbidden`.

## Tecnologias

| Tecnologia             | Versão   |
|------------------------|----------|
| Java                   | 25       |
| Spring Boot            | 4.0.6    |
| Spring Cloud           | 2025.1.1 |
| Spring Cloud OpenFeign | —        |
| Lombok                 | —        |

## Build

```bash
cd api/product
mvn clean install
```
