package edu.uptc.enums;

/**
 * Enumeración que define los diferentes tipos de documentos de identificación aceptados en el sistema.
 * Estos tipos de documento son utilizados para la identificación de usuarios (contratantes y contratistas).
 */
public enum TipoDocumento {
    /**
     * Cédula de Ciudadanía: Documento de identificación para ciudadanos colombianos.
     */
    CC,
    /**
     * Cédula de Extranjería: Documento de identificación para extranjeros residentes en Colombia.
     */
    CE,
    /**
     * Pasaporte: Documento de viaje e identificación internacional.
     */
    PAS,
    /**
     * Permiso por Protección Temporal: Documento de identificación para migrantes venezolanos en Colombia.
     */
    PPT,
    /**
     * Número de Identificación Tributaria: Identificador fiscal para personas jurídicas y algunas personas naturales.
     */
    NIT
}
