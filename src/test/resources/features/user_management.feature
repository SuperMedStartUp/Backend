# language: es
Característica: Gestión de Usuarios
  Como administrador del sistema
  Quiero poder gestionar los usuarios del sistema
  Para controlar el acceso a las diferentes funcionalidades

  Antecedentes:
    Dado que el sistema está en funcionamiento
    Y que los roles están configurados

  Escenario: Crear un nuevo usuario doctor
    Cuando creo un usuario con username "doctor1" y password "pass123" y rol "DOCTOR"
    Entonces el usuario debe ser creado exitosamente
    Y el usuario debe tener el rol "DOCTOR"

  Escenario: Crear un nuevo usuario paciente
    Cuando creo un usuario con username "patient1" y password "pass456" y rol "PATIENT"
    Entonces el usuario debe ser creado exitosamente
    Y el usuario debe tener el rol "PATIENT"

  Escenario: Intentar crear un usuario con username existente
    Dado que existe un usuario con username "doctor2"
    Cuando intento crear un usuario con username "doctor2" y password "pass123" y rol "DOCTOR"
    Entonces debe mostrarse un error indicando que el username ya existe

  Escenario: Obtener usuario por username
    Dado que existe un usuario con username "doctor3"
    Cuando busco el usuario con username "doctor3"
    Entonces debo obtener los datos del usuario
    Y el usuario debe tener el rol "DOCTOR"

  Escenario: Obtener todos los usuarios
    Dado que existen usuarios en el sistema
    Cuando solicito la lista de usuarios
    Entonces debo obtener la lista completa de usuarios