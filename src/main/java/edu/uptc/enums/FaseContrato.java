package edu.uptc.enums;

/**
 * Enumeración que define las diferentes fases por las que puede pasar un contrato en su ciclo de vida.
 * Cada fase representa un estado específico del proceso contractual.
 */
public enum FaseContrato {
    /**
     * Fase inicial donde el contrato es publicado y está disponible para ofertas.
     */
    PUBLICACION,
    /**
     * Fase donde se evalúan las ofertas y se selecciona un contratista potencial.
     */
    LICITACION,
    /**
     * Fase donde el contrato es adjudicado formalmente a un contratista.
     */
    ADJUDICACION,
    /**
     * Fase donde el contrato está en ejecución por parte del contratista.
     */
    EJECUCION
}
