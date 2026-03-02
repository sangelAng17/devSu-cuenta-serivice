# Autor : Angel Santiago Hernandez 
# PRUEBA TECNICA DEVSU


# 🧪 Prueba Técnica - Arquitectura de Microservicios

## 📌 Descripción

Este proyecto corresponde a una prueba técnica enfocada en la implementación de una arquitectura basada en microservicios, aplicando buenas prácticas de desarrollo, diseño y despliegue.

---

## 🎯 Objetivo

Desarrollar una solución que permita gestionar clientes, cuentas y movimientos financieros mediante microservicios, asegurando:

- Buenas prácticas (Clean Code, Clean Architecture)
- Comunicación entre servicios
- Persistencia en base de datos relacional
- Exposición de APIs REST
- Despliegue en Docker

---

## 🧱 Arquitectura

La solución debe dividirse en **2 microservicios**:

### 1️⃣ Microservicio de Clientes
- Persona
- Cliente

### 2️⃣ Microservicio de Cuentas
- Cuenta
- Movimientos

📡 Debe existir **comunicación asincrónica** entre ambos microservicios.

---

## ⚙️ Tecnologías

- Java + Spring Boot **o** .NET (NetCore 5+ / ASP.NET)
- Base de datos relacional
- Docker
- Postman / Karate DSL
- IDE de preferencia

---

## 🧠 Buenas Prácticas Requeridas

- Clean Code
- Clean Architecture
- Patrón Repository
- Manejo de excepciones
- Al menos 1 prueba unitaria

---

## 🌐 API REST

Uso de métodos HTTP:

- GET
- POST
- PUT
- PATCH
- DELETE

### Endpoints:

/clientes
/cuentas
/movimientos

---

## 🧩 Modelos de Datos

### 👤 Persona

- nombre
- genero
- edad
- identificacion
- direccion
- telefono
- **PK obligatoria**

---

### 👥 Cliente

Hereda de Persona:

- clienteId (PK)
- contraseña
- estado

---

### 💳 Cuenta

- numeroCuenta (PK)
- tipoCuenta
- saldoInicial
- estado

---

### 💸 Movimiento

- fecha
- tipoMovimiento
- valor
- saldo
- **PK obligatoria**

---

## 🚀 Funcionalidades

### ✅ F1 - CRUD

- Cliente: CRUD completo
- Cuenta: Crear, Leer, Actualizar
- Movimiento: Crear, Leer, Actualizar

---

### ✅ F2 - Registro de Movimientos

- Validar saldo disponible
- Actualizar saldo automáticamente

---

### ⭐ Funcionalidades adicionales (opcional)

- F6 y F7 (para máxima puntuación)

---

## 📊 Casos de Uso

### 👥 Creación de Usuarios

| Nombre               | Dirección                | Teléfono   | Contraseña | Estado |
|---------------------|--------------------------|------------|------------|--------|
| Jose Lema           | Otavalo sn y principal  | 098254785  | 1234       | True   |
| Marianela Montalvo  | Amazonas y NNUU         | 097548965  | 5678       | True   |
| Juan Osorio         | 13 junio y Equinoccial  | 098874587  | 1245       | True   |

---

### 💳 Creación de Cuentas

| Número Cuenta | Tipo      | Saldo Inicial | Estado | Cliente              |
|--------------|----------|--------------|--------|----------------------|
| 478758       | Ahorros  | 2000         | True   | Jose Lema           |
| 225487       | Corriente| 100          | True   | Marianela Montalvo  |

---

### ➕ Nueva Cuenta

| Número Cuenta | Tipo      | Saldo Inicial | Estado | Cliente    |
|--------------|----------|--------------|--------|------------|
| 585545       | Corriente| 1000         | True   | Jose Lema  |

---

### 💸 Movimientos

| Número Cuenta | Tipo      | Saldo Inicial | Estado | Movimiento        |
|--------------|----------|--------------|--------|------------------|
| 478758       | Ahorros  | 2000         | True   | Retiro de 575    |
| 225487       | Corriente| 100          | True   | Depósito de 600  |
| 495878       | Ahorros  | 0            | True   | Depósito de 150  |
| 496825       | Ahorros  | 540          | True   | Retiro de 540    |

---

### 📈 Reporte de Movimientos

| Fecha     | Cliente             | Cuenta  | Tipo      | Saldo Inicial | Estado | Movimiento | Saldo Disponible |
|----------|---------------------|---------|----------|--------------|--------|------------|------------------|
| 10/2/2022 | Marianela Montalvo | 225487  | Corriente| 100          | True   | 600        | 700              |
| 8/2/2022  | Marianela Montalvo | 496825  | Ahorros  | 540          | True   | -540       | 0                |

---

## 📄 Ejemplo JSON

```json
{
  "fecha": "10/2/2022",
  "cliente": "Marianela Montalvo",
  "numeroCuenta": "225487",
  "tipo": "Corriente",
  "saldoInicial": 100,
  "estado": true
}
```

# DIAGRAMA ARQUITECTURA/CLASES

<img width="1024" height="1536" alt="image" src="https://github.com/user-attachments/assets/5c71f7e0-ba3c-4877-b721-7ec8c69f1a66" />



## DOCUMENTACION CON SWAGGER

### Cuenta/movimientos

http://localhost:8081/swagger-ui/index.html#/
<img width="1902" height="814" alt="image" src="https://github.com/user-attachments/assets/538c2e3c-185d-412c-bcde-6dd480f3419f" />

### Cliente

## PRUEBAS UNITARIAS 
### Cuenta/movimientos
http://localhost:63342/cliente-service/target/site/jacoco/com.devsu.cliente.application/ClienteService.html
<img width="943" height="268" alt="image" src="https://github.com/user-attachments/assets/0bd76328-6ab1-434a-8952-c87a77ce3f5b" />

