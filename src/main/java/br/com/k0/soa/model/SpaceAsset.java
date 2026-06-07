package br.com.k0.soa.model;

import jakarta.persistence.MappedSuperclass;

/**
 * Classe abstrata base para ativos espaciais.
 * Demonstra: Abstração, Herança e Polimorfismo (OOP obrigatório).
 */
@MappedSuperclass
public abstract class SpaceAsset {

    // Abstração: contrato que toda entidade espacial deve cumprir
    public abstract String getAssetType();

    // Polimorfismo: cada subclasse implementa seu próprio resumo
    public abstract String getSummary();
}
