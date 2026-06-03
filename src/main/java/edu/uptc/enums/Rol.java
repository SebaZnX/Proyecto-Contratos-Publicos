package edu.uptc.enums;

/**
 * Enumeración que define los diferentes roles de usuario dentro del sistema.
 * Estos roles determinan los permisos y funcionalidades a los que cada usuario tiene acceso.
 */
public enum Rol {
    /**
     * Rol para usuarios administradores, con acceso completo a la gestión del sistema.
     */
    ADMINISTRADOR,
    /**
     * Rol para usuarios contratantes, encargados de la creación y gestión de contratos.
     */
    CONTRATANTE,
    /**
     * Rol para usuarios contratistas, quienes ejecutan los contratos.
     */
    CONTRATISTA
}
