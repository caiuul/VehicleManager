# VehicleManager

Aplicação para registro e gerenciamento de veículos pessoais, com autenticação JWT. Permite cadastro de usuários, login seguro, cadastro/listagem/atualização/remoção de carros e registro de manutenções.

> ⚠️ Projeto em desenvolvimento ativo. Alguns pontos como: validação, tratamento global de exceções, e testes ainda estão sendo implementados.

## Tecnologias

- **Java** + **Spring Boot**
- **PostgreSQL** (banco de dados relacional)
- **Spring Security + JWT** (autenticação via token, sem provedor externo)
- **MapStruct** para mapeamento entre Entity e DTO
- **JUnit 5**, **Mockito** e **MockMvc/Testcontainers** para testes automatizados

## Arquitetura

```
Request (JSON)
   >>> Controller (@RestController)
        >>> Service (regra de negócio)
             >>> Mapper (MapStruct — Entity <-> DTO)
                  >>> Repository (Spring Data JPA)
                       >>> PostgreSQL
        <<< retorna XxxResponseDTO
   <<< ResponseEntity<T>
```

A autenticação é feita por um `SecurityFilter` que valida o `Bearer token` no header `Authorization`, com `SecurityConfig` liberando apenas `/auth/login` e `/auth/register` — todo o resto exige token válido.

## Endpoints

### Autenticação (`/auth`) — não exigem token

| Nome | Método | URL | Body (raw JSON) |
|---|---|---|---|
| Registrar usuário | POST | `/auth/register` | `{"name": "caiul", "password": "123456"}` |
| Login | POST | `/auth/login` | `{"name": "caiul", "password": "123456"}` → retorna `{"token": "..."}` |

### Carros — exigem header `Authorization: Bearer {token}`

| Nome | Método | URL | Body (raw JSON) |
|---|---|---|---|
| Adicionar carro | POST | `/addcar` | `{"model": "Civic", "licensePlate": "ABCD-123", "year": 2020}` |
| Listar carros | GET | `/listcars` | — (sem body) |
| Atualizar carro | PUT | `/updatecar/{id}` | `id` no path + `{"model": "Civic", "licensePlate": "ABCD-123", "year": 2021}` |
| Deletar carro | DELETE | `/deletecar/{id}` | `id` no path, sem body |

### Manutenção — exigem header `Authorization: Bearer {token}`

| Nome | Método | URL | Body (raw JSON) |
|---|---|---|---|
| Adicionar manutenção | POST | `/addMaint` | `{"carId": 1, "description": "Troca de óleo", "date": "2026-08-20", "price": 250.00}` |
| Listar manutenções do carro | GET | `/listMaint/{carId}` | `carId` no path, sem body |
| Deletar manutenção | DELETE | `/deleteMaint` | `{"maintenanceId": 1, "carId": 1}` |

## Testando no Postman

1. Rode `POST /auth/register` para criar um usuário.
2. Rode `POST /auth/login` e copie o `token` da resposta.
3. Em qualquer rota protegida, vá na aba **Authorization** → tipo **Bearer Token** → cole o token.
4. Para rotas com corpo, use a aba **Body** → **raw** → **JSON**.

## Configuração local

O banco PostgreSQL local roda na porta `5433` (em vez da padrão `5432`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/nome_do_banco
```

## Status atual / Roadmap

**Em andamento:**
- [ ] Handler global de exceções com `@ControllerAdvice`
- [ ] Testes automatizados (JUnit, Mockito, Testcontainers)

**Próximos passos:**
- [ ] Documentação da API com Swagger/OpenAPI
- [ ] Deploy (Render ou Railway)
