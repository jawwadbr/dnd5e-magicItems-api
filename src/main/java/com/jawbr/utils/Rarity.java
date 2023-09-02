package com.jawbr.utils;

public enum Rarity {
    Common("Common"),
    Uncommon("Uncommon"),
    Rare("Rare"),
    VeryRare("Very Rare"),
    Legendary("Legendary"),
    Varies("Varies"),
    Artifact("Artifact");

    private final String displayName;

    Rarity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}