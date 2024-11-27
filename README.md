# 🏥 **Sistema de Gestión Hospitalaria**

El **Sistema de Gestión Hospitalaria** es una herramienta diseñada para agilizar procesos esenciales en un entorno médico. Permite a recepcionistas y médicos gestionar información de pacientes de manera eficiente, desde su registro hasta su diagnóstico y alta médica.

---

## 📋 **Características Principales**

### 🔒 **Acceso Exclusivo para Empleados**
- Acceso restringido únicamente al personal hospitalario autorizado.
- **Login obligatorio** para consultar información de pacientes.
  - Credenciales necesarias: **DNI** y **nombre del paciente**.
- Las acciones y consultas realizadas se registran automáticamente en el historial del sistema.

---

### 📝 **Registro de Pacientes**
El sistema permite registrar información detallada de los pacientes, incluyendo:

- **Datos registrados**:
  - DNI (identificador único).
  - Nombre completo.
  - Fecha de nacimiento.
  - Motivo de ingreso, síntomas y observaciones iniciales.

- **Expediente generado automáticamente**:
  - El sistema crea un expediente inicial con un diagnóstico vacío, que será actualizado tras la consulta médica.

---

### 🔍 **Consulta de Pacientes**
- Los empleados y médicos autorizados pueden buscar pacientes ingresando el **DNI** y el **nombre**.
- Una vez validados los datos, se muestra el expediente completo, que incluye:
  - Información personal.
  - Diagnósticos previos (si están disponibles).

---

### 🩺 **Gestión Médica del Paciente**
#### **Registro de Diagnósticos**
- Los médicos pueden actualizar el expediente del paciente con:
  - Descripción detallada de la condición.
  - Tratamientos y medicamentos recomendados.
  - Observaciones adicionales.

#### **Dar de Alta**
- Posibilidad de eliminar el registro del paciente al finalizar su tratamiento, marcándolo como dado de alta.

---

## 🚀 **Guía de Instalación**

### 1️⃣ **Clona el Repositorio**
```bash
git clone https://github.com/usuario/repositorio.git
